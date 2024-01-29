package br.com.videomentor.api.user.controller;

import br.com.videomentor.api.user.dto.UserDto;
import br.com.videomentor.api.user.service.UserService;
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
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping
  @Transactional
  public ResponseEntity<UserDto> create(
    @RequestBody @Valid UserDto userDto,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    UserDto user = userService.create(userDto);
    URI uri = uriComponentsBuilder
      .path("/users/{id}")
      .buildAndExpand(user.getIdUser())
      .toUri();
    return ResponseEntity.created(uri).body(user);
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<UserDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(userService.retrieveById(uuid));
  }

  @GetMapping
  public ResponseEntity<Page<UserDto>> retrieveAll(
    @PageableDefault(size = 25, sort = { "nmUser" }) Pageable pageable
  ) {
    return ResponseEntity.ok(userService.retrieveAll(pageable));
  }

  @PutMapping
  @Transactional
  public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.update(userDto));
  }

  @DeleteMapping("/{uuid}")
  public ResponseEntity<UserDto> delete(@PathVariable String uuid) {
    userService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }
}
