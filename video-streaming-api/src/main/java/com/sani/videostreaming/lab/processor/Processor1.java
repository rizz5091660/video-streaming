package com.sani.videostreaming.lab.processor;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.FluxSink;

public class Processor1 {
	public void process1() {
		DirectProcessor<Long> data = DirectProcessor.create();
		data.take(2).subscribe(t -> System.out.println(t));
		data.onNext(10L);
		data.onNext(11L);
		data.onNext(12L);
	}
	
	public void process2() {
		DirectProcessor<Long> data = DirectProcessor.create();
		data.subscribe(t -> System.out.println(t),
		        e -> e.printStackTrace(),
		        () -> System.out.println("Finished 1"));
		data.onNext(10L);
		data.onComplete();
		data.subscribe(t -> System.out.println(t),
		        e -> e.printStackTrace(),
		        () -> System.out.println("Finished 2"));
		data.onNext(12L);
		data.onNext(15L);
	}
	
	public void process3() {
		DirectProcessor<Long> data = DirectProcessor.create();
		data.subscribe(t -> 
				System.out.println(t),  
				e -> e.printStackTrace(),    
				() -> System.out.println("Finished"),  
				s -> s.request(1));
		data.onNext(10L);
		data.onNext(11L);
		data.onNext(12L);
	}
	
	public void process4() {
		EmitterProcessor<Long> data = EmitterProcessor.create(1);
		data.subscribe(t -> System.out.println(t));
		FluxSink<Long> sink = data.sink();
		sink.next(10L);
		sink.next(11L);
		sink.next(12L);
		data.subscribe(t -> System.out.println(t));
		sink.next(13L);
		sink.next(14L);
		sink.next(15L);
	}
	
	public static void main (String args[]) {
		Processor1 p1 = new Processor1();
		//p1.process1();
		//p1.process2();
		p1.process4();
	}

}
