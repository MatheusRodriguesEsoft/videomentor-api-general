package br.com.videomentor.api.notification.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.videomentor.api.commons.AbstractConverter;
import br.com.videomentor.api.notification.dto.NotificationDto;
import br.com.videomentor.api.notification.model.Notification;

@Component
public class NotificationConverter implements AbstractConverter<Notification, NotificationDto> {

	@Override
	public Notification dtoToOrm(NotificationDto dto, Notification orm) {
		if (dto.getIdNotification() != null) 
			orm.setIdNotification(dto.getIdNotification());
		orm.setNmNotification(dto.getNmNotification());
		orm.setStNotification(dto.getStNotification());
		orm.setMessage(dto.getMessage());
		orm.setActions(dto.getActions());
		return orm;
	}

	@Override
	public Notification dtoToOrm(NotificationDto dto) {
		return dtoToOrm(dto, new Notification());
	}

	@Override
	public NotificationDto ormToDto(Notification orm, NotificationDto dto) {
		dto.setIdNotification(orm.getIdNotification());
		dto.setNmNotification(orm.getNmNotification());
		dto.setMessage(orm.getMessage());
		dto.setActions(orm.getActions());
		dto.setStNotification(orm.getStNotification());
		return dto;
	}

	@Override
	public NotificationDto ormToDto(Notification orm) {
		return ormToDto(orm, new NotificationDto());
	}

	@Override
	public List<Notification> dtoListToOrmList(List<NotificationDto> dtoList) {
		if (dtoList == null) return null;
		return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
	}

	@Override
	public List<NotificationDto> ormListToDtoList(List<Notification> entityList) {
		if (entityList == null) return null;
		return entityList.stream().map(this::ormToDto).collect(Collectors.toList());
	}
}
