package br.com.videomentor.api.student.controller;

import br.com.videomentor.api.classe.converter.ClasseConverter;
import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.classe.service.ClasseService;
import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.student.dto.StudentDto;
import br.com.videomentor.api.student.service.StudentService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/students")
public class StudentController implements AbstractController<StudentDto> {

  @Autowired
  private StudentService studentService;

  @Autowired
  private ClasseService classeService;

  @Autowired
  private ClasseConverter classeConverter;

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<StudentDto> create(@RequestBody @Valid StudentDto studentDto,
      UriComponentsBuilder uriComponentsBuilder) {
    StudentDto student = studentService.create(studentDto);
    URI uri = uriComponentsBuilder.path("/students/{id}").buildAndExpand(student.getIdUser()).toUri();
    return ResponseEntity.created(uri).body(student);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<StudentDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(studentService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<StudentDto>> retrieveAll(
      @PageableDefault(size = 25, sort = { "nmStudent" }) Pageable pageable) {
    return ResponseEntity.ok(studentService.retrieveAll(pageable));
  }

  @GetMapping("/classe/{idClasse}")
  public ResponseEntity<List<StudentDto>> retrieveByClasse(@PathVariable UUID idClasse) {
    ClasseDto classe = classeService.retrieveById(idClasse);
    if (classe == null) {
      return ResponseEntity.notFound().build();
    }
    List<StudentDto> students = studentService.retrieveByClasse(classeConverter.dtoToOrm(classe));
    return ResponseEntity.ok(students);
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<StudentDto> update(@RequestBody StudentDto studentDto) {
    return ResponseEntity.ok(studentService.update(studentDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<StudentDto> delete(@PathVariable String uuid) {
    studentService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/count")
  public ResponseEntity<Long> countAllStudents() {
    long totalStudents = studentService.countAll();
    return ResponseEntity.ok(totalStudents);
  }
  
}
