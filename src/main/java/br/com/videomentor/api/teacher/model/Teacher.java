package br.com.videomentor.api.teacher.model;

import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.comment.model.Comment;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.notification.model.Notification;
import br.com.videomentor.api.role.model.Role;
import br.com.videomentor.api.subject.model.Subject;
import br.com.videomentor.api.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Teacher.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Entity(name = "Teacher")
public class Teacher extends User {

  @ManyToMany(mappedBy = "teachers")
  private List<Classe> classes;

  @ManyToMany
  @JoinTable(name = "teacher_subject", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
  private List<Subject> subjects;

  public List<Classe> getClasses() {
    return classes;
  }

  public void setClasses(List<Classe> classes) {
    this.classes = classes;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }

  public Teacher(UUID idUser, @NotBlank String nmUser, @NotBlank String username, @NotBlank String password,
      String imageUrl, String imageName, List<Role> roles, List<Notification> notifications, List<Comment> comments,
      StatusEnum stUser, LocalDateTime createdDate, List<Classe> classes, List<Subject> subjects) {
    super(idUser, nmUser, username, password, imageUrl, imageName, roles, notifications, comments, stUser, createdDate);
    this.classes = classes;
    this.subjects = subjects;
  }

  public Teacher(List<Classe> classes, List<Subject> subjects) {
    this.classes = classes;
    this.subjects = subjects;
  }

  public Teacher() {
  }

}
