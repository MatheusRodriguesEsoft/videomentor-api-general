package br.com.videomentor.api.message.model;

import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.contentmessage.model.ContentMessage;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.enumerations.StatusMessageEnum;
import br.com.videomentor.api.subject.model.Subject;
import br.com.videomentor.api.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Message.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "messages")
@Entity(name = "Message")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idMessage;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "message")
  private List<ContentMessage> contentMessages;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User sender;

  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private User receiver;

  @ManyToOne
  @JoinColumn(name = "subject_id", nullable = true)
  private Subject subject;

  @ManyToOne
  @JoinColumn(name = "classe_id", nullable = true)
  private Classe classe;

  private Date createdDate;

  private StatusMessageEnum statusMessage = StatusMessageEnum.NEW;

  private StatusEnum stMessage = StatusEnum.ACTIVE;

  public UUID getIdMessage() {
    return idMessage;
  }

  public void setIdMessage(UUID idMessage) {
    this.idMessage = idMessage;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<ContentMessage> getContentMessages() {
    return contentMessages;
  }

  public void setContentMessages(List<ContentMessage> contentMessages) {
    this.contentMessages = contentMessages;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public Classe getClasse() {
    return classe;
  }

  public void setClasse(Classe classe) {
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

  public Message(UUID idMessage, User user, List<ContentMessage> contentMessages, User sender, User receiver, Subject subject,
    Classe classe, Date createdDate, StatusMessageEnum statusMessage, StatusEnum stMessage) {
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

  public Message() {
  }



}
