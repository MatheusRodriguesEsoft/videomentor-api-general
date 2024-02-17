package br.com.videomentor.api.youtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.videomentor.api.resources.VideoMentorService;
import br.com.videomentor.api.videoaula.dto.MusicYoutDto;
import br.com.videomentor.api.youtube.video.dto.VideoYoutDto;

@Service
public class YoutubeService {
    @Autowired
    private VideoMentorService videoMentorService;

    public MusicYoutDto findById(String id) {
        VideoYoutDto videoYoutDto = videoMentorService.getVideo(id);
        return new MusicYoutDto(videoYoutDto);
    }
}
