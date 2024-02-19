package br.com.videomentor.api.videoaula.controller;

import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.videoaula.dto.VideoAulaDto;
import br.com.videomentor.api.videoaula.service.VideoAulaService;
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
@RequestMapping("/videoaulas")
public class VideoAulaController implements AbstractController<VideoAulaDto> {

  @Autowired
  VideoAulaService videoAulaService;

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
}
