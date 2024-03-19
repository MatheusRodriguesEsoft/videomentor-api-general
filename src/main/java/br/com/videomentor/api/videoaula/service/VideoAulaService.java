package br.com.videomentor.api.videoaula.service;

import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.module.model.Module;
import br.com.videomentor.api.videoaula.converter.VideoAulaConverter;
import br.com.videomentor.api.videoaula.dto.VideoAulaDto;
import br.com.videomentor.api.videoaula.model.VideoAula;
import br.com.videomentor.api.videoaula.repository.VideoAulaRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class VideoAulaService implements AbstractService<VideoAulaDto> {

  @Autowired
  private VideoAulaConverter videoAulaConverter;

  @Autowired
  private VideoAulaRepository videoAulaRepository;

  @Override
  public VideoAulaDto create(VideoAulaDto videoAulaDto) {
    try {
      VideoAula videoAula = videoAulaConverter.dtoToOrm(videoAulaDto);
      return videoAulaConverter.ormToDto(videoAulaRepository.save(videoAula));
    } catch (Exception e) {
      new HandleRuntimeException("Erro ao registrar Videoaula");
      return null;
    }
  }

  @Override
  public VideoAulaDto retrieveById(UUID uuid) {
    VideoAula videoAula = videoAulaRepository.findById(uuid)
        .orElseThrow(() -> new NotFoundException("Videoaula not found"));
    return videoAulaConverter.ormToDto(videoAula);
  }

  @Override
  public Page<VideoAulaDto> retrieveAll(Pageable pageable) {
    List<VideoAula> videoAulas = videoAulaRepository.findAll().stream().collect(Collectors.toList());
    List<VideoAulaDto> videoAulaDtos = videoAulas.stream().map(v -> videoAulaConverter.ormToDto(v))
        .collect(Collectors.toList());
    Page<VideoAulaDto> page = new PageImpl<VideoAulaDto>(videoAulaDtos, pageable, videoAulas.size());
    return page;
  }

  public List<VideoAulaDto> retrieveByClasse(Classe classe) {
    List<VideoAula> videoAulas = videoAulaRepository.findByClasses(classe);
    return videoAulas.stream().map(videoAulaConverter::ormToDto).collect(Collectors.toList());
  }

  public List<VideoAulaDto> retrieveByModule(Module module) {
    List<VideoAula> videoAulas = videoAulaRepository.findByModule(module);
    return videoAulas.stream().map(videoAulaConverter::ormToDto).collect(Collectors.toList());
  }

  @Override
  public VideoAulaDto update(VideoAulaDto videoAulaDto) {
    try {
      VideoAula videoAula = videoAulaRepository.getReferenceById(videoAulaDto.getIdVideoaula());
      videoAulaRepository.save(videoAulaConverter.dtoToOrm(videoAulaDto, videoAula));
      return videoAulaConverter.ormToDto(videoAula, videoAulaDto);
    } catch (Exception e) {
      new HandleRuntimeException(e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    try {
      VideoAula videoAula = videoAulaRepository.findById(uuid)
          .orElseThrow(() -> new NotFoundException("Videoaula not found"));

      videoAulaRepository.deleteById(videoAula.getIdVideoaula());
    } catch (Exception e) {
      throw new HandleRuntimeException("Erro ao deletar a videoaula");
    }
  }
}
