package br.com.videomentor.api.module.model;

import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.subject.model.Subject;
import br.com.videomentor.api.videoaula.model.VideoAula;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Module.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "modules")
@Entity(name = "Module")
public class Module {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idModule;

  private String nmModule;

  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Subject subject;

  @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
  private List<VideoAula> videoAulas;

  private StatusEnum stModule = StatusEnum.ACTIVE;

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

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public List<VideoAula> getVideoAulas() {
    return videoAulas;
  }

  public void setVideoAulas(List<VideoAula> videoAulas) {
    this.videoAulas = videoAulas;
  }

  public StatusEnum getStModule() {
    return stModule;
  }

  public void setStModule(StatusEnum stModule) {
    this.stModule = stModule;
  }

  public Module(UUID idModule, String nmModule, Subject subject, List<VideoAula> videoAulas, StatusEnum stModule) {
    this.idModule = idModule;
    this.nmModule = nmModule;
    this.subject = subject;
    this.videoAulas = videoAulas;
    this.stModule = stModule;
  }

  public Module() {

  }
}
