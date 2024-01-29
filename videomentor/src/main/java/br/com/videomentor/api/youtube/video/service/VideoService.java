package br.com.videomentor.api.youtube.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.videomentor.api.resources.VideoMentorService;
import br.com.videomentor.api.video.dto.VideoYoutDto;

/**
 * VideoService.
 * 
 * @author Matheus Rodrigues <matheusrodrigues@outlook.com.br>
 * @version 1.0
 * @since 12/12/2022
 */

@Service
public class VideoService {

    @Autowired
    private VideoMentorService videoMentorService;

    public VideoYoutDto findById(String id) {
        VideoYoutDto videoYoutDto = videoMentorService.getVideo(id);
        return new VideoYoutDto(videoYoutDto);
    }
} 
