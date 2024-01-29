package br.com.videomentor.api.youtube.defaultDto;

/**
 * Default.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class Default {

    public String url;

    public int width;

    public int height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Default(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public Default() {
    }  

}
