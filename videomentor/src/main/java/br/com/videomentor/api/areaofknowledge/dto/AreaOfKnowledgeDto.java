package br.com.videomentor.api.areaofknowledge.dto;

import br.com.videomentor.api.subject.dto.SubjectDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.UUID;

/**
 * AreaOfKnowledgeDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class AreaOfKnowledgeDto {

  private UUID idAreaOfKnowledge;

  private String nmAreaOfKnowledge;

  @JsonIgnore
  private List<SubjectDto> subjects;

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

  public List<SubjectDto> getSubjects() {
    return subjects;
  }

  public void setSubjects(List<SubjectDto> subjects) {
    this.subjects = subjects;
  }

  public AreaOfKnowledgeDto(
    UUID idAreaOfKnowledge,
    String nmAreaOfKnowledge,
    List<SubjectDto> subjects
  ) {
    this.idAreaOfKnowledge = idAreaOfKnowledge;
    this.nmAreaOfKnowledge = nmAreaOfKnowledge;
    this.subjects = subjects;
  }

  public AreaOfKnowledgeDto() {}
}
