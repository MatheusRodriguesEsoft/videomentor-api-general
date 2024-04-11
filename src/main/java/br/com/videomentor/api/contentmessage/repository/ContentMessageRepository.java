package br.com.videomentor.api.contentmessage.repository;

import br.com.videomentor.api.contentmessage.model.ContentMessage;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ContentMessageRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface ContentMessageRepository extends JpaRepository<ContentMessage, UUID> {
}
