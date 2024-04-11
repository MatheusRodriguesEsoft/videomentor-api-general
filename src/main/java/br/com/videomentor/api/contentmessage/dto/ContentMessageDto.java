package br.com.videomentor.api.contentmessage.dto;

import br.com.videomentor.api.enumerations.StatusContentMessageEnum;
import br.com.videomentor.api.message.dto.MessageDto;
import br.com.videomentor.api.user.dto.UserDto;

import java.util.Date;
import java.util.UUID;

/**
 * ContentMessageDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class ContentMessageDto {

  private UUID idContentMessage;

  private String content;

  private UserDto user;

  private Date date;

  private MessageDto message;

  private StatusContentMessageEnum statusMessage;

  public UUID getIdContentMessage() {
    return idContentMessage;
  }

  public void setIdContentMessage(UUID idContentMessage) {
    this.idContentMessage = idContentMessage;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }


  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public MessageDto getMessage() {
    return message;
  }

  public void setMessage(MessageDto message) {
    this.message = message;
  }

  public StatusContentMessageEnum getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(StatusContentMessageEnum statusMessage) {
    this.statusMessage = statusMessage;
  }

  public ContentMessageDto(UUID idContentMessage, String content, UserDto user, Date date, MessageDto message,
      StatusContentMessageEnum statusMessage) {
    this.idContentMessage = idContentMessage;
    this.content = content;
    this.user = user;
    this.date = date;
    this.message = message;
    this.statusMessage = statusMessage;
  }

  public ContentMessageDto() {
  }

}
