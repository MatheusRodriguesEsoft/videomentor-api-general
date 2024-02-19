package br.com.videomentor.api.videoaula.converter;

import br.com.videomentor.api.areaofknowledge.converter.AreaOfKnowledgeConverter;
import br.com.videomentor.api.classe.converter.ClasseConverter;
import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.subject.converter.SubjectConverter;
import br.com.videomentor.api.videoaula.dto.VideoAulaDto;
import br.com.videomentor.api.videoaula.model.VideoAula;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * VideoAulaConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class VideoAulaConverter implements AbstractConverter<VideoAula, VideoAulaDto> {

  private ClasseConverter classeConverter;

  private SubjectConverter subjectConverter;

  public VideoAulaConverter(ClasseConverter classeConverter, SubjectConverter subjectConverter) {
    this.classeConverter = classeConverter;
    this.subjectConverter = subjectConverter;
  }

  @Override
  public VideoAula dtoToOrm(VideoAulaDto dto, VideoAula orm) {
    if (dto.getIdVideoaula() != null)
      orm.setIdVideoaula(dto.getIdVideoaula());
    orm.setIdTeacher(dto.getIdTeacher());
    orm.setVideoId(dto.getVideoId());
    orm.setVideoTitle(dto.getVideoTitle());
    orm.setVideoThumbnails(dto.getVideoThumbnails());
    orm.setVideoAuthor(dto.getVideoAuthor());
    orm.setStVideoaula(dto.getStVideoaula());
    orm.setClasses(classeConverter.dtoListToOrmList(dto.getClasses()));
    orm.setSubject(subjectConverter.dtoToOrm(dto.getSubject()));
    return orm;
  }

  @Override
  public VideoAula dtoToOrm(VideoAulaDto dto) {
    return dtoToOrm(dto, new VideoAula());
  }

  @Override
  public VideoAulaDto ormToDto(VideoAula orm, VideoAulaDto dto) {
    dto.setIdVideoaula(orm.getIdVideoaula());
    dto.setIdTeacher(orm.getIdTeacher());
    dto.setVideoId(orm.getVideoId());
    dto.setVideoTitle(orm.getVideoTitle());
    dto.setVideoThumbnails(orm.getVideoThumbnails());
    dto.setVideoAuthor(orm.getVideoAuthor());
    dto.setStVideoaula(orm.getStVideoaula());
    dto.setClasses(classeConverter.ormListToDtoList(orm.getClasses()));
    dto.setSubject(subjectConverter.ormToDto(orm.getSubject()));
    dto.setStVideoaula(orm.getStVideoaula());
    return dto;
  }

  @Override
  public VideoAulaDto ormToDto(VideoAula orm) {
    return ormToDto(orm, new VideoAulaDto());
  }

  @Override
  public List<VideoAula> dtoListToOrmList(List<VideoAulaDto> dtoList) {
    if (dtoList == null)
      return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<VideoAulaDto> ormListToDtoList(List<VideoAula> ormList) {
    if (ormList == null)
      return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
