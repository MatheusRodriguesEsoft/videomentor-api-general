package br.com.videomentor.api.student.repository;

import br.com.videomentor.api.student.model.Student;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.videomentor.api.classe.model.Classe;


/**
 * StudentRepository.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByClasse(Classe classe);
}
