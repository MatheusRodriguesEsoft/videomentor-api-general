package br.com.videomentor.api.video.dto;

import java.util.List;

import br.com.videomentor.api.youtube.item.Item;

/**
 * VideoYoutDto.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class VideoYoutDto {

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
    
    public VideoYoutDto(VideoYoutDto videoYoutDto) {
        this.king = videoYoutDto.king;
        this.etag = videoYoutDto.etag;
        this.items = videoYoutDto.items;
    }

    public VideoYoutDto(String king, String etag, List<Item> items) {
        this.king = king;
        this.etag = etag;
        this.items = items;
    }

    public VideoYoutDto() {
    }

}
