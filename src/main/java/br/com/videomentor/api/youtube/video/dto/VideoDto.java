package br.com.videomentor.api.youtube.video.dto;

import java.util.List;

import br.com.videomentor.api.youtube.item.Item;

public class VideoDto {

    public String king;

    public String etag;

    public List<Item> items;

    public String getKing() {
        return king;
    }

    public void setKing(String king) {
        this.king = king;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public VideoDto(String king, String etag, List<Item> items) {
        this.king = king;
        this.etag = etag;
        this.items = items;
    }

    public VideoDto() {
    }

}
