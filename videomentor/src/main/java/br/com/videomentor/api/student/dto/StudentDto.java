package br.com.videomentor.api.student.dto;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.notification.dto.NotificationDto;
import br.com.videomentor.api.role.dto.RoleDto;
import br.com.videomentor.api.user.dto.UserDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class StudentDto extends UserDto {

  private ClasseDto classe;

  private UUID idClasse;

  public ClasseDto getClasse() {
    return classe;
  }

  public void setClasse(ClasseDto classe) {
    this.classe = classe;
  }

  public UUID getIdClasse() {
    return idClasse;
  }

  public void setIdClasse(UUID idClasse) {
    this.idClasse = idClasse;
  }

  public StudentDto(
    UUID idUser,
    String nmUser,
    String username,
    StatusEnum stUser,
    String password,
    String avatar,
    List<RoleDto> roles,
    List<NotificationDto> notifications,
    LocalDateTime createdDate,
    ClasseDto classe,
    UUID idClasse
  ) {
    super(
      idUser,
      nmUser,
      username,
      stUser,
      password,
      avatar,
      roles,
      notifications,
      createdDate
    );
    this.classe = classe;
    this.idClasse = idClasse;
  }

  public StudentDto(ClasseDto classe, UUID idClasse) {
    this.classe = classe;
    this.idClasse = idClasse;
  }

  public StudentDto() {}
}
