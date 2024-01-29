package br.com.videomentor.api.user.repository;

import br.com.videomentor.api.user.model.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, UUID> {
  User findByUsername(String username);
  List<User> findByNmUserContainingIgnoreCase(String nmUser);
}
