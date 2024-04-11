package br.com.videomentor.api.comment.model;

import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.user.model.User;
import br.com.videomentor.api.videoaula.model.VideoAula;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

/**
 * Comment.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "comments")
@Entity(name = "Comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID idComment;

  private String contentComment;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "videoaula_id")
  private VideoAula videoAula;

  private StatusEnum stComment = StatusEnum.ACTIVE;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public VideoAula getVideoAula() {
    return videoAula;
  }

  public void setVideoAula(VideoAula videoAula) {
    this.videoAula = videoAula;
  }

  public StatusEnum getStComment() {
    return stComment;
  }

  public void setStComment(StatusEnum stComment) {
    this.stComment = stComment;
  }

  public Comment(UUID idComment, String contentComment, User user, VideoAula videoAula, StatusEnum stComment) {
    this.idComment = idComment;
    this.contentComment = contentComment;
    this.user = user;
    this.videoAula = videoAula;
    this.stComment = stComment;
  }

  public Comment() {
  }

}
