package br.com.videomentor.api.videoaula.model;

import br.com.videomentor.api.classe.model.Classe;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.subject.model.Subject;
import br.com.videomentor.api.module.model.Module;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.List;
import java.util.UUID;

/**
 * Videoaula.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Table(name = "videoaulas")
@Entity(name = "Videoaula")
public class VideoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idVideoaula;

    private UUID idTeacher;

    private String videoId;

    private String videoTitle;

    private String videoThumbnails;

    private String videoAuthor;

    @ManyToMany
    @JoinTable(name = "classe_videoaula", joinColumns = @JoinColumn(name = "classe_id"), inverseJoinColumns = @JoinColumn(name = "videoaula_id"))
    private List<Classe> classes;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    private StatusEnum stVideoaula = StatusEnum.ACTIVE;

    public UUID getIdVideoaula() {
        return idVideoaula;
    }

    public void setIdVideoaula(UUID idVideoaula) {
        this.idVideoaula = idVideoaula;
    }

    public UUID getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(UUID idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoThumbnails() {
        return videoThumbnails;
    }

    public void setVideoThumbnails(String videoThumbnails) {
        this.videoThumbnails = videoThumbnails;
    }

    public String getVideoAuthor() {
        return videoAuthor;
    }

    public void setVideoAuthor(String videoAuthor) {
        this.videoAuthor = videoAuthor;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public StatusEnum getStVideoaula() {
        return stVideoaula;
    }

    public void setStVideoaula(StatusEnum stVideoaula) {
        this.stVideoaula = stVideoaula;
    }

    public VideoAula(UUID idVideoaula, UUID idTeacher, String videoId, String videoTitle, String videoThumbnails,
            String videoAuthor, List<Classe> classes, Subject subject, Module module, StatusEnum stVideoaula) {
        this.idVideoaula = idVideoaula;
        this.idTeacher = idTeacher;
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.videoThumbnails = videoThumbnails;
        this.videoAuthor = videoAuthor;
        this.classes = classes;
        this.subject = subject;
        this.module = module;
        this.stVideoaula = stVideoaula;
    }

    public VideoAula() {
    }

}
