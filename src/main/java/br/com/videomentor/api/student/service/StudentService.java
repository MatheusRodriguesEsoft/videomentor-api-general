package br.com.videomentor.api.student.service;

import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.enumerations.RolesEnum;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.exceptions.NotFoundException;
import br.com.videomentor.api.notification.model.Notification;
import br.com.videomentor.api.notification.repository.NotificationRepository;
import br.com.videomentor.api.role.model.Role;
import br.com.videomentor.api.role.repository.RoleRepository;
import br.com.videomentor.api.student.converter.StudentConverter;
import br.com.videomentor.api.student.dto.StudentDto;
import br.com.videomentor.api.student.model.Student;
import br.com.videomentor.api.student.repository.StudentRepository;
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

@Service
public class StudentService implements AbstractService<StudentDto> {

  /**
   *
   */
  @Autowired
  private StudentConverter studentConverter;

  /**
   *
   */
  @Autowired
  private StudentRepository studentRepository;

  /**
   *
   */
  @Autowired
  private UserRepository userRepository;

  /**
   *
   */
  @Autowired
  private RoleRepository roleRepository;

  /**
   *
   */
  @Autowired
  private NotificationRepository notificationRepository;

  /**
   * @param studentDto
   * @return StudentDto
   */
  @Override
  public StudentDto create(final StudentDto studentDto) {
    User existsUser = userRepository.findByUsername(studentDto.getUsername());
    Role role = roleRepository.findByName(RolesEnum.STUDENT);

    if (existsUser != null) {
      throw new HandleRuntimeException("Email já está registrado");
    }
    Student student = studentConverter.dtoToOrm(studentDto);
    Notification notification = new Notification();
    notification.setNmNotification("Redefinição de senha");
    notification.setMessage("Para redefinir sua senha utilize a senha temporária enviado para seu email.");
    notification.setActions(0);
    notificationRepository.save(notification);
    student.setRoles(Collections.singletonList(role));
    student.setNotifications(Collections.singletonList(notification));
    return studentConverter.ormToDto(studentRepository.save(student));
  }

  /**
   * @param uuid
   * @return StudentDto
   */
  @Override
  public StudentDto retrieveById(final UUID uuid) {
    Student student = studentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Student not found"));
    return studentConverter.ormToDto(student);
  }

  /**
   * @param pageable
   * @return Page<StudentDto>
   */
  @Override
  public Page<StudentDto> retrieveAll(final Pageable pageable) {
    List<Student> students = studentRepository.findAll().stream().collect(Collectors.toList());
    List<StudentDto> studentDtos = students.stream().map(s -> studentConverter.ormToDto(s))
        .collect(Collectors.toList());
    Page<StudentDto> page = new PageImpl<StudentDto>(studentDtos, pageable, students.size());
    return page;
  }

  public List<StudentDto> retrieveByClasse(Classe classe) {
    List<Student> students = studentRepository.findByClasse(classe);
    return students.stream().map(studentConverter::ormToDto).collect(Collectors.toList());
  }

  @Override
  public final StudentDto update(final StudentDto studentDto) {
    try {
      Student student = studentRepository.getReferenceById(studentDto.getIdUser());
      studentRepository.save(studentConverter.dtoToOrm(studentDto, student));
      return studentConverter.ormToDto(student, studentDto);
    } catch (Exception e) {
      System.out.println("ERR: " + e.getMessage());
      return null;
    }
  }

  @Override
  public final void delete(final UUID uuid) {
    Student student = studentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Student not found"));
    studentRepository.deleteById(student.getIdUser());
  }

  public long countAll() {
    return studentRepository.count();
  }
}
