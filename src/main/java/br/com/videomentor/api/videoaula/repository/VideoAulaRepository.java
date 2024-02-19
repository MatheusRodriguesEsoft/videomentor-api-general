package br.com.videomentor.api.videoaula.repository;

import br.com.videomentor.api.videoaula.model.VideoAula;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * VideoAulaRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface VideoAulaRepository extends JpaRepository<VideoAula, UUID> {
}
