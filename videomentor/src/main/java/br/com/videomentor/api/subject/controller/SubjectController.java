package br.com.videomentor.api.subject.controller;

import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.subject.service.SubjectService;
import jakarta.validation.Valid;
import java.net.URI;
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
@RequestMapping("/subjects")
public class SubjectController implements AbstractController<SubjectDto> {

  @Autowired
  SubjectService subjectService;

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<SubjectDto> create(
    @RequestBody @Valid SubjectDto subjectDto,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    SubjectDto subject = subjectService.create(subjectDto);
    URI uri = uriComponentsBuilder
      .path("/subjects/{id}")
      .buildAndExpand(subject.getIdSubject())
      .toUri();
    return ResponseEntity.created(uri).body(subject);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<SubjectDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(subjectService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<SubjectDto>> retrieveAll(
    @PageableDefault(size = 25, sort = { "nmSubject" }) Pageable pageable
  ) {
    return ResponseEntity.ok(subjectService.retrieveAll(pageable));
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<SubjectDto> update(@RequestBody SubjectDto subjectDto) {
    return ResponseEntity.ok(subjectService.update(subjectDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<SubjectDto> delete(@PathVariable String uuid) {
    subjectService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }
}
