package br.com.videomentor.api.student.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.videomentor.api.classe.converter.ClasseConverter;
import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.notification.converter.NotificationConverter;
import br.com.videomentor.api.role.converter.RoleConverter;
import br.com.videomentor.api.student.dto.StudentDto;
import br.com.videomentor.api.student.model.Student;

/**
 * StudentConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */
@Component
public class StudentConverter implements AbstractConverter<Student, StudentDto> {

  @Autowired
  private ClasseConverter classeConverter;

  @Autowired
  private RoleConverter roleConverter;

  @Autowired
  private NotificationConverter notificationConverter;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Student dtoToOrm(StudentDto dto, Student orm) {
    if (dto.getIdUser() != null) {
      orm.setIdUser(dto.getIdUser());
    }
    orm.setNmUser(dto.getNmUser());
    orm.setUsername(dto.getUsername());
    if (dto.getPassword() != null) {
      orm.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
    if (dto.getClasse() != null) {
      orm.setClasse(classeConverter.dtoToOrm(dto.getClasse()));
    } else {
      orm.setClasse(null);
    }
    orm.setTemporaryPassword(dto.getTemporaryPassword());
    orm.setStUser(dto.getStUser());
    return orm;
  }

  @Override
  public Student dtoToOrm(StudentDto dto) {
    return dtoToOrm(dto, new Student());
  }

  @Override
  public StudentDto ormToDto(Student orm, StudentDto dto) {
    dto.setIdUser(orm.getIdUser());
    dto.setNmUser(orm.getNmUser());
    dto.setUsername(orm.getUsername());
    dto.setStUser(orm.getStUser());
    dto.setRoles(roleConverter.ormListToDtoList(orm.getRoles()));
    dto.setNotifications(notificationConverter.ormListToDtoList(orm.getNotifications()));
    if (orm.getClasse() != null) {
      dto.setIdClasse(orm.getClasse().getIdClasse());
    }
    if (dto.getPassword() != null) {
      orm.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
    dto.setTemporaryPassword(orm.getTemporaryPassword());
    dto.setCreatedDate(orm.getCreatedDate());
    return dto;
  }

  @Override
  public StudentDto ormToDto(Student orm) {
    return ormToDto(orm, new StudentDto());
  }

  @Override
  public List<Student> dtoListToOrmList(List<StudentDto> dtoList) {
    if (dtoList == null)
      return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<StudentDto> ormListToDtoList(List<Student> ormList) {
    if (ormList == null)
      return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
