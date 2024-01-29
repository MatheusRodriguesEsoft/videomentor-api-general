package br.com.videomentor.api.areaofknowledge.controller;

import br.com.videomentor.api.areaofknowledge.dto.AreaOfKnowledgeDto;
import br.com.videomentor.api.areaofknowledge.service.AreaOfKnowledgeService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area-of-knowledge")
public class AreaOfKnowledgeController {

  @Autowired
  AreaOfKnowledgeService areaOfKnowledgeService;

  @GetMapping("/{uuid}")
  public ResponseEntity<AreaOfKnowledgeDto> retrieveById(
    @PathVariable UUID uuid
  ) {
    return ResponseEntity.ok(areaOfKnowledgeService.retrieveById(uuid));
  }

  @GetMapping
  public ResponseEntity<Page<AreaOfKnowledgeDto>> retrieveAll(
    @PageableDefault Pageable pageable
  ) {
    return ResponseEntity.ok(areaOfKnowledgeService.retrieveAll(pageable));
  }
}
