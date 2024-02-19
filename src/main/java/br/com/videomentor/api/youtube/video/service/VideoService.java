package br.com.videomentor.api.youtube.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.videomentor.api.resources.VideoMentorService;

import br.com.videomentor.api.youtube.video.dto.VideoDto;
import br.com.videomentor.api.youtube.video.dto.VideoYoutubeDto;

@Service
public class VideoService {

    @Autowired
    private VideoMentorService videoMentorService;

    public VideoYoutubeDto findById(String id) {
        VideoDto videoDto = videoMentorService.getVideo(id);
        return new VideoYoutubeDto(videoDto);
    }
}
