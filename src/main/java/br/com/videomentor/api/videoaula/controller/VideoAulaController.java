package br.com.videomentor.api.videoaula.controller;

import br.com.videomentor.api.classe.converter.ClasseConverter;
import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.classe.service.ClasseService;
import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.module.converter.ModuleConverter;
import br.com.videomentor.api.module.dto.ModuleDto;
import br.com.videomentor.api.module.service.ModuleService;
import br.com.videomentor.api.videoaula.dto.VideoAulaDto;
import br.com.videomentor.api.videoaula.service.VideoAulaService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/videoaulas")
public class VideoAulaController implements AbstractController<VideoAulaDto> {

  private VideoAulaService videoAulaService;

  private ClasseService classeService;

  private ModuleService moduleService;

  private ClasseConverter classeConverter;

  private ModuleConverter moduleConverter;

  public VideoAulaController(VideoAulaService videoAulaService, ClasseService classeService,
      ModuleService moduleService, ClasseConverter classeConverter, ModuleConverter moduleConverter) {
    this.classeService = classeService;
    this.videoAulaService = videoAulaService;
    this.moduleService = moduleService;
    this.classeConverter = classeConverter;
    this.moduleConverter = moduleConverter;
  }

  /**
   * @param videoAulaDto
   * @param uriComponentsBuilder
   * @return ResponseEntity<VideoAulaDto>
   */
  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<VideoAulaDto> create(@RequestBody @Valid VideoAulaDto videoAulaDto,
      UriComponentsBuilder uriComponentsBuilder) {
    VideoAulaDto videoAula = videoAulaService.create(videoAulaDto);
    URI uri = uriComponentsBuilder.path("/videoaulas/{id}").buildAndExpand(videoAula.getIdVideoaula()).toUri();
    return ResponseEntity.created(uri).body(videoAula);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<VideoAulaDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(videoAulaService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<VideoAulaDto>> retrieveAll(
      @PageableDefault(size = 25, sort = { "videoTitle" }) Pageable pageable) {
    return ResponseEntity.ok(videoAulaService.retrieveAll(pageable));
  }

  @GetMapping("/classe/{idClasse}")
  public ResponseEntity<List<VideoAulaDto>> retrieveByClasse(@PathVariable UUID idClasse) {
    ClasseDto classe = classeService.retrieveById(idClasse);
    if (classe == null) {
      return ResponseEntity.notFound().build();
    }
    List<VideoAulaDto> videoAulas = videoAulaService.retrieveByClasse(classeConverter.dtoToOrm(classe));
    return ResponseEntity.ok(videoAulas);
  }

  @GetMapping("/module/{idModule}")
  public ResponseEntity<List<VideoAulaDto>> retrieveByModule(@PathVariable UUID idModule) {
    ModuleDto module = moduleService.retrieveById(idModule);
    if (module == null) {
      return ResponseEntity.notFound().build();
    }
    List<VideoAulaDto> videoAulas = videoAulaService.retrieveByModule(moduleConverter.dtoToOrm(module));
    return ResponseEntity.ok(videoAulas);
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<VideoAulaDto> update(@RequestBody VideoAulaDto videoAulaDto) {
    return ResponseEntity.ok(videoAulaService.update(videoAulaDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<VideoAulaDto> delete(@PathVariable String uuid) {
    videoAulaService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/count")
  public ResponseEntity<Long> countAllVideoAulas() {
    long totalVideoAulas = videoAulaService.countAll();
    return ResponseEntity.ok(totalVideoAulas);
  }
  
}
