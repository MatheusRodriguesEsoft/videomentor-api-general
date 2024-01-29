package br.com.videomentor.api.classe.model;

import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.serie.model.Serie;
import br.com.videomentor.api.student.model.Student;
import br.com.videomentor.api.teacher.model.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Classe.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "classes")
@Entity(name = "Classe")
public class Classe {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idClasse;

  private String nmClasse;

  @ManyToOne
  @JoinColumn(name = "serie_id")
  private Serie serie;

  @ManyToMany
  @JoinTable(
    name = "classe_teacher",
    joinColumns = @JoinColumn(name = "classe_id"),
    inverseJoinColumns = @JoinColumn(name = "teacher_id")
  )
  private List<Teacher> teachers;

  @OneToMany(mappedBy = "classe")
  private List<Student> students;

  private StatusEnum stClasse = StatusEnum.ACTIVE;

  public UUID getIdClasse() {
    return idClasse;
  }

  public void setIdClasse(UUID idClasse) {
    this.idClasse = idClasse;
  }

  public String getNmClasse() {
    return nmClasse;
  }

  public void setNmClasse(String nmClasse) {
    this.nmClasse = nmClasse;
  }

  public Serie getSerie() {
    return serie;
  }

  public void setSerie(Serie serie) {
    this.serie = serie;
  }

  public List<Teacher> getTeachers() {
    return teachers;
  }

  public void setTeachers(List<Teacher> teachers) {
    this.teachers = teachers;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public StatusEnum getStClasse() {
    return stClasse;
  }

  public void setStClasse(StatusEnum stClasse) {
    this.stClasse = stClasse;
  }

  public Classe(
    UUID idClasse,
    String nmClasse,
    Serie serie,
    List<Teacher> teachers,
    List<Student> students,
    StatusEnum stClasse
  ) {
    this.idClasse = idClasse;
    this.nmClasse = nmClasse;
    this.serie = serie;
    this.teachers = teachers;
    this.students = students;
    this.stClasse = stClasse;
  }

  public Classe() {}
}
