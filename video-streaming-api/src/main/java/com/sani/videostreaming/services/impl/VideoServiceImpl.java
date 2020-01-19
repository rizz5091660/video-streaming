package com.sani.videostreaming.services.impl;

import java.net.MalformedURLException;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sani.videostreaming.model.Video;
import com.sani.videostreaming.services.VideoService;

import reactor.core.publisher.Mono;

@Service
public class VideoServiceImpl implements VideoService{

	@Override
	public Mono<Video> get(String id) {
		Video video = new Video();
		video.setId(id);
		video.setName("Avengers Endgame");
		video.setDescription("After Thanos, an intergalactic warlord, disintegrates half of the universe, the Avengers must reunite and assemble again to reinvigorate their trounced allies and restore balance.");
		video.setDuration(150);		
		return Mono.just(video);
	}

	@Override
	public Mono<UrlResource> watch(String id) {		
		try {
			return Mono.just(new UrlResource("file:/Users/user/Downloads/getfvid.mp4"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Mono<UrlResource> watchs3(String id) {
		try {
			return Mono.just(new UrlResource("http://hirememates.s3-ap-southeast-2.amazonaws.com/getfvid.mp4"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
