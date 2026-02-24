package com.example.se17;

import java.util.List;

public class Exercise01 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
        var zoo = List.of(
        		new Spider(),
        		new Cat(),
        		new VanCat(),
        		new Fish(),
        		new Spider(),
        		new Fish()
        	);
        for (var animal : zoo) {
        	   if (animal instanceof Pet pet && pet.metric() > 4) {
        	    		// var pet = (Pet) animal;
        	    		pet.play();
        	   }
        	   switch(animal) {
        	      case null -> {}
        	      case Cat cat when cat.getLegs() == 4 -> {}
        	      case Spider spider -> {}
        	      case Fish fish -> {}
        	      default -> {}
        	   }
        }
	}
}

sealed abstract class Animal permits Spider, Cat, Fish {
	abstract public int getLegs();
}

interface Pet {
	default void play() {
	}
	default int metric() { return 42;}
}

// Solution Class
final class Spider extends Animal {

	@Override
	public int getLegs() {
		return 8;
	}

}

sealed class Cat extends Animal implements Pet permits VanCat {

	@Override
	public int getLegs() {
		return 4;
	}
}

final class Fish extends Animal implements Pet {

	@Override
	public int getLegs() {
		return 0;
	}
}

non-sealed class VanCat extends Cat {
}