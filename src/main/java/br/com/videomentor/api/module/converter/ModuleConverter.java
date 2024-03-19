package br.com.videomentor.api.module.converter;

import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.module.dto.ModuleDto;
import br.com.videomentor.api.module.model.Module;
import br.com.videomentor.api.subject.converter.SubjectConverter;
import br.com.videomentor.api.videoaula.converter.VideoAulaConverter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ModuleConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class ModuleConverter implements AbstractConverter<Module, ModuleDto> {

  @Autowired
  private SubjectConverter subjectConverter;

  @Autowired
  private VideoAulaConverter videoAulaConverter;

  @Override
  public Module dtoToOrm(ModuleDto dto, Module orm) {
    if (dto.getIdModule() != null)
      orm.setIdModule(dto.getIdModule());
    orm.setNmModule(dto.getNmModule());
    if (dto.getSubject() != null) {
      orm.setSubject(subjectConverter.dtoToOrm(dto.getSubject()));
    }
    orm.setStModule(dto.getStModule());
    return orm;
  }

  @Override
  public Module dtoToOrm(ModuleDto dto) {
    return dtoToOrm(dto, new Module());
  }

  @Override
  public ModuleDto ormToDto(Module orm, ModuleDto dto) {
    dto.setIdModule(orm.getIdModule());
    dto.setNmModule(orm.getNmModule());
    dto.setSubject(subjectConverter.ormToDto(orm.getSubject()));
    dto.setStModule(orm.getStModule());
    return dto;
  }

  @Override
  public ModuleDto ormToDto(Module orm) {
    return ormToDto(orm, new ModuleDto());
  }

  @Override
  public List<Module> dtoListToOrmList(List<ModuleDto> dtoList) {
    if (dtoList == null)
      return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<ModuleDto> ormListToDtoList(List<Module> ormList) {
    if (ormList == null)
      return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
