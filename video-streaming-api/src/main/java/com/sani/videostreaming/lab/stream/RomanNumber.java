package com.sani.videostreaming.lab.stream;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

import reactor.core.publisher.Flux;

public class RomanNumber {
	TreeMap<Integer, String> romanMap= new TreeMap<>();
    RomanNumber(){
        romanMap.put(1000, "M");
        romanMap.put(900, "CM");
        romanMap.put(500, "D");
        romanMap.put(400, "CD");
        romanMap.put(100, "C");
        romanMap.put(90, "XC");
        romanMap.put(50, "L");
        romanMap.put(40, "XL");
        romanMap.put(10, "X");
        romanMap.put(9, "IX");
        romanMap.put(5, "V");
        romanMap.put(4, "IV");
        romanMap.put(1, "I");
    }
    public String toRomanNumeral(int number) {
        int l =  romanMap.floorKey(number);
        if ( number == l ) {
            return romanMap.get(number);
        }
        return romanMap.get(l) + toRomanNumeral(number-l);
    }
    
    public void convert() {
    	Flux<Long> genRandom = Flux.create(e ->{
			long current = 0;
			AtomicBoolean stop = new AtomicBoolean(false);
	        e.onDispose(()->{
	            stop.set(true);
	            System.out.println("******* Stop Received ****** ");
	        });
	        while (current < 50) {
	        	current++;
	            e.next(current);
	        }
	        e.complete();
	        
		});
		
		genRandom
			.take(10)
			.log()
			.map(t -> toRomanNumeral(t.intValue()))
			.subscribe(System.out::println);
    	
    }
    
    public static void main(String args[]) {
    	RomanNumber numberConvertor= new RomanNumber();
    	numberConvertor.convert();
    	
    	
    }
}
