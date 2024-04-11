package br.com.videomentor.api.comment.repository;

import br.com.videomentor.api.comment.model.Comment;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import br.com.videomentor.api.videoaula.model.VideoAula;

/**
 * CommentRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByVideoAula(VideoAula videoAula);
}
