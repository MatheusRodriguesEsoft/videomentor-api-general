package br.com.videomentor.api.comment.dto;

import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.user.dto.UserDto;
import br.com.videomentor.api.videoaula.dto.VideoAulaDto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

/**
 * CommentDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class CommentDto {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idComment;

  private String contentComment;

  private UserDto user;

  private VideoAulaDto videoAula;

  private StatusEnum stComment;

  public UUID getIdComment() {
    return idComment;
  }

  public void setIdComment(UUID idComment) {
    this.idComment = idComment;
  }

  public String getContentComment() {
    return contentComment;
  }

  public void setContentComment(String contentComment) {
    this.contentComment = contentComment;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }

  public VideoAulaDto getVideoAula() {
    return videoAula;
  }

  public void setVideoAula(VideoAulaDto videoAula) {
    this.videoAula = videoAula;
  }

  public StatusEnum getStComment() {
    return stComment;
  }

  public void setStComment(StatusEnum stComment) {
    this.stComment = stComment;
  }

  public CommentDto(UUID idComment, String contentComment, UserDto user, VideoAulaDto videoAula, StatusEnum stComment) {
    this.idComment = idComment;
    this.contentComment = contentComment;
    this.user = user;
    this.videoAula = videoAula;
    this.stComment = stComment;
  }

  public CommentDto() {
  }

}
