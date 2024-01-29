package br.com.videomentor.api.serie.repository;

import br.com.videomentor.api.serie.model.Serie;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SerieRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface SerieRepository extends JpaRepository<Serie, UUID> {}
