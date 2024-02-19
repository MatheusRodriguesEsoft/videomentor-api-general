package br.com.videomentor.api.youtube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.videomentor.api.youtube.service.YoutubeService;
import br.com.videomentor.api.youtube.video.dto.VideoYoutubeDto;

/**
 * YoutubeController.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@RestController
@RequestMapping("/videoaulas")
public class YoutubeController {

    @Autowired
    public YoutubeService youtubeService;

    @GetMapping("youtube/{id}")
    public ResponseEntity<VideoYoutubeDto> findYoutById(@PathVariable String id) {
        return ResponseEntity.ok(youtubeService.findById(id));
    }
}
