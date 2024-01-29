package br.com.videomentor.api.student.model;

import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.notification.orm.Notification;
import br.com.videomentor.api.role.model.Role;
import br.com.videomentor.api.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Student.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Entity(name = "Student")
public class Student extends User {

  @ManyToOne
  @JoinColumn(name = "classe_id")
  private Classe classe;

  public Classe getClasse() {
    return classe;
  }

  public void setClasse(Classe classe) {
    this.classe = classe;
  }

  public Student(
    UUID idUser,
    @NotBlank String nmUser,
    @NotBlank String username,
    @NotBlank String password,
    String image,
    List<Role> roles,
    List<Notification> notifications,
    StatusEnum stUser,
    LocalDateTime createdDate,
    Classe classe
  ) {
    super(
      idUser,
      nmUser,
      username,
      password,
      image,
      roles,
      notifications,
      stUser,
      createdDate
    );
    this.classe = classe;
  }

  public Student(Classe classe) {
    this.classe = classe;
  }

  public Student() {}
}
