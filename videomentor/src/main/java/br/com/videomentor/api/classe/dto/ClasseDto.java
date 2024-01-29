package br.com.videomentor.api.classe.dto;

import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.serie.dto.SerieDto;
import br.com.videomentor.api.student.dto.StudentDto;
import br.com.videomentor.api.teacher.dto.TeacherDto;
import java.util.List;
import java.util.UUID;

/**
 * ClasseDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class ClasseDto {

  private UUID idClasse;

  private String nmClasse;

  private SerieDto serie;

  private List<TeacherDto> teachers;

  private List<StudentDto> students;

  private StatusEnum stClasse;

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

  public SerieDto getSerie() {
    return serie;
  }

  public void setSerie(SerieDto serie) {
    this.serie = serie;
  }

  public List<TeacherDto> getTeachers() {
    return teachers;
  }

  public void setTeachers(List<TeacherDto> teachers) {
    this.teachers = teachers;
  }

  public List<StudentDto> getStudents() {
    return students;
  }

  public void setStudents(List<StudentDto> students) {
    this.students = students;
  }

  public StatusEnum getStClasse() {
    return stClasse;
  }

  public void setStClasse(StatusEnum stClasse) {
    this.stClasse = stClasse;
  }

  public ClasseDto(
    UUID idClasse,
    String nmClasse,
    SerieDto serie,
    List<TeacherDto> teachers,
    List<StudentDto> students,
    StatusEnum stClasse
  ) {
    this.idClasse = idClasse;
    this.nmClasse = nmClasse;
    this.serie = serie;
    this.teachers = teachers;
    this.students = students;
    this.stClasse = stClasse;
  }

  public ClasseDto() {}
}
