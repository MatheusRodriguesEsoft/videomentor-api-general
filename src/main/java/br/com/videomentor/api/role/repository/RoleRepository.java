package br.com.videomentor.api.role.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.videomentor.api.enumerations.RolesEnum;
import br.com.videomentor.api.role.model.Role;

/**
 * RoleRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface RoleRepository extends JpaRepository<Role, UUID> {

	Role findByName(RolesEnum name);
}