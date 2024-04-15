package br.com.videomentor.api.notification.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.videomentor.api.notification.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

	Notification findByNmNotification(String nmNotification);
}
