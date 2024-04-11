package br.com.videomentor.api.message.dto;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.contentmessage.dto.ContentMessageDto;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.enumerations.StatusMessageEnum;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.user.dto.UserDto;
import io.micrometer.common.lang.Nullable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * MessageDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class MessageDto {

  private UUID idMessage;

  private UserDto user;

  private List<ContentMessageDto> contentMessages;

  private UserDto sender;

  private UserDto receiver;

  @Nullable
  private SubjectDto subject;

  @Nullable
  private ClasseDto classe;

  private Date createdDate;

  private StatusMessageEnum statusMessage;

  private StatusEnum stMessage;

  public UUID getIdMessage() {
    return idMessage;
  }

  public void setIdMessage(UUID idMessage) {
    this.idMessage = idMessage;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public List<ContentMessageDto> getContentMessages() {
    return contentMessages;
  }

  public void setContentMessages(List<ContentMessageDto> contentMessages) {
    this.contentMessages = contentMessages;
  }

  public UserDto getSender() {
    return sender;
  }

  public void setSender(UserDto sender) {
    this.sender = sender;
  }

  public UserDto getReceiver() {
    return receiver;
  }

  public void setReceiver(UserDto receiver) {
    this.receiver = receiver;
  }

  public SubjectDto getSubject() {
    return subject;
  }

  public void setSubject(SubjectDto subject) {
    this.subject = subject;
  }

  public ClasseDto getClasse() {
    return classe;
  }

  public void setClasse(ClasseDto classe) {
    this.classe = classe;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public StatusMessageEnum getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(StatusMessageEnum statusMessage) {
    this.statusMessage = statusMessage;
  }

  public StatusEnum getStMessage() {
    return stMessage;
  }

  public void setStMessage(StatusEnum stMessage) {
    this.stMessage = stMessage;
  }

  public MessageDto(UUID idMessage, UserDto user, List<ContentMessageDto> contentMessages, UserDto sender,
      UserDto receiver, SubjectDto subject, ClasseDto classe, Date createdDate, StatusMessageEnum statusMessage,
      StatusEnum stMessage) {
    this.idMessage = idMessage;
    this.user = user;
    this.contentMessages = contentMessages;
    this.sender = sender;
    this.receiver = receiver;
    this.subject = subject;
    this.classe = classe;
    this.createdDate = createdDate;
    this.statusMessage = statusMessage;
    this.stMessage = stMessage;
  }

  public MessageDto() {
  }

}
