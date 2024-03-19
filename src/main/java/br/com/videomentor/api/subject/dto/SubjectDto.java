package br.com.videomentor.api.subject.dto;

import br.com.videomentor.api.areaofknowledge.dto.AreaOfKnowledgeDto;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.videoaula.dto.VideoAulaDto;
import java.util.List;
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

  private String imageUrl;

  private String imageName;

  private AreaOfKnowledgeDto areaOfKnowledge;

  private List<VideoAulaDto> videoAulas;

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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
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

  public List<VideoAulaDto> getVideoAulas() {
    return videoAulas;
  }

  public void setVideoAulas(List<VideoAulaDto> videoAulas) {
    this.videoAulas = videoAulas;
  }

  public SubjectDto(UUID idSubject, String nmSubject, String imageUrl, String imageName,
      AreaOfKnowledgeDto areaOfKnowledge, List<VideoAulaDto> videoAulas, StatusEnum stSubject) {
    this.idSubject = idSubject;
    this.nmSubject = nmSubject;
    this.imageUrl = imageUrl;
    this.imageName = imageName;
    this.areaOfKnowledge = areaOfKnowledge;
    this.videoAulas = videoAulas;
    this.stSubject = stSubject;
  }

  public SubjectDto() {
  }

}
