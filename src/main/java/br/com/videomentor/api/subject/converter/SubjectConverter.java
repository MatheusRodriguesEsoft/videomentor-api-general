package br.com.videomentor.api.subject.converter;

import br.com.videomentor.api.areaofknowledge.converter.AreaOfKnowledgeConverter;
import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.subject.model.Subject;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SubjectConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class SubjectConverter implements AbstractConverter<Subject, SubjectDto> {

  @Autowired
  private AreaOfKnowledgeConverter areaOfKnowledgeConverter;

  @Override
  public Subject dtoToOrm(SubjectDto dto, Subject orm) {
    if (dto.getIdSubject() != null)
      orm.setIdSubject(dto.getIdSubject());
    orm.setNmSubject(dto.getNmSubject());
    if (dto.getAreaOfKnowledge() != null) {
      orm.setAreaOfKnowledge(areaOfKnowledgeConverter.dtoToOrm(dto.getAreaOfKnowledge()));
    }
    orm.setImageUrl(dto.getImageUrl());
    orm.setImageName(dto.getImageName());
    orm.setStSubject(dto.getStSubject());
    return orm;
  }

  @Override
  public Subject dtoToOrm(SubjectDto dto) {
    return dtoToOrm(dto, new Subject());
  }

  @Override
  public SubjectDto ormToDto(Subject orm, SubjectDto dto) {
    dto.setIdSubject(orm.getIdSubject());
    dto.setNmSubject(orm.getNmSubject());
    dto.setImageUrl(orm.getImageUrl());
    dto.setImageName(orm.getImageName());
    dto.setAreaOfKnowledge(areaOfKnowledgeConverter.ormToDto(orm.getAreaOfKnowledge()));
    dto.setStSubject(orm.getStSubject());
    return dto;
  }

  @Override
  public SubjectDto ormToDto(Subject orm) {
    return ormToDto(orm, new SubjectDto());
  }

  @Override
  public List<Subject> dtoListToOrmList(List<SubjectDto> dtoList) {
    if (dtoList == null)
      return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<SubjectDto> ormListToDtoList(List<Subject> ormList) {
    if (ormList == null)
      return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
