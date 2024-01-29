package br.com.videomentor.api.notification.dto;

import java.util.UUID;

import br.com.videomentor.api.enumerations.StatusEnum;

public class NotificationDto {

	private UUID idNotification;

	private String nmNotification;

	private String message;

	private Integer actions;

	private StatusEnum stNotification;

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

	public NotificationDto() {
	}

}
