package br.com.videomentor.api.commons;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<Dto> {
  Dto create(Dto dto);

  Dto retrieveById(UUID uuid);

  Page<Dto> retrieveAll(Pageable pageable);

  Dto update(Dto dto);

  void delete(UUID uuid);
}
