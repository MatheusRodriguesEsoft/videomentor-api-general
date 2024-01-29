package br.com.videomentor.api.areaofknowledge.converter;

import br.com.videomentor.api.areaofknowledge.dto.AreaOfKnowledgeDto;
import br.com.videomentor.api.areaofknowledge.model.AreaOfKnowledge;
import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.subject.converter.SubjectConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AreaOfKnowledgeConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class AreaOfKnowledgeConverter
  implements AbstractConverter<AreaOfKnowledge, AreaOfKnowledgeDto> {

  @Autowired
  private SubjectConverter subjectConverter;

  @Override
  public AreaOfKnowledge dtoToOrm(AreaOfKnowledgeDto dto, AreaOfKnowledge orm) {
    if (dto.getIdAreaOfKnowledge() != null) orm.setIdAreaOfKnowledge(
      dto.getIdAreaOfKnowledge()
    );
    orm.setNmAreaOfKnowledge(dto.getNmAreaOfKnowledge());
    orm.setSubjects(subjectConverter.dtoListToOrmList(dto.getSubjects()));
    return orm;
  }

  @Override
  public AreaOfKnowledge dtoToOrm(AreaOfKnowledgeDto dto) {
    return dtoToOrm(dto, new AreaOfKnowledge());
  }

  @Override
  public AreaOfKnowledgeDto ormToDto(
    AreaOfKnowledge orm,
    AreaOfKnowledgeDto dto
  ) {
    dto.setIdAreaOfKnowledge(orm.getIdAreaOfKnowledge());
    dto.setNmAreaOfKnowledge(orm.getNmAreaOfKnowledge());
    return dto;
  }

  @Override
  public AreaOfKnowledgeDto ormToDto(AreaOfKnowledge orm) {
    return ormToDto(orm, new AreaOfKnowledgeDto());
  }

  @Override
  public List<AreaOfKnowledge> dtoListToOrmList(
    List<AreaOfKnowledgeDto> dtoList
  ) {
    if (dtoList == null) return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<AreaOfKnowledgeDto> ormListToDtoList(
    List<AreaOfKnowledge> ormList
  ) {
    if (ormList == null) return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
