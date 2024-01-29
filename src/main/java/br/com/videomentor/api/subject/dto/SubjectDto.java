package br.com.videomentor.api.subject.dto;

import br.com.videomentor.api.areaofknowledge.dto.AreaOfKnowledgeDto;
import br.com.videomentor.api.enumerations.StatusEnum;
import java.util.UUID;

/**
 * SubjectDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class SubjectDto {

  private UUID idSubject;

  private String nmSubject;

  private AreaOfKnowledgeDto areaOfKnowledge;

  private StatusEnum stSubject;

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

  public AreaOfKnowledgeDto getAreaOfKnowledge() {
    return areaOfKnowledge;
  }

  public void setAreaOfKnowledge(AreaOfKnowledgeDto areaOfKnowledge) {
    this.areaOfKnowledge = areaOfKnowledge;
  }

  public StatusEnum getStSubject() {
    return stSubject;
  }

  public void setStSubject(StatusEnum stSubject) {
    this.stSubject = stSubject;
  }

  public SubjectDto(
    UUID idSubject,
    String nmSubject,
    AreaOfKnowledgeDto areaOfKnowledge,
    StatusEnum stSubject
  ) {
    this.idSubject = idSubject;
    this.nmSubject = nmSubject;
    this.areaOfKnowledge = areaOfKnowledge;
    this.stSubject = stSubject;
  }

  public SubjectDto() {}
}
