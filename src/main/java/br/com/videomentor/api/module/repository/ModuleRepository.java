package br.com.videomentor.api.module.repository;

import br.com.videomentor.api.module.model.Module;
import br.com.videomentor.api.subject.model.Subject;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ModuleRepository.
 *
 * @author Matheus Rodrigues <matheus.rodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface ModuleRepository extends JpaRepository<Module, UUID> {
    List<Module> findBySubject(Subject subject);
}
