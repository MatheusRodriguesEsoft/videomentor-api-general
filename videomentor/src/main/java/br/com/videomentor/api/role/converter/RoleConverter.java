package br.com.videomentor.api.role.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.videomentor.api.role.dto.RoleDto;
import br.com.videomentor.api.role.model.Role;
import br.com.videomentor.api.commons.AbstractConverter;

/**
 * RoleConverter.
 *
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com>
 * @version 1.0
 */
@Component
public class RoleConverter implements AbstractConverter<Role, RoleDto> {

	@Override
	public Role dtoToOrm(RoleDto dto, Role orm) {
		if(dto.getIdRole() != null)
			orm.setId(dto.getIdRole());
		orm.setName(dto.getNmRole());
		orm.setStatus(dto.getStRole());
		return orm;
	}

	@Override
	public Role dtoToOrm(RoleDto dto) {
		return dtoToOrm(dto, new Role());
	}

	@Override
	public RoleDto ormToDto(Role orm, RoleDto dto) {
		dto.setIdRole(orm.getId());
		dto.setNmRole(orm.getName());
		dto.setStRole(orm.getStatus());
		return dto;
	}

	@Override
	public RoleDto ormToDto(Role orm) {
		return ormToDto(orm, new RoleDto());
	}

	@Override
	public List<Role> dtoListToOrmList(List<RoleDto> dtoList) {
		if (dtoList == null)
			return null;
		return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
	}

	@Override
	public List<RoleDto> ormListToDtoList(List<Role> ormList) {
		if (ormList == null)
			return null;
		return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
	}

}