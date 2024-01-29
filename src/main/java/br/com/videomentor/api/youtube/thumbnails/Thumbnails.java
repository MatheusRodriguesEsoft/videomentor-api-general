package br.com.videomentor.api.youtube.thumbnails;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.videomentor.api.youtube.defaultDto.Default;
import br.com.videomentor.api.youtube.high.High;
import br.com.videomentor.api.youtube.maxres.Maxres;
import br.com.videomentor.api.youtube.medium.Medium;
import br.com.videomentor.api.youtube.standard.Standard;

// import br.com.musicfinder.api.defaultDto.Default;
// import br.com.musicfinder.api.high.High;
// import br.com.musicfinder.api.medium.Medium;
/**
 * Thumbnails.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

public class Thumbnails {

    @JsonProperty("default")

    public Standard standard;

    public Maxres maxres;

    public Default mydefault;

    public Medium medium;

    public High high;

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public Maxres getMaxres() {
        return maxres;
    }

    public void setMaxres(Maxres maxres) {
        this.maxres = maxres;
    }

    public Default getMydefault() {
        return mydefault;
    }

    public void setMydefault(Default mydefault) {
        this.mydefault = mydefault;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }

    public Thumbnails(Standard standard, Maxres maxres, Default mydefault, Medium medium, High high) {
        this.standard = standard;
        this.maxres = maxres;
        this.mydefault = mydefault;
        this.medium = medium;
        this.high = high;
    }

    public Thumbnails() {
    }

}
