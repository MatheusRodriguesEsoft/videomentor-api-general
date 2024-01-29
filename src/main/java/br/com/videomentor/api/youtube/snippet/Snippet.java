package br.com.videomentor.api.youtube.snippet;

import br.com.videomentor.api.youtube.resourceId.ResourceId;
import br.com.videomentor.api.youtube.thumbnails.Thumbnails;

/**
 * Snippet.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class Snippet {

    public String title;

    public Thumbnails thumbnails;

    public int position;

    public ResourceId resourceId;

    // public Date publishedAt;
    // public String channelId;
    // public String description;
    public String channelTitle;
    // public String playlistId;
    // public String videoOwnerChannelTitle;
    // public String videoOwnerChannelId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ResourceId getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Snippet(String title, Thumbnails thumbnails, int position, ResourceId resourceId, String channelTitle) {
        this.title = title;
        this.thumbnails = thumbnails;
        this.position = position;
        this.resourceId = resourceId;
        this.channelTitle = channelTitle;
    }

    public Snippet() {
    }

        
}
