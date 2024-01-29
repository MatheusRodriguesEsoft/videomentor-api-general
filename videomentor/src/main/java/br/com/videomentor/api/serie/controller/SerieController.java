package br.com.videomentor.api.serie.controller;

import br.com.videomentor.api.serie.dto.SerieDto;
import br.com.videomentor.api.serie.service.SerieService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SerieController {

  @Autowired
  SerieService serieService;

  @GetMapping("/{uuid}")
  public ResponseEntity<SerieDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(serieService.retrieveById(uuid));
  }

  @GetMapping
  public ResponseEntity<Page<SerieDto>> retrieveAll(
    @PageableDefault Pageable pageable
  ) {
    return ResponseEntity.ok(serieService.retrieveAll(pageable));
  }
}
