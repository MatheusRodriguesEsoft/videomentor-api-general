package br.com.videomentor.api.serie.converter;

import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.serie.dto.SerieDto;
import br.com.videomentor.api.serie.model.Serie;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * SerieConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class SerieConverter implements AbstractConverter<Serie, SerieDto> {

  @Override
  public Serie dtoToOrm(SerieDto dto, Serie orm) {
    if (dto.getIdSerie() != null) orm.setIdSerie(dto.getIdSerie());
    orm.setNmSerie(dto.getNmSerie());

    orm.setStSerie(dto.getStSerie());
    return orm;
  }

  @Override
  public Serie dtoToOrm(SerieDto dto) {
    return dtoToOrm(dto, new Serie());
  }

  @Override
  public SerieDto ormToDto(Serie orm, SerieDto dto) {
    dto.setIdSerie(orm.getIdSerie());
    dto.setNmSerie(orm.getNmSerie());
    dto.setStSerie(orm.getStSerie());
    return dto;
  }

  @Override
  public SerieDto ormToDto(Serie orm) {
    return ormToDto(orm, new SerieDto());
  }

  @Override
  public List<Serie> dtoListToOrmList(List<SerieDto> dtoList) {
    if (dtoList == null) return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<SerieDto> ormListToDtoList(List<Serie> ormList) {
    if (ormList == null) return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
