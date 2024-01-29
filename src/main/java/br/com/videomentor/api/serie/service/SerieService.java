package br.com.videomentor.api.serie.service;

import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.NotFoundException;
import br.com.videomentor.api.serie.converter.SerieConverter;
import br.com.videomentor.api.serie.dto.SerieDto;
import br.com.videomentor.api.serie.model.Serie;
import br.com.videomentor.api.serie.repository.SerieRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SerieService implements AbstractService<SerieDto> {

  @Autowired
  private SerieRepository serieRepository;

  @Autowired
  private SerieConverter serieConverter;

  @Override
  public SerieDto create(SerieDto dto) {
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public SerieDto retrieveById(UUID uuid) {
    Serie serie = serieRepository
      .findById(uuid)
      .orElseThrow(() -> new NotFoundException("Serie not found."));
    return serieConverter.ormToDto(serie);
  }

  @Override
  public Page<SerieDto> retrieveAll(Pageable pageable) {
    Page<Serie> seriePage = serieRepository.findAll(pageable);
    List<SerieDto> serieDtoList = serieConverter.ormListToDtoList(
      seriePage.getContent()
    );
    return new PageImpl<>(serieDtoList, pageable, seriePage.getTotalElements());
  }

  @Override
  public SerieDto update(SerieDto dto) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(UUID uuid) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
