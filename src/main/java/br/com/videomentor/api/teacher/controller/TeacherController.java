package br.com.videomentor.api.teacher.controller;

import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.teacher.dto.TeacherDto;
import br.com.videomentor.api.teacher.service.TeacherService;
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
@RequestMapping("/teachers")
public class TeacherController implements AbstractController<TeacherDto> {

  @Autowired
  TeacherService teacherService;

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<TeacherDto> create(@RequestBody @Valid TeacherDto teacherDto,
      UriComponentsBuilder uriComponentsBuilder) {
    TeacherDto teacher = teacherService.create(teacherDto);
    URI uri = uriComponentsBuilder.path("/teachers/{id}").buildAndExpand(teacher.getIdUser()).toUri();
    return ResponseEntity.created(uri).body(teacher);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<TeacherDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(teacherService.retrieveById(uuid));
  }

  @GetMapping("/subject/{uuid}")
  public ResponseEntity<List<TeacherDto>> retrieveBySubjectsId(@PathVariable UUID uuid) {
    return ResponseEntity.ok(teacherService.findBySubjectId(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<TeacherDto>> retrieveAll(
      @PageableDefault(size = 25, sort = { "nmTeacher" }) Pageable pageable) {
    return ResponseEntity.ok(teacherService.retrieveAll(pageable));
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<TeacherDto> update(@RequestBody TeacherDto teacherDto) {
    return ResponseEntity.ok(teacherService.update(teacherDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<TeacherDto> delete(@PathVariable String uuid) {
    teacherService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/count")
  public ResponseEntity<Long> countAllTeachers() {
    long totalTeachers = teacherService.countAll();
    return ResponseEntity.ok(totalTeachers);
  }
}
