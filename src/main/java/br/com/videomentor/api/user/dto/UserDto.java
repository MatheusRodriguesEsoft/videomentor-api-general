package br.com.videomentor.api.user.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.notification.dto.NotificationDto;
import br.com.videomentor.api.role.dto.RoleDto;
import jakarta.validation.constraints.NotBlank;

/**
 * UserDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class UserDto {

  private UUID idUser;

  private String nmUser;

  private String username;

  private String imageUrl;

  private String imageName;

  private StatusEnum stUser;

  @NotBlank
  @Length(min = 8, max = 8)
  private String password;

  // @Length(min = 8, max = 8)
  private String temporaryPassword;

  private List<RoleDto> roles;

  private List<NotificationDto> notifications;

  private LocalDateTime createdDate;

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public StatusEnum getStUser() {
    return stUser;
  }

  public void setStUser(StatusEnum stUser) {
    this.stUser = stUser;
  }

  public List<NotificationDto> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<NotificationDto> notifications) {
    this.notifications = notifications;
  }

  public String getPassword() {
    return password;
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

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public UserDto(
    UUID idUser,
    String nmUser,
    String username,
    StatusEnum stUser,
    String password,
    String imageUrl,
    String imageName,
    List<RoleDto> roles,
    List<NotificationDto> notifications,
    LocalDateTime createdDate
  ) {
    this.idUser = idUser;
    this.nmUser = nmUser;
    this.username = username;
    this.stUser = stUser;
    this.password = password;
    this.imageUrl = imageUrl;
    this.imageName = imageName;
    this.roles = roles;
    this.notifications = notifications;
    this.createdDate = createdDate;
  }

  public UserDto() {}
}
