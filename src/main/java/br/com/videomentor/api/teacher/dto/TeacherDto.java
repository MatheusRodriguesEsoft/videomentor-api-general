package br.com.videomentor.api.teacher.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.comment.dto.CommentDto;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.notification.dto.NotificationDto;
import br.com.videomentor.api.role.dto.RoleDto;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.user.dto.UserDto;

/**
 * TeacherDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class TeacherDto extends UserDto {

  private List<ClasseDto> classes;

  private List<SubjectDto> subjects;

  public List<ClasseDto> getClasses() {
    return classes;
  }

  public void setClasses(List<ClasseDto> classes) {
    this.classes = classes;
  }

  public List<SubjectDto> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<SubjectDto> subjects) {
    this.subjects = subjects;
  }

  public TeacherDto(UUID idUser, String nmUser, String username, StatusEnum stUser, String password, String imageUrl,
      String imageName, List<RoleDto> roles, List<NotificationDto> notifications, List<CommentDto> comments,
      LocalDateTime createdDate, List<ClasseDto> classes, List<SubjectDto> subjects) {
    super(idUser, nmUser, username, stUser, password, imageUrl, imageName, roles, notifications, comments, createdDate);
    this.classes = classes;
    this.subjects = subjects;
  }

  public TeacherDto(List<ClasseDto> classes, List<SubjectDto> subjects) {
    this.classes = classes;
    this.subjects = subjects;
  }

  public TeacherDto() {
  }
}
