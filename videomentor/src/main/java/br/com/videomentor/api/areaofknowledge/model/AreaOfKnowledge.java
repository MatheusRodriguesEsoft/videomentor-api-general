package br.com.videomentor.api.areaofknowledge.model;

import br.com.videomentor.api.subject.model.Subject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Areaofknowledge.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "areaofknowledges")
@Entity(name = "AreaOfKnowledge")
public class AreaOfKnowledge {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idAreaOfKnowledge;

  private String nmAreaOfKnowledge;

  @OneToMany(mappedBy = "areaOfKnowledge")
  private List<Subject> subjects;

  public UUID getIdAreaOfKnowledge() {
    return idAreaOfKnowledge;
  }

  public void setIdAreaOfKnowledge(UUID idAreaOfKnowledge) {
    this.idAreaOfKnowledge = idAreaOfKnowledge;
  }

  public String getNmAreaOfKnowledge() {
    return nmAreaOfKnowledge;
  }

  public void setNmAreaOfKnowledge(String nmAreaOfKnowledge) {
    this.nmAreaOfKnowledge = nmAreaOfKnowledge;
  }

  public List<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }

  public AreaOfKnowledge(
    UUID idAreaOfKnowledge,
    String nmAreaOfKnowledge,
    List<Subject> subjects
  ) {
    this.idAreaOfKnowledge = idAreaOfKnowledge;
    this.nmAreaOfKnowledge = nmAreaOfKnowledge;
    this.subjects = subjects;
  }

  public AreaOfKnowledge() {}
}
