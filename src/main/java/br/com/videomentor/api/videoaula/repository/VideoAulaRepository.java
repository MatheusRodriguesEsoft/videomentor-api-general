package br.com.videomentor.api.videoaula.repository;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.module.model.Module;
import br.com.videomentor.api.videoaula.model.VideoAula;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * VideoAulaRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface VideoAulaRepository extends JpaRepository<VideoAula, UUID> {
    List<VideoAula> findByClasses(Classe classe);

    List<VideoAula> findByModule(Module module);
}
