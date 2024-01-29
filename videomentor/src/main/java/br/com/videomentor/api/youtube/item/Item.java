package br.com.videomentor.api.youtube.item;

import br.com.videomentor.api.youtube.snippet.Snippet;

/**
 * Item.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class Item {

    public Snippet snippet;

    public String id;

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item(Snippet snippet, String id) {
        this.snippet = snippet;
        this.id = id;
    }

    public Item() {
    }

}
