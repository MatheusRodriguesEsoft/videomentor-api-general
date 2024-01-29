package br.com.videomentor.api.youtube.standard;

/**
 * Standard.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class Standard {

    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Standard(String url) {
        this.url = url;
    }

    public Standard() {
    }

    // public int width;
    // public int height;

}
