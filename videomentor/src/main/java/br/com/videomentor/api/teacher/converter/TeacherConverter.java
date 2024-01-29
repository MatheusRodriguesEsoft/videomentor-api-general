package br.com.videomentor.api.teacher.converter;

import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.notification.converter.NotificationConverter;
import br.com.videomentor.api.role.converter.RoleConverter;
import br.com.videomentor.api.subject.converter.SubjectConverter;
import br.com.videomentor.api.teacher.dto.TeacherDto;
import br.com.videomentor.api.teacher.model.Teacher;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * TeacherConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class TeacherConverter
  implements AbstractConverter<Teacher, TeacherDto> {

  @Autowired
  private RoleConverter roleConverter;

  @Autowired
  private SubjectConverter subjectConverter;

  @Autowired
  private NotificationConverter notificationConverter;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Teacher dtoToOrm(TeacherDto dto, Teacher orm) {
    if (dto.getIdUser() != null) {
      orm.setIdUser(dto.getIdUser());
    }
    orm.setNmUser(dto.getNmUser());
    orm.setUsername(dto.getUsername());
    if (dto.getPassword() != null) {
      orm.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
    orm.setSubjects(subjectConverter.dtoListToOrmList(dto.getSubjects()));
    orm.setTemporaryPassword(dto.getTemporaryPassword());
    orm.setStUser(dto.getStUser());
    return orm;
  }

  @Override
  public Teacher dtoToOrm(TeacherDto dto) {
    return dtoToOrm(dto, new Teacher());
  }

  @Override
  public TeacherDto ormToDto(Teacher orm, TeacherDto dto) {
    dto.setIdUser(orm.getIdUser());
    dto.setNmUser(orm.getNmUser());
    dto.setUsername(orm.getUsername());
    dto.setStUser(orm.getStUser());
    dto.setRoles(roleConverter.ormListToDtoList(orm.getRoles()));
    dto.setNotifications(
      notificationConverter.ormListToDtoList(orm.getNotifications())
    );
    dto.setSubjects(subjectConverter.ormListToDtoList(orm.getSubjects()));
    dto.setTemporaryPassword(orm.getTemporaryPassword());
    dto.setCreatedDate(orm.getCreatedDate());
    return dto;
  }

  @Override
  public TeacherDto ormToDto(Teacher orm) {
    return ormToDto(orm, new TeacherDto());
  }

  @Override
  public List<Teacher> dtoListToOrmList(List<TeacherDto> dtoList) {
    if (dtoList == null) return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<TeacherDto> ormListToDtoList(List<Teacher> ormList) {
    if (ormList == null) return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
