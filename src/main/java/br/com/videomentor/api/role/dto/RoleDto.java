package br.com.videomentor.api.role.dto;

import java.util.UUID;

import br.com.videomentor.api.enumerations.RolesEnum;
import br.com.videomentor.api.enumerations.StatusEnum;

/**
 * RoleDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class RoleDto {
    
	private UUID idRole;

	private RolesEnum nmRole;

	private StatusEnum stRole;

	public UUID getIdRole() {
		return idRole;
	}

	public void setIdRole(UUID idRole) {
		this.idRole = idRole;
	}

	public RolesEnum getNmRole() {
		return nmRole;
	}

	public void setNmRole(RolesEnum nmRole) {
		this.nmRole = nmRole;
	}

	public StatusEnum getStRole() {
		return stRole;
	}

	public void setStRole(StatusEnum stRole) {
		this.stRole = stRole;
	}

	public RoleDto(UUID idRole, RolesEnum nmRole, StatusEnum stRole) {
		this.idRole = idRole;
		this.nmRole = nmRole;
		this.stRole = stRole;
	}

	public RoleDto() {
	}

	
}