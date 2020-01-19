package com.sani.videostreaming;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sani.videostreaming.lab.stream.Lab1;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test/")
public class TestAPI {
	
	@RequestMapping(value= {"get/{notake}"}, method=RequestMethod.GET)
	public ResponseEntity<Flux<Long>> get(@PathVariable int notake){
		return new ResponseEntity<Flux<Long>>(Lab1.addParam(notake), HttpStatus.OK);
	}
}
