package br.com.videomentor.api.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.videomentor.api.video.dto.VideoYoutDto;

/**
 * VideoMentorService.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

 @Service
public class VideoMentorService {

    @Value("${api.url.host}")
    private String host;

    @Value("${api.url.path.video}")
    private String pathVideo;

    public VideoYoutDto getVideo(String id) {

        RestTemplate restTemplate = new RestTemplate();

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(host)
                .path(pathVideo + id)
                .build();

        ResponseEntity<VideoYoutDto> responseEntity = restTemplate.getForEntity(uriComponents.toString(),
                VideoYoutDto.class);
        // List<String> titles = responseEntity.getBody().items.stream().map(p ->
        // p.snippet.title).toList();
        return responseEntity.getBody();
    }
    
}
