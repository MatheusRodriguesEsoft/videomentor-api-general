package br.com.videomentor.api.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.videomentor.api.youtube.playlist.dto.PlayListYoutDto;
import br.com.videomentor.api.youtube.video.dto.VideoDto;

/**
 * MusicFinderService.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@Service
public class VideoMentorService {

        @Value("${api.url.host}")
        private String host;

        @Value("${api.url.path.playlist}")
        private String pathPlayList;

        @Value("${api.url.path.video}")
        private String pathVideo;

        /**
         * @param id
         * @return PlayListDto
         */
        public PlayListYoutDto getPlayList(String id, Integer maxResults) {

                RestTemplate restTemplate = new RestTemplate();

                UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https").host(host)
                                .path(pathPlayList + id).build();
                ResponseEntity<PlayListYoutDto> responseEntity = restTemplate.getForEntity(uriComponents.toString(),
                                PlayListYoutDto.class);
                return responseEntity.getBody();
        }

        public VideoDto getVideo(String id) {

                RestTemplate restTemplate = new RestTemplate();

                UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https").host(host)
                                .path(pathVideo + id).build();

                ResponseEntity<VideoDto> responseEntity = restTemplate.getForEntity(uriComponents.toString(),
                                VideoDto.class);
                return responseEntity.getBody();
        }

}