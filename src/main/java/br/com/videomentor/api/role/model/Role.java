package br.com.videomentor.api.role.model;

import java.util.UUID;

import br.com.videomentor.api.enumerations.RolesEnum;
import br.com.videomentor.api.enumerations.StatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Role.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "roles")
@Entity(name = "Role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Enumerated(EnumType.STRING)
	private RolesEnum name;

	private StatusEnum status = StatusEnum.ACTIVE;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public RolesEnum getName() {
		return name;
	}

	public void setName(RolesEnum name) {
		this.name = name;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Role(UUID id, RolesEnum name, StatusEnum status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public Role() {
	}

}