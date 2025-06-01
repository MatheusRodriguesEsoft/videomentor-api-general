package br.com.videomentor.api.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.videomentor.api.auth.dto.AuthDto;
import br.com.videomentor.api.auth.dto.ForgotPasswordDto;
import br.com.videomentor.api.auth.dto.RdfPasswordDto;
import br.com.videomentor.api.auth.dto.ResDto;
import br.com.videomentor.api.user.dto.UserDto;
import br.com.videomentor.api.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

  @Autowired
  private UserService userService;

  @Transactional
  @PostMapping("/login")
  public ResponseEntity<ResDto> authenticate(@RequestBody AuthDto authDto) {
    return ResponseEntity.ok(userService.authenticate(authDto, "ADMIN"));
  }

  @Transactional
  @PostMapping("/login/teacher")
  public ResponseEntity<ResDto> authenticateTeacher(@RequestBody AuthDto authDto) {
    return ResponseEntity.ok(userService.authenticate(authDto, "TEACHER"));
  }

  @Transactional
  @PostMapping("/login/student")
  public ResponseEntity<ResDto> authenticateStudent(@RequestBody AuthDto authDto) {
    return ResponseEntity.ok(userService.authenticate(authDto, "STUDENT"));
  }

  @Transactional
  @PostMapping
  public ResponseEntity<UserDto> findUserByToken(@RequestBody String token) {
    return ResponseEntity.ok(userService.findUserByToken(token));
  }

  @Transactional
  @PostMapping("/redefine-password")
  public ResponseEntity<ResDto> redefinePassword(
      @RequestBody RdfPasswordDto rdfPasswordDto) {
    return ResponseEntity.ok(userService.redefinePassword(rdfPasswordDto));
  }

  @Transactional
  @PostMapping("/forgot-password")
  public ResponseEntity<ResDto> redefinePassword(
      @RequestBody ForgotPasswordDto forgotPasswordDto) {
    return ResponseEntity.ok(userService.forgotPassword(forgotPasswordDto));
  }
}
