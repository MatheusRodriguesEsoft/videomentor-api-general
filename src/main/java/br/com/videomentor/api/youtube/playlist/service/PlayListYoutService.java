package br.com.videomentor.api.youtube.playlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.videomentor.api.resources.VideoMentorService;
import br.com.videomentor.api.youtube.playlist.dto.PlayListYoutDto;


@Service
public class PlayListYoutService {

    @Autowired
    private VideoMentorService videoMentorService;

    public PlayListYoutDto findById(String id) {
        PlayListYoutDto playListYoutDto = videoMentorService.getPlayList(id, 25);
        return playListYoutDto;
    }

}
