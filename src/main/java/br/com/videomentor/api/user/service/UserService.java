package br.com.videomentor.api.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.videomentor.api.auth.dto.AuthDto;
import br.com.videomentor.api.auth.dto.ForgotPasswordDto;
import br.com.videomentor.api.auth.dto.RdfPasswordDto;
import br.com.videomentor.api.auth.dto.ResDto;
import br.com.videomentor.api.auth.jwt.JwtUtilities;
import br.com.videomentor.api.enumerations.RolesEnum;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.enumerations.StatusPassword;
import br.com.videomentor.api.exceptions.AuthenticationException;
import br.com.videomentor.api.exceptions.HandleRuntimeException;
import br.com.videomentor.api.exceptions.NotFoundException;
import br.com.videomentor.api.notification.model.Notification;
import br.com.videomentor.api.notification.repository.NotificationRepository;
import br.com.videomentor.api.role.model.Role;
import br.com.videomentor.api.role.repository.RoleRepository;
import br.com.videomentor.api.user.converter.UserConverter;
import br.com.videomentor.api.user.dto.UserDto;
import br.com.videomentor.api.user.model.User;
import br.com.videomentor.api.user.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtUtilities jwtUtilities;

  @Autowired
  private UserConverter userConverter;

  @Autowired
  UserRepository userRepository;

  @Autowired
  NotificationRepository notificationRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }

  public UserDto findUserByToken(String token) {
    String subject = jwtUtilities.getSubject(token);
    return userConverter.ormToDto(userRepository.findByUsername(subject));
  }

  public Page<UserDto> findByName(String nmUser, Pageable pageable) {
    List<User> users = userRepository.findByNmUserContainingIgnoreCase(nmUser).stream().collect(Collectors.toList());
    List<UserDto> usersDtos = users.stream().map(a -> userConverter.ormToDto(a)).collect(Collectors.toList());
    Page<UserDto> page = new PageImpl<UserDto>(usersDtos, pageable, usersDtos.size());
    return page;
  }

  public ResDto authenticate(AuthDto authDto, String role) {
    try {
      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      User user = userRepository.findByUsername(authentication.getName());

      var roleUser = user.getRoles().get(0);

      if (roleUser.getName().toString() != "ADMIN" & roleUser.getName().toString() != role) {
        throw new HandleRuntimeException("Usuário inválido");
      }

      List<String> rolesNames = new ArrayList<>();
      user.getRoles().forEach(r -> rolesNames.add(r.getName().toString()));
      String token = jwtUtilities.generateToken(user.getUsername());
      return new ResDto(token, userConverter.ormToDto(user));
    } catch (Exception ex) {
      throw new AuthenticationException("Usuário inválido ou senha incorreta");
    }
  }

  public ResDto redefinePassword(RdfPasswordDto rdfPasswordDto) {
    User user = userRepository.getReferenceById(rdfPasswordDto.getIdUser());
    String senhaTemporariaNaoEncriptada = rdfPasswordDto.getTemporaryPassword();
    String senhaEncriptadaDoBanco = user.getPassword();
    if (!rdfPasswordDto.getPassword().equals(rdfPasswordDto.getConfirmPassword())) {
      throw new AuthenticationException("Senha e Confirmar Senha devem ser iguais");
    }
    if (passwordEncoder.matches(senhaTemporariaNaoEncriptada, senhaEncriptadaDoBanco)) {
      user.setPassword(passwordEncoder.encode(rdfPasswordDto.getPassword()));
      user.setTemporaryPassword(null);
      user.setStPassword(StatusPassword.CONFIRMED);
      for (Notification n : user.getNotifications()) {
        if (n.getActions() == 0) {
          n.setStNotification(StatusEnum.INACTIVE);
          notificationRepository.save(n);
        }
      }
      userRepository.save(user);
      String token = jwtUtilities.generateToken(user.getUsername());
      return new ResDto(token, userConverter.ormToDto(user));
    } else {
      throw new AuthenticationException("Senha temporária inválida");
    }
  }

  public ResDto forgotPassword(ForgotPasswordDto forgotPasswordDto) {
    User user = userRepository.findByUsername(forgotPasswordDto.getUsername());
    if (user != null) {
      user.setTemporaryPassword(null);
      user.setStPassword(StatusPassword.UNCONFIRMED);
      userRepository.save(user);
      return new ResDto(null, null);
    } else {
      throw new AuthenticationException("Usuário não registardo");
    }
  }

  public UserDto create(UserDto userDto) {
    User existsUser = userRepository.findByUsername(userDto.getUsername());
    Role role = roleRepository.findByName(RolesEnum.ADMIN);
    if (existsUser != null) {
      throw new Error("Usuário já está registrado");
    }
    User user = userConverter.dtoToOrm(userDto);
    Notification notification = new Notification();
    notification.setNmNotification("Redefinição de senha");
    notification.setMessage("Para redefinir sua senha utilize a senha temporária enviado para seu email.");
    notification.setActions(0);
    notificationRepository.save(notification);
    user.setRoles(Collections.singletonList(role));
    user.setNotifications(Collections.singletonList(notification));
    return userConverter.ormToDto(userRepository.save(user));
  }

  public UserDto retrieveById(UUID id) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
    return userConverter.ormToDto(user);
  }

  public Page<UserDto> retrieveAll(Pageable pageable) {
    Page<User> userPage = userRepository.findAll(pageable);
    List<UserDto> userDtoList = userConverter.ormListToDtoList(userPage.getContent());
    return new PageImpl<>(userDtoList, pageable, userPage.getTotalElements());
  }

  public UserDto update(UserDto userDto) {
    try {
      User user = userRepository.getReferenceById(userDto.getIdUser());
      userRepository.save(userConverter.dtoToOrm(userDto, user));
      return userConverter.ormToDto(user, userDto);
    } catch (Exception e) {
      System.out.println("ERR: " + e.getMessage());
      return null;
    }
  }

  public void delete(UUID id) {
    User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    user.setStUser(StatusEnum.INACTIVE);
    userRepository.save(user);
  }
}
