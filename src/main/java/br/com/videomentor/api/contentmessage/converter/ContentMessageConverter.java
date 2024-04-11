package br.com.videomentor.api.contentmessage.converter;

import br.com.videomentor.api.contentmessage.dto.ContentMessageDto;
import br.com.videomentor.api.contentmessage.model.ContentMessage;
import br.com.videomentor.api.message.converter.MessageConverter;
import br.com.videomentor.api.user.converter.UserConverter;
import br.com.videomentor.api.commons.AbstractConverter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ContentMessageConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class ContentMessageConverter implements AbstractConverter<ContentMessage, ContentMessageDto> {

  @Autowired
  private MessageConverter messageConverter;

  @Autowired
  private UserConverter userConverter;

  @Override
  public ContentMessage dtoToOrm(ContentMessageDto dto, ContentMessage orm) {
    if (dto.getIdContentMessage() != null)
      orm.setIdContentMessage(dto.getIdContentMessage());
    orm.setContent(dto.getContent());
    orm.setUser(userConverter.dtoToOrm(dto.getUser()));
    orm.setDate(dto.getDate());
    if (dto.getMessage() != null) {
      orm.setMessage(messageConverter.dtoToOrm(dto.getMessage()));
    }
    orm.setStatusMessage(dto.getStatusMessage());
    return orm;
  }

  @Override
  public ContentMessage dtoToOrm(ContentMessageDto dto) {
    return dtoToOrm(dto, new ContentMessage());
  }

  @Override
  public ContentMessageDto ormToDto(ContentMessage orm, ContentMessageDto dto) {
    dto.setIdContentMessage(orm.getIdContentMessage());
    dto.setContent(orm.getContent());
    dto.setUser(userConverter.ormToDto(orm.getUser()));
    dto.setDate(orm.getDate());
    dto.setStatusMessage(orm.getStatusMessage());
    return dto;
  }

  @Override
  public ContentMessageDto ormToDto(ContentMessage orm) {
    return ormToDto(orm, new ContentMessageDto());
  }

  @Override
  public List<ContentMessage> dtoListToOrmList(List<ContentMessageDto> dtoList) {
    if (dtoList == null)
      return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<ContentMessageDto> ormListToDtoList(List<ContentMessage> ormList) {
    if (ormList == null)
      return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
