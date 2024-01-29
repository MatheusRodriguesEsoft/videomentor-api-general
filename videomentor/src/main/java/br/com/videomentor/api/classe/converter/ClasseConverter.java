package br.com.videomentor.api.classe.converter;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.serie.converter.SerieConverter;
import br.com.videomentor.api.student.converter.StudentConverter;
import br.com.videomentor.api.teacher.converter.TeacherConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClasseConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class ClasseConverter implements AbstractConverter<Classe, ClasseDto> {

  @Autowired
  private SerieConverter serieConverter;

  @Autowired
  private TeacherConverter teacherConverter;

  @Autowired
  private StudentConverter studentConverter;

  @Override
  public Classe dtoToOrm(ClasseDto dto, Classe orm) {
    if (dto.getIdClasse() != null) orm.setIdClasse(dto.getIdClasse());
    orm.setNmClasse(dto.getNmClasse());
    if (dto.getSerie() != null) {
      orm.setSerie(serieConverter.dtoToOrm(dto.getSerie()));
    }
    orm.setTeachers(teacherConverter.dtoListToOrmList(dto.getTeachers()));
    orm.setStClasse(dto.getStClasse());
    return orm;
  }

  @Override
  public Classe dtoToOrm(ClasseDto dto) {
    return dtoToOrm(dto, new Classe());
  }

  @Override
  public ClasseDto ormToDto(Classe orm, ClasseDto dto) {
    dto.setIdClasse(orm.getIdClasse());
    dto.setNmClasse(orm.getNmClasse());
    dto.setSerie(serieConverter.ormToDto(orm.getSerie()));
    dto.setTeachers(teacherConverter.ormListToDtoList(orm.getTeachers()));
    dto.setStudents(studentConverter.ormListToDtoList(orm.getStudents()));
    dto.setStClasse(orm.getStClasse());
    return dto;
  }

  @Override
  public ClasseDto ormToDto(Classe orm) {
    return ormToDto(orm, new ClasseDto());
  }

  @Override
  public List<Classe> dtoListToOrmList(List<ClasseDto> dtoList) {
    if (dtoList == null) return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<ClasseDto> ormListToDtoList(List<Classe> ormList) {
    if (ormList == null) return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
