package br.com.videomentor.api.message.converter;

import br.com.videomentor.api.classe.converter.ClasseConverter;
import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.contentmessage.converter.ContentMessageConverter;
import br.com.videomentor.api.message.dto.MessageDto;
import br.com.videomentor.api.message.model.Message;
import br.com.videomentor.api.subject.converter.SubjectConverter;
import br.com.videomentor.api.user.converter.UserConverter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MessageConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class MessageConverter implements AbstractConverter<Message, MessageDto> {

  @Autowired
  private UserConverter userConverter;

  @Autowired
  private ContentMessageConverter contentMessageConverter;

  @Autowired
  private SubjectConverter subjectConverter;

  @Autowired
  private ClasseConverter classeConverter;

  @Override
  public Message dtoToOrm(MessageDto dto, Message orm) {
    if (dto.getIdMessage() != null)
      orm.setIdMessage(dto.getIdMessage());
    orm.setUser(userConverter.dtoToOrm(dto.getUser()));
    orm.setContentMessages(contentMessageConverter.dtoListToOrmList(dto.getContentMessages()));
    orm.setSender(userConverter.dtoToOrm(dto.getSender()));
    orm.setReceiver(userConverter.dtoToOrm(dto.getReceiver()));
    if (dto.getSubject() != null) {
      orm.setSubject(subjectConverter.dtoToOrm(dto.getSubject()));
    }
    if (dto.getClasse() != null) {
      orm.setClasse(classeConverter.dtoToOrm(dto.getClasse()));
    }
    orm.setCreatedDate(dto.getCreatedDate());
    orm.setStatusMessage(dto.getStatusMessage());
    orm.setStMessage(dto.getStMessage());

    return orm;
  }

  @Override
  public Message dtoToOrm(MessageDto dto) {
    return dtoToOrm(dto, new Message());
  }

  @Override
  public MessageDto ormToDto(Message orm, MessageDto dto) {
    dto.setIdMessage(orm.getIdMessage());
    dto.setContentMessages(contentMessageConverter.ormListToDtoList(orm.getContentMessages()));
    if (orm.getSubject() != null) {
      dto.setSubject(subjectConverter.ormToDto(orm.getSubject()));
    }
    if (orm.getClasse() != null) {
      dto.setClasse(classeConverter.ormToDto(orm.getClasse()));
    }
    dto.setSender(userConverter.ormToDto(orm.getSender()));
    dto.setReceiver(userConverter.ormToDto(orm.getReceiver()));
    dto.setUser(userConverter.ormToDto(orm.getUser()));
    dto.setCreatedDate(orm.getCreatedDate());
    dto.setStatusMessage(orm.getStatusMessage());
    dto.setStMessage(orm.getStMessage());
    return dto;
  }

  @Override
  public MessageDto ormToDto(Message orm) {
    return ormToDto(orm, new MessageDto());
  }

  @Override
  public List<Message> dtoListToOrmList(List<MessageDto> dtoList) {
    if (dtoList == null)
      return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<MessageDto> ormListToDtoList(List<Message> ormList) {
    if (ormList == null)
      return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
