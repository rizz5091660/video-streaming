package com.sani.videostreaming.model;

import lombok.Data;

@Data
public class Video {
	private String id;
	private String name;
	private String description;
	private String location;
	private int duration;
}
