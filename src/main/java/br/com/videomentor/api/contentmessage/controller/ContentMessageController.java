package br.com.videomentor.api.contentmessage.controller;

import br.com.videomentor.api.contentmessage.dto.ContentMessageDto;
import br.com.videomentor.api.contentmessage.service.ContentMessageService;
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
@RequestMapping("/content-messages")
public class ContentMessageController implements AbstractController<ContentMessageDto> {

  private ContentMessageService contentMessageService;

  public ContentMessageController(ContentMessageService contentMessageService) {
    this.contentMessageService = contentMessageService;
  }

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<ContentMessageDto> create(@RequestBody @Valid ContentMessageDto contentMessageDto,
      UriComponentsBuilder uriComponentsBuilder) {
    ContentMessageDto contentMessage = contentMessageService.create(contentMessageDto);
    URI uri = uriComponentsBuilder.path("/content-messages/{id}").buildAndExpand(contentMessage.getIdContentMessage())
        .toUri();
    return ResponseEntity.created(uri).body(contentMessage);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<ContentMessageDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(contentMessageService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<ContentMessageDto>> retrieveAll(
      @PageableDefault(size = 25, sort = { "nmContentMessage" }) Pageable pageable) {
    return ResponseEntity.ok(contentMessageService.retrieveAll(pageable));
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<ContentMessageDto> update(@RequestBody ContentMessageDto contentMessageDto) {
    return ResponseEntity.ok(contentMessageService.update(contentMessageDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<ContentMessageDto> delete(@PathVariable String uuid) {
    contentMessageService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }
}
