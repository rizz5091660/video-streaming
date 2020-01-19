package com.sani.videostreaming.lab.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;

public class Lab2 {
	public static Collection<Integer>  findFactor(int number){
		 ArrayList<Integer> factors= new ArrayList<>();
		 for(int i =1; i<= number;i++ ) {
			 if (number !=0   && number % i == 0) {
	                factors.add(i);
	            }
		 }
		 return factors;
	}
	
	public void map() {
		Lab1
		.generateRandom()
		.take(5)
		.repeat(1)
		.map(v -> (new RomanNumber()).toRomanNumeral(v.intValue()))
		.collect(Collectors.toList())
		.subscribe(System.out::println);
		
		Lab1
		.generateRandom()
		.take(10)
		.flatMap(v -> Flux.fromIterable(Lab2.findFactor(v.intValue())))
		.subscribe(System.out::println);
		
		Lab1
		.generateRandom()
		.take(10)
		.collectMap(t -> t%2==0 ? "even": "odd")
		.subscribe(System.out::println);
		
		Lab1
		.generateRandom()
		.take(10)
		.concatWith(Flux.just(100L,200L,300L))
		.subscribe(System.out::println);
	}
	
	public static void main (String args[]) {
		Lab2 lab2 = new Lab2();
		lab2.map();
		
	}

}
