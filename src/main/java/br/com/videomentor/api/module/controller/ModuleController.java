package br.com.videomentor.api.module.controller;

import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.module.dto.ModuleDto;
import br.com.videomentor.api.module.service.ModuleService;
import br.com.videomentor.api.subject.converter.SubjectConverter;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.subject.service.SubjectService;
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
@RequestMapping("/modules")
public class ModuleController implements AbstractController<ModuleDto> {

  @Autowired
  private ModuleService moduleService;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private SubjectConverter subjectConverter;

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<ModuleDto> create(@RequestBody @Valid ModuleDto moduleDto,
      UriComponentsBuilder uriComponentsBuilder) {
    ModuleDto module = moduleService.create(moduleDto);
    URI uri = uriComponentsBuilder.path("/modules/{id}").buildAndExpand(module.getIdModule()).toUri();
    return ResponseEntity.created(uri).body(module);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<ModuleDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(moduleService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<ModuleDto>> retrieveAll(
      @PageableDefault(size = 25, sort = { "nmModule" }) Pageable pageable) {
    return ResponseEntity.ok(moduleService.retrieveAll(pageable));
  }

  @GetMapping("/subject/{idSubject}")
  public ResponseEntity<List<ModuleDto>> retrieveBySubject(@PathVariable UUID idSubject) {
    SubjectDto subject = subjectService.retrieveById(idSubject);
    if (subject == null) {
      return ResponseEntity.notFound().build();
    }

    List<ModuleDto> modules = moduleService.retrieveBySubject(subjectConverter.dtoToOrm(subject));
    return ResponseEntity.ok(modules);
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<ModuleDto> update(@RequestBody ModuleDto moduleDto) {
    return ResponseEntity.ok(moduleService.update(moduleDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<ModuleDto> delete(@PathVariable String uuid) {
    moduleService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }
}
