package br.com.videomentor.api.serie.model;

import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.enumerations.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

/**
 * Serie.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "series")
@Entity(name = "Serie")
public class Serie {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idSerie;

  private String nmSerie;

  @OneToMany(mappedBy = "serie")
  private List<Classe> classes;

  private StatusEnum stSerie = StatusEnum.ACTIVE;

  public UUID getIdSerie() {
    return idSerie;
  }

  public void setIdSerie(UUID idSerie) {
    this.idSerie = idSerie;
  }

  public String getNmSerie() {
    return nmSerie;
  }

  public void setNmSerie(String nmSerie) {
    this.nmSerie = nmSerie;
  }

  public List<Classe> getClasses() {
    return classes;
  }

  public void setClasses(List<Classe> classes) {
    this.classes = classes;
  }

  public StatusEnum getStSerie() {
    return stSerie;
  }

  public void setStSerie(StatusEnum stSerie) {
    this.stSerie = stSerie;
  }

  public Serie(
    UUID idSerie,
    String nmSerie,
    List<Classe> classes,
    StatusEnum stSerie
  ) {
    this.idSerie = idSerie;
    this.nmSerie = nmSerie;
    this.classes = classes;
    this.stSerie = stSerie;
  }

  public Serie() {}
}
