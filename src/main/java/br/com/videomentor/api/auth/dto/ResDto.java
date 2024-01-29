package br.com.videomentor.api.auth.dto;

import br.com.videomentor.api.user.dto.UserDto;

public class ResDto {

  private String token;

  private UserDto user;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  /**
   * Default constructor
   */
  public ResDto() {}

  public ResDto(String token, UserDto user) {
    this.token = token;
    this.user = user;
  }
}
