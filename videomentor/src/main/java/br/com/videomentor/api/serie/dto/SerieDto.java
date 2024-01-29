package br.com.videomentor.api.serie.dto;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.enumerations.StatusEnum;
import java.util.List;
import java.util.UUID;

/**
 * SerieDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class SerieDto {

  private UUID idSerie;

  private String nmSerie;

  private StatusEnum stSerie;

  private List<ClasseDto> classes;

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

  public StatusEnum getStSerie() {
    return stSerie;
  }

  public void setStSerie(StatusEnum stSerie) {
    this.stSerie = stSerie;
  }

  public List<ClasseDto> getClasses() {
    return classes;
  }

  public void setClasses(List<ClasseDto> classes) {
    this.classes = classes;
  }

  public SerieDto(
    UUID idSerie,
    String nmSerie,
    StatusEnum stSerie,
    List<ClasseDto> classes
  ) {
    this.idSerie = idSerie;
    this.nmSerie = nmSerie;
    this.stSerie = stSerie;
    this.classes = classes;
  }

  public SerieDto() {}
}
