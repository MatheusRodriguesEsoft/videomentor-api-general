package br.com.videomentor.api.subject.repository;

import br.com.videomentor.api.subject.model.Subject;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SubjectRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface SubjectRepository extends JpaRepository<Subject, UUID> {}
