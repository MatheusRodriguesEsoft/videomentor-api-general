package br.com.videomentor.api.notification.service;

import br.com.videomentor.api.commons.AbstractService;
import br.com.videomentor.api.exceptions.NotFoundException;
import br.com.videomentor.api.notification.converter.NotificationConverter;
import br.com.videomentor.api.notification.dto.NotificationDto;
import br.com.videomentor.api.notification.orm.Notification;
import br.com.videomentor.api.notification.repository.NotificationRepository;
import br.com.videomentor.api.user.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements AbstractService<NotificationDto> {

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private NotificationConverter notificationConverter;

  @Autowired
  UserRepository userRepository;

  public NotificationDto create(NotificationDto roleDto) {
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public NotificationDto retrieveById(UUID uuid) {
    Notification role = notificationRepository
      .findById(uuid)
      .orElseThrow(() -> new NotFoundException("Notification not found."));
    return notificationConverter.ormToDto(role);
  }

  @Override
  public Page<NotificationDto> retrieveAll(Pageable pageable) {
    List<Notification> notifications = notificationRepository
      .findAll()
      .stream()
      .collect(Collectors.toList());
    List<NotificationDto> notificationDtos = notifications
      .stream()
      .map(a -> notificationConverter.ormToDto(a))
      .collect(Collectors.toList());
    Page<NotificationDto> page = new PageImpl<NotificationDto>(
      notificationDtos,
      pageable,
      notificationDtos.size()
    );
    return page;
  }

  @Override
  public NotificationDto update(NotificationDto dto) {
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(UUID uuid) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }
}
