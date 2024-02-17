package br.com.videomentor.api.youtube.playlist.dto;

import java.io.Serializable;
import java.util.List;

import br.com.videomentor.api.youtube.item.Item;

/**
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 16/02/2024
 */

public class PlayListYoutDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public String king;

    public String etag;

    public List<Item> items;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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

    public PlayListYoutDto(String king, String etag, List<Item> items) {
        this.king = king;
        this.etag = etag;
        this.items = items;
    }

    public PlayListYoutDto() {
    }

}
