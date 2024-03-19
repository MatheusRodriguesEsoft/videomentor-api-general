package br.com.videomentor.api.videoaula.dto;

import br.com.videomentor.api.classe.dto.ClasseDto;
import br.com.videomentor.api.enumerations.StatusEnum;
import br.com.videomentor.api.subject.dto.SubjectDto;
import br.com.videomentor.api.module.dto.ModuleDto;

import java.util.List;
import java.util.UUID;

/**
 * VideoAulaDto.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

public class VideoAulaDto {
    private UUID idVideoaula;

    private UUID idTeacher;

    private String videoId;

    private String videoTitle;

    private String videoThumbnails;

    private String videoAuthor;

    private List<ClasseDto> classes;

    private SubjectDto subject;

    private ModuleDto module;

    private StatusEnum stVideoaula;

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

    public List<ClasseDto> getClasses() {
        return classes;
    }

    public void setClasses(List<ClasseDto> classes) {
        this.classes = classes;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public ModuleDto getModule() {
        return module;
    }

    public void setModule(ModuleDto module) {
        this.module = module;
    }

    public StatusEnum getStVideoaula() {
        return stVideoaula;
    }

    public void setStVideoaula(StatusEnum stVideoaula) {
        this.stVideoaula = stVideoaula;
    }

    public VideoAulaDto(UUID idVideoaula, UUID idTeacher, String videoId, String videoTitle, String videoThumbnails,
            String videoAuthor, List<ClasseDto> classes, SubjectDto subject, ModuleDto module, StatusEnum stVideoaula) {
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

    public VideoAulaDto() {
    }

}
