package br.com.videomentor.api.classe.repository;

import br.com.videomentor.api.classe.model.Classe;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClasseRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface ClasseRepository extends JpaRepository<Classe, UUID> {}
