package br.com.videomentor.api.youtube.resourceId;

/**
 * ResourceId.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */
public class ResourceId {

    public String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public ResourceId(String videoId) {
        this.videoId = videoId;
    }

    public ResourceId() {
    }

    // public String kind;
    
}
