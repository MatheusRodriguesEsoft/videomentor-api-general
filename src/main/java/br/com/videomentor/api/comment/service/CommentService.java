package br.com.videomentor.api.comment.service;

import br.com.videomentor.api.comment.converter.CommentConverter;
import br.com.videomentor.api.comment.dto.CommentDto;
import br.com.videomentor.api.comment.model.Comment;
import br.com.videomentor.api.comment.repository.CommentRepository;
import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.exceptions.NotFoundException;
import br.com.videomentor.api.videoaula.model.VideoAula;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements AbstractService<CommentDto> {

  @Autowired
  private CommentConverter commentConverter;

  @Autowired
  private CommentRepository commentRepository;

  @Override
  public CommentDto create(CommentDto commentDto) {
    try {
      Comment comment = commentConverter.dtoToOrm(commentDto);
      return commentConverter.ormToDto(commentRepository.save(comment));
    } catch (Exception e) {
      new HandleRuntimeException("Erro ao registrar Comentário");
      return null;
    }
  }

  @Override
  public CommentDto retrieveById(UUID uuid) {
    Comment comment = commentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Comment not found"));
    return commentConverter.ormToDto(comment);
  }

  @Override
  public Page<CommentDto> retrieveAll(Pageable pageable) {
    List<Comment> comments = commentRepository.findAll().stream().collect(Collectors.toList());
    List<CommentDto> commentDtos = comments.stream().map(c -> commentConverter.ormToDto(c))
        .collect(Collectors.toList());
    Page<CommentDto> page = new PageImpl<CommentDto>(commentDtos, pageable, comments.size());
    return page;
  }

  public List<CommentDto> retrieveByVideoAula(VideoAula videoAula) {
    List<Comment> comments = commentRepository.findByVideoAula(videoAula);
    return comments.stream().map(commentConverter::ormToDto).collect(Collectors.toList());
  }

  @Override
  public CommentDto update(CommentDto commentDto) {
    try {
      Comment comment = commentRepository.getReferenceById(commentDto.getIdComment());
      commentRepository.save(commentConverter.dtoToOrm(commentDto, comment));
      return commentConverter.ormToDto(comment, commentDto);
    } catch (Exception e) {
      new HandleRuntimeException(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    try {
      Comment comment = commentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Comment not found"));
      commentRepository.deleteById(comment.getIdComment());
    } catch (Exception e) {
      throw new HandleRuntimeException("Erro ao deletar a Comment");
    }
  }
}
