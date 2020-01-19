package com.sani.videostreaming.lab.stream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import com.sani.videostreaming.model.Video;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

public class Lab1 {

	public void getAll(){
	   List<Video> videos = new ArrayList<Video>();
	   for(int i =0; i<10; i++) {
		  Video video = new Video();
		  video.setId(UUID.randomUUID().toString());
		  video.setName("Video-"+(i+1));
		  videos.add(video);
	   }
	  
	   Flux<Video> fVideos = Flux.fromIterable(videos);
	   fVideos.map(v -> {
		   v.setDescription("Description of "+v.getName());
		   return v;
	   })
	   .subscribe(System.out::println);
	   
	   
	   fVideos.empty()
	   .count()
	   .subscribe(System.out::println);
	   
	   
	   Flux<Long> fibonacciGenerator = Flux.generate(
		        () -> Tuples.<Long, Long>of(0L, 1L),
		        (state, sink) -> {
		            sink.next(state.getT1());
		            System.out.println("generated "+state.getT1());
		            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
		        });
		fibonacciGenerator.take(5).subscribe(t -> {
		    System.out.println("consuming "+t);
		   // fibonacciSeries.add(t);
		});
		

	}

	public void testFibonacciFluxSink1() {
	    Flux<Long> fibonacciGenerator = Flux.create(e -> {
	        long current = 1, prev = 0;
	        AtomicBoolean stop = new AtomicBoolean(false);
	        e.onDispose(()->{
	            stop.set(true);
	            System.out.println("******* Stop Received ****** ");
	        });
	        while (current > 0) {
	            e.next(current);
	            System.out.println("generated " + current);
	            long next = current + prev;
	            prev = current;
	            current = next;
	        }
	        e.complete();
	    });
	    List<Long> fibonacciSeries = new LinkedList<>();
	    fibonacciGenerator.take(50).subscribe(t -> {
	        System.out.println("consuming " + t);
	        fibonacciSeries.add(t);
	    });                      
	    //System.out.println(fibonacciSeries);
	}
	
	
	public void testFibonacciFluxSink2() {
		Flux<Long> fibonacciGenerator = Flux.generate(
		        () -> Tuples.<Long, Long>of(0L, 1L),
		        (state, sink) -> {
		            sink.next(state.getT1());
		            System.out.println("generated "+state.getT1());
		            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
		        });
		
		
		/*
		List<Long> fibonacciSeries = new LinkedList<>();
		fibonacciGenerator.take(10).subscribe(t -> {
		    System.out.println("consuming All "+t);
		    fibonacciSeries.add(t);
		});
		*/
		
		
		fibonacciGenerator
			.take(10)
			.filter(a -> a%2 == 0)
			.subscribe(t -> System.out.println("Consuming Odd "+t));
		
		fibonacciGenerator
		.skip(10)
		.subscribe(t -> System.out.println("Consuming "+t));
		
	}
	
	public static Flux<Long> generateRandom() {
		return  Flux.create(e ->{
			System.out.println("Inside create");
			long current = 0;
			AtomicBoolean stop = new AtomicBoolean(false);
	        e.onDispose(()->{
	            stop.set(true);
	            System.out.println("******* Stop Received ****** ");
	        });
	        while (current < 50) {
	        	current++;
	        	System.out.println("Inside loop");
	            e.next(current);
	        }
	        e.complete();
	        
		});		
	}
	
	public static Flux<Long> addParam(int count) {
		return  Flux.create(e ->{
			System.out.println("Inside create");
			long current = 0;
			AtomicBoolean stop = new AtomicBoolean(false);
	        e.onDispose(()->{
	            stop.set(true);
	            System.out.println("******* Stop Received ****** ");
	        });
	        while (current < count) {
	        	current++;
	        	System.out.println("Inside loop");
	            e.next(current);
	        }
	        e.complete();
	        
		});		
	}
	
	public void testRandom() {
		Flux<Long>result = generateRandom();
		result
			.take(10)
			.subscribe(System.out::println);
	}
	
	
	public void testMono1() {
		Mono mono1 = Mono.just("ok");
		mono1.subscribe(t -> System.out.println("consuming " + t));
		
	}
	
	public static void main (String args[]) {
		Lab1 lab1 = new Lab1();
		//lab1.getAll();
		//lab1.testFibonacciFluxSink2();
		//lab1.testMono1();
		//lab1.testFibonacciFluxSink2();
		lab1.testRandom();
	}
}
