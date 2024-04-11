package br.com.videomentor.api.comment.converter;

import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.comment.dto.CommentDto;
import br.com.videomentor.api.comment.model.Comment;
import br.com.videomentor.api.user.converter.UserConverter;
import br.com.videomentor.api.videoaula.converter.VideoAulaConverter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CommentConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class CommentConverter implements AbstractConverter<Comment, CommentDto> {

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private VideoAulaConverter videoAulaConverter;

  @Override
  public Comment dtoToOrm(CommentDto dto, Comment orm) {
    if (dto.getIdComment() != null)
      orm.setIdComment(dto.getIdComment());
    orm.setContentComment(dto.getContentComment());
    orm.setUser(userConverter.dtoToOrm(dto.getUser()));
    orm.setVideoAula(videoAulaConverter.dtoToOrm(dto.getVideoAula()));
    orm.setStComment(dto.getStComment());
    return orm;
  }

  @Override
  public Comment dtoToOrm(CommentDto dto) {
    return dtoToOrm(dto, new Comment());
  }

  @Override
  public CommentDto ormToDto(Comment orm, CommentDto dto) {
    dto.setIdComment(orm.getIdComment());
    dto.setContentComment(orm.getContentComment());
    dto.setUser(userConverter.ormToDto(orm.getUser()));
    dto.setVideoAula(videoAulaConverter.ormToDto(orm.getVideoAula()));
    dto.setStComment(orm.getStComment());
    return dto;
  }

  @Override
  public CommentDto ormToDto(Comment orm) {
    return ormToDto(orm, new CommentDto());
  }

  @Override
  public List<Comment> dtoListToOrmList(List<CommentDto> dtoList) {
    if (dtoList == null)
      return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<CommentDto> ormListToDtoList(List<Comment> ormList) {
    if (ormList == null)
      return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
