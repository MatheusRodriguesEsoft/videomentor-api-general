package br.com.videomentor.api.contentmessage.service;

import br.com.videomentor.api.contentmessage.converter.ContentMessageConverter;
import br.com.videomentor.api.contentmessage.dto.ContentMessageDto;
import br.com.videomentor.api.contentmessage.model.ContentMessage;
import br.com.videomentor.api.contentmessage.repository.ContentMessageRepository;
import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentMessageService implements AbstractService<ContentMessageDto> {

  @Autowired
  private ContentMessageConverter contentMessageConverter;

  @Autowired
  private ContentMessageRepository contentMessageRepository;

  @Override
  public ContentMessageDto create(ContentMessageDto contentMessageDto) {
    try {
      ContentMessage contentMessage = contentMessageConverter.dtoToOrm(contentMessageDto);
      return contentMessageConverter.ormToDto(contentMessageRepository.save(contentMessage));
    } catch (Exception e) {
      new HandleRuntimeException("Erro ao registrar ContentMessage");
      return null;
    }
  }

  @Override
  public ContentMessageDto retrieveById(UUID uuid) {
    ContentMessage contentMessage = contentMessageRepository.findById(uuid)
        .orElseThrow(() -> new NotFoundException("ContentMessage not found"));
    return contentMessageConverter.ormToDto(contentMessage);
  }

  @Override
  public Page<ContentMessageDto> retrieveAll(Pageable pageable) {
    List<ContentMessage> contentMessages = contentMessageRepository.findAll().stream().collect(Collectors.toList());
    List<ContentMessageDto> contentMessageDtos = contentMessages.stream().map(c -> contentMessageConverter.ormToDto(c))
        .collect(Collectors.toList());
    Page<ContentMessageDto> page = new PageImpl<ContentMessageDto>(contentMessageDtos, pageable,
        contentMessages.size());
    return page;
  }

  @Override
  public ContentMessageDto update(ContentMessageDto contentMessageDto) {
    try {
      ContentMessage contentMessage = contentMessageRepository
          .getReferenceById(contentMessageDto.getIdContentMessage());
      contentMessageRepository.save(contentMessageConverter.dtoToOrm(contentMessageDto, contentMessage));
      return contentMessageConverter.ormToDto(contentMessage, contentMessageDto);
    } catch (Exception e) {
      new HandleRuntimeException(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    try {
      ContentMessage contentMessage = contentMessageRepository.findById(uuid)
          .orElseThrow(() -> new NotFoundException("ContentMessage not found"));

      contentMessageRepository.deleteById(contentMessage.getIdContentMessage());
    } catch (Exception e) {
      throw new HandleRuntimeException("Erro ao deletar a ContentMessage");
    }
  }
}
