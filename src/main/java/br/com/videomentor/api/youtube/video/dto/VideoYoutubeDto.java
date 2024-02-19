package br.com.videomentor.api.youtube.video.dto;

import java.util.stream.Stream;

import br.com.videomentor.api.youtube.high.High;
import br.com.videomentor.api.youtube.item.Item;
import br.com.videomentor.api.youtube.maxres.Maxres;
import br.com.videomentor.api.youtube.medium.Medium;
import br.com.videomentor.api.youtube.standard.Standard;

public class VideoYoutubeDto {

    private String id;
    private String title;
    private String thumbnails;
    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public VideoYoutubeDto(VideoDto videoDto) {
        Stream<Item> itemStr = videoDto.items.stream().map(i -> i);
        Item item = itemStr.findFirst().get();
        Maxres maxres = item.snippet.thumbnails.maxres;
        Medium medium = item.snippet.thumbnails.medium;
        Standard standard = item.snippet.thumbnails.standard;
        High high = item.snippet.thumbnails.high;

        id = item.id;
        title = item.snippet.title;
        author = item.snippet.channelTitle;

        if (maxres != null) {
            thumbnails = maxres.url;
        } else if (medium != null) {
            thumbnails = medium.url;
        } else if (standard != null) {
            thumbnails = standard.url;
        } else if (high != null) {
            thumbnails = high.url;
        }
    }

    public VideoYoutubeDto() {
    }

}
