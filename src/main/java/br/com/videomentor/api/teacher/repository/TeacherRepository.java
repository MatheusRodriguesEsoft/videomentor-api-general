package br.com.videomentor.api.teacher.repository;

import br.com.videomentor.api.teacher.model.Teacher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TeacherRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {}
