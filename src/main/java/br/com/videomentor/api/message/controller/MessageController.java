package br.com.videomentor.api.message.controller;

import br.com.videomentor.api.message.dto.MessageDto;
import br.com.videomentor.api.message.service.MessageService;
import br.com.videomentor.api.user.converter.UserConverter;
import br.com.videomentor.api.user.dto.UserDto;
import br.com.videomentor.api.user.service.UserService;
import br.com.videomentor.api.commons.AbstractController;
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
@RequestMapping("/messages")
public class MessageController implements AbstractController<MessageDto> {

  private MessageService messageService;

  private UserService userService;

  private UserConverter userConverter;

  public MessageController(MessageService messageService, UserService userService, UserConverter userConverter) {
    this.messageService = messageService;
    this.userService = userService;
    this.userConverter = userConverter;
  }

  @Override
  @PostMapping
  @Transactional
  public ResponseEntity<MessageDto> create(@RequestBody @Valid MessageDto messageDto,
      UriComponentsBuilder uriComponentsBuilder) {
    MessageDto message = messageService.create(messageDto);
    URI uri = uriComponentsBuilder.path("/messages/{id}").buildAndExpand(message.getIdMessage()).toUri();
    return ResponseEntity.created(uri).body(message);
  }

  @Override
  @GetMapping("/{uuid}")
  public ResponseEntity<MessageDto> retrieveById(@PathVariable UUID uuid) {
    return ResponseEntity.ok(messageService.retrieveById(uuid));
  }

  @Override
  @GetMapping
  public ResponseEntity<Page<MessageDto>> retrieveAll(
      @PageableDefault(size = 25, sort = { "nmMessage" }) Pageable pageable) {
    return ResponseEntity.ok(messageService.retrieveAll(pageable));
  }

  @GetMapping("/user/{idUser}")
  public ResponseEntity<List<MessageDto>> retrieveByUser(@PathVariable UUID idUser) {
    UserDto user = userService.retrieveById(idUser);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    List<MessageDto> messages = messageService.retrieveByUser(userConverter.dtoToOrm(user));
    return ResponseEntity.ok(messages);
  }

  @GetMapping("/receiver/{idUser}")
  public ResponseEntity<List<MessageDto>> retrieveByReceiver(@PathVariable UUID idUser) {
    UserDto user = userService.retrieveById(idUser);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    List<MessageDto> messages = messageService.retrieveByReceiver(userConverter.dtoToOrm(user));
    return ResponseEntity.ok(messages);
  }

  @GetMapping("/sender/{idUser}")
  public ResponseEntity<List<MessageDto>> retrieveBySender(@PathVariable UUID idUser) {
    UserDto user = userService.retrieveById(idUser);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }
    List<MessageDto> messages = messageService.retrieveBySender(userConverter.dtoToOrm(user));
    return ResponseEntity.ok(messages);
  }

  @Override
  @PutMapping
  @Transactional
  public ResponseEntity<MessageDto> update(@RequestBody MessageDto messageDto) {
    return ResponseEntity.ok(messageService.update(messageDto));
  }

  @Override
  @DeleteMapping("/{uuid}")
  public ResponseEntity<MessageDto> delete(@PathVariable String uuid) {
    messageService.delete(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
  }
}
