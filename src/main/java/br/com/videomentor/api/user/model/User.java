package br.com.videomentor.api.user.model;

import br.com.videomentor.api.comment.model.Comment;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.enumerations.StatusPassword;
import br.com.videomentor.api.message.model.Message;
import br.com.videomentor.api.notification.model.Notification;
import br.com.videomentor.api.role.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "users")
@Entity(name = "User")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idUser;

  @Column(name = "nm_user", nullable = false, length = 50)
  private String nmUser;

  @Column(name = "username", nullable = false, length = 50, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "temporary_password")
  private String temporaryPassword;

  @Column(name = "imageUrl")
  private String imageUrl;

  @Column(name = "imageName")
  private String imageName;

  @Column(name = "stPassword")
  private StatusPassword stPassword = StatusPassword.PROVISORY;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JsonIgnore
  @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
      @JoinColumn(name = "role_id") })
  List<Role> roles;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JsonIgnore
  @JoinTable(name = "users_notifications", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
      @JoinColumn(name = "notification_id") })
  private List<Notification> notifications = new ArrayList<>();

  @OneToMany(mappedBy = "sender")
  private List<Message> sentMessages;

  @OneToMany(mappedBy = "receiver")
  private List<Message> receivedMessages;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments;

  private StatusEnum stUser = StatusEnum.ACTIVE;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @PrePersist
  protected void onCreate() {
    createdDate = LocalDateTime.now();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().toString())));
    return authorities;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public User orElseThrow(Object object) {
    return null;
  }

  public UUID getIdUser() {
    return idUser;
  }

  public void setIdUser(UUID idUser) {
    this.idUser = idUser;
  }

  public String getNmUser() {
    return nmUser;
  }

  public void setNmUser(String nmUser) {
    this.nmUser = nmUser;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTemporaryPassword() {
    return temporaryPassword;
  }

  public void setTemporaryPassword(String temporaryPassword) {
    this.temporaryPassword = temporaryPassword;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }

  public StatusPassword getStPassword() {
    return stPassword;
  }

  public void setStPassword(StatusPassword stPassword) {
    this.stPassword = stPassword;
  }

  public StatusEnum getStUser() {
    return stUser;
  }

  public void setStUser(StatusEnum stUser) {
    this.stUser = stUser;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public List<Message> getSentMessages() {
    return sentMessages;
  }

  public void setSentMessages(List<Message> sentMessages) {
    this.sentMessages = sentMessages;
  }

  public List<Message> getReceivedMessages() {
    return receivedMessages;
  }

  public void setReceivedMessages(List<Message> receivedMessages) {
    this.receivedMessages = receivedMessages;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public User(UUID idUser, @NotBlank String nmUser, @NotBlank String username, @NotBlank String password,
      String imageUrl, String imageName, List<Role> roles, List<Notification> notifications, List<Comment> comments, StatusEnum stUser,
      LocalDateTime createdDate) {
    this.idUser = idUser;
    this.nmUser = nmUser;
    this.username = username;
    this.password = password;
    this.imageUrl = imageUrl;
    this.imageName = imageName;
    this.roles = roles;
    this.notifications = notifications;
    this.comments = comments;
    this.stUser = stUser;
    this.createdDate = createdDate;
  }

  public User() {
  }
}
