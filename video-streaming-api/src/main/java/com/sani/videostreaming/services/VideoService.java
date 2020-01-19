package com.sani.videostreaming.services;

import org.springframework.core.io.UrlResource;

import com.sani.videostreaming.model.Video;

import reactor.core.publisher.Mono;

public interface VideoService {
	public Mono<Video> get(String id);
	public Mono<UrlResource> watch(String id);
	public Mono<UrlResource> watchs3(String id);
}
