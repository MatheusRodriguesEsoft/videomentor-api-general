package br.com.videomentor.api.areaofknowledge.repository;

import br.com.videomentor.api.areaofknowledge.model.AreaOfKnowledge;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AreaOfKnowledgeRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface AreaOfKnowledgeRepository
  extends JpaRepository<AreaOfKnowledge, UUID> {}
