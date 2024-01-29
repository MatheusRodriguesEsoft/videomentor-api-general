package br.com.videomentor.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VideomentorApplication.
 *
 * @author Matheus Rodrigues <matheusrodrigues.dev@outlook.com>
 * @version 1.0
 */

@RestController
@RequestMapping("/")
@SpringBootApplication
public class VideomentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideomentorApplication.class, args);
	}

	@GetMapping
    public String index() {
        return "Api VideoMentor® V1.0";
    }

}
