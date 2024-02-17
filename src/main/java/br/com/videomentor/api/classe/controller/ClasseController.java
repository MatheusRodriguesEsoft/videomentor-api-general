package br.com.videomentor.api.classe.controller;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.classe.service.ClasseService;
import br.com.videomentor.api.commons.AbstractController;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/classes")
public class ClasseController implements AbstractController<ClasseDto> {

  private ClasseService classeService;

  public ClasseController(ClasseService classeService) {
    this.classeService = classeService;
  }

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<ClasseDto> create(@RequestBody @Valid ClasseDto classeDto,UriComponentsBuilder uriComponentsBuilder) {
    ClasseDto classe = classeService.create(classeDto);
    URI uri = uriComponentsBuilder.path("/classes/{id}").buildAndExpand(classe.getIdClasse()).toUri();
    return ResponseEntity.created(uri).body(classe);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<ClasseDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(classeService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<ClasseDto>> retrieveAll(
    @PageableDefault(size = 25, sort = { "nmClasse" }) Pageable pageable) {
    return ResponseEntity.ok(classeService.retrieveAll(pageable));
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<ClasseDto> update(@RequestBody ClasseDto classeDto) {
    return ResponseEntity.ok(classeService.update(classeDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<ClasseDto> delete(@PathVariable String uuid) {
    classeService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }
}
