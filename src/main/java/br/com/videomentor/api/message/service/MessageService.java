package br.com.videomentor.api.message.service;

import br.com.videomentor.api.message.converter.MessageConverter;
import br.com.videomentor.api.message.dto.MessageDto;
import br.com.videomentor.api.message.model.Message;
import br.com.videomentor.api.message.repository.MessageRepository;
import br.com.videomentor.api.user.model.User;
import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.contentmessage.repository.ContentMessageRepository;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class MessageService implements AbstractService<MessageDto> {

  @Autowired
  private MessageConverter messageConverter;

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private ContentMessageRepository contentMessageRepository;

  @Override
  public MessageDto create(MessageDto messageDto) {
    try {
      Message message = messageConverter.dtoToOrm(messageDto);
      messageRepository.save(message);
      message.getContentMessages().forEach(contentMessage -> {
        contentMessage.setMessage(message);
        contentMessageRepository.save(contentMessage);
      });
      return messageConverter.ormToDto(message);
    } catch (Exception e) {
      new HandleRuntimeException("Erro ao registrar Message");
      return null;
    }
  }

  @Override
  public MessageDto retrieveById(UUID uuid) {
    Message message = messageRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Message not found"));
    return messageConverter.ormToDto(message);
  }

  @Override
  public Page<MessageDto> retrieveAll(Pageable pageable) {
    List<Message> messages = messageRepository.findAll().stream().collect(Collectors.toList());
    List<MessageDto> messageDtos = messages.stream().map(m -> messageConverter.ormToDto(m))
        .collect(Collectors.toList());
    Page<MessageDto> page = new PageImpl<MessageDto>(messageDtos, pageable, messages.size());
    return page;
  }

  public List<MessageDto> retrieveByUser(User user) {
    List<Message> messages = messageRepository.findByUser(user);
    return messages.stream().map(messageConverter::ormToDto).collect(Collectors.toList());
  }

  public List<MessageDto> retrieveByReceiver(User user) {
    List<Message> messages = messageRepository.findByReceiver(user);
    return messages.stream().map(messageConverter::ormToDto).collect(Collectors.toList());
  }

  public List<MessageDto> retrieveBySender(User user) {
    List<Message> messages = messageRepository.findBySender(user);
    return messages.stream().map(messageConverter::ormToDto).collect(Collectors.toList());
  }

  @Override
  public MessageDto update(MessageDto messageDto) {
    try {
      Message message = messageRepository.getReferenceById(messageDto.getIdMessage());
      messageRepository.save(messageConverter.dtoToOrm(messageDto, message));
      return messageConverter.ormToDto(message, messageDto);
    } catch (Exception e) {
      new HandleRuntimeException(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    try {
      Message message = messageRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Message not found"));

      messageRepository.deleteById(message.getIdMessage());
    } catch (Exception e) {
      throw new HandleRuntimeException("Erro ao deletar a Message");
    }
  }
}
