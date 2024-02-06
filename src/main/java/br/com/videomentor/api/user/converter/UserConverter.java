package br.com.videomentor.api.user.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.notification.converter.NotificationConverter;
import br.com.videomentor.api.role.converter.RoleConverter;
import br.com.videomentor.api.user.dto.UserDto;
import br.com.videomentor.api.user.model.User;

/**
 * UserConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Component
public class UserConverter implements AbstractConverter<User, UserDto> {

  @Autowired
  private RoleConverter roleConverter;

  @Autowired
  private NotificationConverter notificationConverter;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User dtoToOrm(UserDto dto, User orm) {
    if (dto.getIdUser() != null) {
      orm.setIdUser(dto.getIdUser());
    }
    orm.setNmUser(dto.getNmUser());
    orm.setUsername(dto.getUsername());
    orm.setImageUrl(dto.getImageUrl());
    orm.setImageName(dto.getImageName());
    if (dto.getPassword() != null) {
      orm.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
    orm.setTemporaryPassword(dto.getTemporaryPassword());
    return orm;
  }

  @Override
  public User dtoToOrm(UserDto dto) {
    return dtoToOrm(dto, new User());
  }

  @Override
  public UserDto ormToDto(User orm, UserDto dto) {
    dto.setIdUser(orm.getIdUser());
    dto.setNmUser(orm.getNmUser());
    dto.setUsername(orm.getUsername());
    dto.setStUser(orm.getStUser());
    dto.setImageUrl(orm.getImageUrl());
    dto.setImageName(orm.getImageName());
    dto.setRoles(roleConverter.ormListToDtoList(orm.getRoles()));
    dto.setNotifications(
      notificationConverter.ormListToDtoList(orm.getNotifications())
    );
    dto.setCreatedDate(orm.getCreatedDate());
    return dto;
  }

  @Override
  public UserDto ormToDto(User orm) {
    return ormToDto(orm, new UserDto());
  }

  @Override
  public List<User> dtoListToOrmList(List<UserDto> dtoList) {
    if (dtoList == null) return null;
    return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
  }

  @Override
  public List<UserDto> ormListToDtoList(List<User> ormList) {
    if (ormList == null) return null;
    return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
  }
}
