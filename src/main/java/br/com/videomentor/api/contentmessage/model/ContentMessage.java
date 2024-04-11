package br.com.videomentor.api.contentmessage.model;

import br.com.videomentor.api.enumerations.StatusContentMessageEnum;
import br.com.videomentor.api.message.model.Message;
import br.com.videomentor.api.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;

/**
 * ContentMessage.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "contentmessages")
@Entity(name = "ContentMessage")
public class ContentMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idContentMessage;

  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private Date date;

  @ManyToOne
  @JoinColumn(name = "message_id")
  private Message message;

  private StatusContentMessageEnum statusMessage = StatusContentMessageEnum.SENT;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public StatusContentMessageEnum getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(StatusContentMessageEnum statusMessage) {
    this.statusMessage = statusMessage;
  }

  public ContentMessage(UUID idContentMessage, String content, User user, Date date, Message message,
      StatusContentMessageEnum statusMessage) {
    this.idContentMessage = idContentMessage;
    this.content = content;
    this.user = user;
    this.date = date;
    this.message = message;
    this.statusMessage = statusMessage;
  }

  public ContentMessage() {
  }

}
