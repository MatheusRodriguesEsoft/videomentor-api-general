package br.com.videomentor.api.teacher.repository;

import br.com.videomentor.api.teacher.model.Teacher;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * TeacherRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    @Query("SELECT t FROM Teacher t JOIN t.subjects s WHERE s.id = :subjectId")
    List<Teacher> findBySubjectsId(UUID subjectId);
}
