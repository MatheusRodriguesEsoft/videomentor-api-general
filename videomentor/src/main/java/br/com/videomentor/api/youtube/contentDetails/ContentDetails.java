package br.com.videomentor.api.youtube.contentDetails;

/**
 * ContentDetails.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */
public class ContentDetails {

    public String duration;

    public String aspectRatio;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public ContentDetails(String duration, String aspectRatio) {
        this.duration = duration;
        this.aspectRatio = aspectRatio;
    }

    public ContentDetails() {
    }

}
