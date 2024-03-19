package br.com.videomentor.api.module.dto;

import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.videoaula.dto.VideoAulaDto;
import java.util.List;
import java.util.UUID;

/**
 * ModuleDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class ModuleDto {

  private UUID idModule;

  private String nmModule;

  private SubjectDto subject;

  private List<VideoAulaDto> videoAulas;

  private StatusEnum stModule;

  public UUID getIdModule() {
    return idModule;
  }

  public void setIdModule(UUID idModule) {
    this.idModule = idModule;
  }

  public String getNmModule() {
    return nmModule;
  }

  public void setNmModule(String nmModule) {
    this.nmModule = nmModule;
  }

  public SubjectDto getSubject() {
    return subject;
  }

  public void setSubject(SubjectDto subject) {
    this.subject = subject;
  }

  public List<VideoAulaDto> getVideoAulas() {
    return videoAulas;
  }

  public void setVideoAulas(List<VideoAulaDto> videoAulas) {
    this.videoAulas = videoAulas;
  }

  public StatusEnum getStModule() {
    return stModule;
  }

  public void setStModule(StatusEnum stModule) {
    this.stModule = stModule;
  }

  public ModuleDto(UUID idModule, String nmModule, SubjectDto subject, List<VideoAulaDto> videoAulas,
      StatusEnum stModule) {
    this.idModule = idModule;
    this.nmModule = nmModule;
    this.subject = subject;
    this.videoAulas = videoAulas;
    this.stModule = stModule;
  }

  public ModuleDto() {
  }

}
