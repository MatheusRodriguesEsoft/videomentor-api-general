package br.com.videomentor.api.teacher.service;

import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.enumerations.RolesEnum;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.notification.orm.Notification;
import br.com.videomentor.api.notification.repository.NotificationRepository;
import br.com.videomentor.api.role.model.Role;
import br.com.videomentor.api.role.repository.RoleRepository;
import br.com.videomentor.api.teacher.converter.TeacherConverter;
import br.com.videomentor.api.teacher.dto.TeacherDto;
import br.com.videomentor.api.teacher.model.Teacher;
import br.com.videomentor.api.teacher.repository.TeacherRepository;
import br.com.videomentor.api.user.model.User;
import br.com.videomentor.api.user.repository.UserRepository;
import java.util.Collections;
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
public class TeacherService implements AbstractService<TeacherDto> {

  @Autowired
  private TeacherConverter teacherConverter;

  @Autowired
  private TeacherRepository teacherRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  NotificationRepository notificationRepository;

  @Override
  public TeacherDto create(TeacherDto teacherDto) {
    User existsUser = userRepository.findByUsername(teacherDto.getUsername());
    Role role = roleRepository.findByName(RolesEnum.TEACHER);

    if (existsUser != null) {
      throw new HandleRuntimeException("Email já está registrado");
    }
    Teacher teacher = teacherConverter.dtoToOrm(teacherDto);
    Notification notification = new Notification();
    notification.setNmNotification("Redefinição de senha");
    notification.setMessage(
      "Para redefinir sua senha utilize a senha temporária enviado para seu email."
    );
    notification.setActions(0);
    notificationRepository.save(notification);
    teacher.setRoles(Collections.singletonList(role));
    teacher.setNotifications(Collections.singletonList(notification));
    return teacherConverter.ormToDto(teacherRepository.save(teacher));
  }

  @Override
  public TeacherDto retrieveById(UUID uuid) {
    Teacher teacher = teacherRepository
      .findById(uuid)
      .orElseThrow(() -> new NotFoundException("Teacher not found"));
    return teacherConverter.ormToDto(teacher);
  }

  @Override
  public Page<TeacherDto> retrieveAll(Pageable pageable) {
    List<Teacher> teachers = teacherRepository
      .findAll()
      .stream()
      .collect(Collectors.toList());
    List<TeacherDto> teacherDtos = teachers
      .stream()
      .map(t -> teacherConverter.ormToDto(t))
      .collect(Collectors.toList());
    Page<TeacherDto> page = new PageImpl<TeacherDto>(
      teacherDtos,
      pageable,
      teachers.size()
    );
    return page;
  }

  @Override
  public TeacherDto update(TeacherDto teacherDto) {
    try {
      Teacher teacher = teacherRepository.getReferenceById(
        teacherDto.getIdUser()
      );
      teacherRepository.save(teacherConverter.dtoToOrm(teacherDto, teacher));
      return teacherConverter.ormToDto(teacher, teacherDto);
    } catch (Exception e) {
      System.out.println("ERR: " + e.getMessage());
      return null;
    }
  }

  @Override
  public void delete(UUID uuid) {
    Teacher teacher = teacherRepository
      .findById(uuid)
      .orElseThrow(() -> new NotFoundException("Teacher not found"));
    teacherRepository.deleteById(teacher.getIdUser());
  }
}
