package br.com.videomentor.api.commons;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface AbstractController<Dto> {
  ResponseEntity<Dto> create(
    Dto dto,
    UriComponentsBuilder uriComponentsBuilder
  );

  ResponseEntity<Dto> retrieveById(UUID uuid);

  ResponseEntity<Page<Dto>> retrieveAll(Pageable pageable);

  ResponseEntity<Dto> update(Dto dto);

  ResponseEntity<Dto> delete(String uuid);
}
