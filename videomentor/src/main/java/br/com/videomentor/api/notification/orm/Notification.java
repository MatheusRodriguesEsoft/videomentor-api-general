package br.com.videomentor.api.notification.orm;

import java.util.UUID;

import br.com.videomentor.api.enumerations.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "notifications")
@Entity(name = "Notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idNotification;

	@Column(name = "nm_notification")
	private String nmNotification;

	@Column(name = "st_notification")
	@Enumerated(EnumType.STRING)
	private StatusEnum stNotification = StatusEnum.ACTIVE;

	@Column(name = "message")
	private String message;

	@Column(name = "actions")
	private Integer actions;

	public UUID getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(UUID idNotification) {
		this.idNotification = idNotification;
	}

	public String getNmNotification() {
		return nmNotification;
	}

	public void setNmNotification(String nmNotification) {
		this.nmNotification = nmNotification;
	}

	public StatusEnum getStNotification() {
		return stNotification;
	}

	public void setStNotification(StatusEnum stNotification) {
		this.stNotification = stNotification;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public Integer getActions() {
		return actions;
	}

	public void setActions(Integer actions) {
		this.actions = actions;
	}

	public Notification() {
	}

}
