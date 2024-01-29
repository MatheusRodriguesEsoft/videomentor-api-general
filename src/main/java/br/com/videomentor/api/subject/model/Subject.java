package br.com.videomentor.api.subject.model;

import br.com.videomentor.api.areaofknowledge.model.AreaOfKnowledge;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.teacher.model.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Subject.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "subjects")
@Entity(name = "Subject")
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idSubject;

  private String nmSubject;

  @ManyToOne
  @JoinColumn(name = "area_of_knowledge_id")
  private AreaOfKnowledge areaOfKnowledge;

  @ManyToMany(mappedBy = "subjects")
  private List<Teacher> teachers;

  private StatusEnum stSubject = StatusEnum.ACTIVE;

  public UUID getIdSubject() {
    return idSubject;
  }

  public void setIdSubject(UUID idSubject) {
    this.idSubject = idSubject;
  }

  public String getNmSubject() {
    return nmSubject;
  }

  public void setNmSubject(String nmSubject) {
    this.nmSubject = nmSubject;
  }

  public AreaOfKnowledge getAreaOfKnowledge() {
    return areaOfKnowledge;
  }

  public void setAreaOfKnowledge(AreaOfKnowledge areaOfKnowledge) {
    this.areaOfKnowledge = areaOfKnowledge;
  }

  public List<Teacher> getTeachers() {
    return teachers;
  }

  public void setTeachers(List<Teacher> teachers) {
    this.teachers = teachers;
  }

  public StatusEnum getStSubject() {
    return stSubject;
  }

  public void setStSubject(StatusEnum stSubject) {
    this.stSubject = stSubject;
  }

  public Subject(
    UUID idSubject,
    String nmSubject,
    AreaOfKnowledge areaOfKnowledge,
    List<Teacher> teachers,
    StatusEnum stSubject
  ) {
    this.idSubject = idSubject;
    this.nmSubject = nmSubject;
    this.areaOfKnowledge = areaOfKnowledge;
    this.teachers = teachers;
    this.stSubject = stSubject;
  }

  public Subject() {}
}