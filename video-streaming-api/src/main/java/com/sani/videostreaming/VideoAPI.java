package com.sani.videostreaming;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sani.videostreaming.model.Video;
import com.sani.videostreaming.services.VideoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/video/")
public class VideoAPI {
	
	@Autowired
	VideoService videoService;
	
	
	@RequestMapping(value= {"watch/{id}"}, method=RequestMethod.GET)
	public ResponseEntity<Mono<UrlResource>> getVideo(@PathVariable String id){
		Mono<UrlResource> watch = videoService.watch(id);
		 return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(watch);
	}
	
	@RequestMapping(value= {"watchs3/{id}"}, method=RequestMethod.GET)
	public ResponseEntity<Mono<UrlResource>> getVideoS3(@PathVariable String id){
		Mono<UrlResource> watch = videoService.watchs3(id);
		 return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(watch);
	}
	

}
