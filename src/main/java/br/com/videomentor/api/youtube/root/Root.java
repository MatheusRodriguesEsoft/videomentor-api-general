package br.com.videomentor.api.youtube.root;

import java.util.ArrayList;

import br.com.videomentor.api.youtube.item.Item;
import br.com.videomentor.api.youtube.pageInfo.PageInfo;

/**
 * Root.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */
public class Root {

    public String kind;

    public String etag;

    public ArrayList<Item> items;

    public PageInfo pageInfo;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Root(String kind, String etag, ArrayList<Item> items, PageInfo pageInfo) {
        this.kind = kind;
        this.etag = etag;
        this.items = items;
        this.pageInfo = pageInfo;
    }

    public Root() {
    }

}
