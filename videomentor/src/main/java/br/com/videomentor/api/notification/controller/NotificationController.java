package br.com.videomentor.api.notification.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.videomentor.api.commons.AbstractController;
import br.com.videomentor.api.notification.dto.NotificationDto;
import br.com.videomentor.api.notification.service.NotificationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notifications")
public class NotificationController implements AbstractController<NotificationDto> {

	@Autowired
	NotificationService notificationService;

	@PostMapping
	@Transactional
	@Override
	public ResponseEntity<NotificationDto> create(@RequestBody @Valid NotificationDto notificationDto, UriComponentsBuilder u) {
        NotificationDto notification = notificationService.create(notificationDto);
        URI uri = u.path("/notifications/{id}").buildAndExpand(notification.getIdNotification()).toUri();
        return ResponseEntity.created(uri).body(notification);
    }

	@Override
	@GetMapping("/{id}")
    public ResponseEntity<NotificationDto> retrieveById(@PathVariable UUID id) {
        return ResponseEntity.ok(notificationService.retrieveById(id));
    }

	@Override
	@GetMapping
	public ResponseEntity<Page<NotificationDto>> retrieveAll(Pageable pageable) {
		return ResponseEntity.ok(notificationService.retrieveAll(pageable));
	}

	@Override
	public ResponseEntity<NotificationDto> update(NotificationDto dto) {
		
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public ResponseEntity<NotificationDto> delete(String uuid) {
		
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

}