package br.com.videomentor.api.comment.controller;

import br.com.videomentor.api.comment.dto.CommentDto;
import br.com.videomentor.api.comment.service.CommentService;
import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.videoaula.converter.VideoAulaConverter;
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
@RequestMapping("/comments")
public class CommentController implements AbstractController<CommentDto> {

  private CommentService commentService;

  private VideoAulaService videoAulaService;

  private VideoAulaConverter videoAulaConverter;

  public CommentController(CommentService commentService, VideoAulaService videoAulaService,
      VideoAulaConverter videoAulaConverter) {
    this.commentService = commentService;
    this.videoAulaService = videoAulaService;
    this.videoAulaConverter = videoAulaConverter;
  }

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<CommentDto> create(@RequestBody @Valid CommentDto commentDto,
      UriComponentsBuilder uriComponentsBuilder) {
    CommentDto comment = commentService.create(commentDto);
    URI uri = uriComponentsBuilder.path("/comments/{id}").buildAndExpand(comment.getIdComment()).toUri();
    return ResponseEntity.created(uri).body(comment);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<CommentDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(commentService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<CommentDto>> retrieveAll(
      @PageableDefault(size = 25, sort = { "nmComment" }) Pageable pageable) {
    return ResponseEntity.ok(commentService.retrieveAll(pageable));
  }

  @GetMapping("/videoaula/{idVideoaula}")
  public ResponseEntity<List<CommentDto>> retrieveVideoAula(@PathVariable UUID idVideoaula) {
    VideoAulaDto videoAula = videoAulaService.retrieveById(idVideoaula);
    if (videoAula == null) {
      return ResponseEntity.notFound().build();
    }
    List<CommentDto> comments = commentService.retrieveByVideoAula(videoAulaConverter.dtoToOrm(videoAula));
    return ResponseEntity.ok(comments);
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<CommentDto> update(@RequestBody CommentDto commentDto) {
    return ResponseEntity.ok(commentService.update(commentDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<CommentDto> delete(@PathVariable String uuid) {
    commentService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }
}
