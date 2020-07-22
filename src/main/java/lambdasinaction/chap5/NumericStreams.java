package lambdasinaction.chap5;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lambdasinaction.chap4.Dish;

public class NumericStreams {

	public static void main(String[] args) {
		System.out.println("--------- sum all calories reduce,map  --------------");
		/*int sumaTotal = Dish.menu.stream().reduce(0, (acum, d) -> 
		acum + d.getCalories(), Integer::sum);
		System.out.println("Total calories: " + Dish.menu.stream().reduce(0, (total, d) -> 
			total + d.getCalories(), Integer::sum
		));*/
		System.out.println("Total of calories: " + sumCaloriesMap(Dish.menu));
		System.out.println("--------- max calories  --------------");
		System.out.println("Max: " + getMaxCalories(Dish.menu).orElse(1));
		System.out.println("----------------- Numeric ranges ------------------");
		System.out.println("----------------- Generate even numbers ------------------");
		evenNumbers(1,100).forEach(System.out::println);
		System.out.println("--------- Pytagorean triples ---------------");
		 Stream<int[]> pythagoreanTriples =
	               IntStream.rangeClosed(1, 100).boxed()
	                        .flatMap(a -> IntStream.rangeClosed(a, 100)
	                                               .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
	                                               .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));       

	        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
		

	}
	
	public static int sumCalories(List<Dish> menu) {
		int total =  0;
		for (Dish dish : menu) {
			total += dish.getCalories();
		}
		return total;
	}
	
	public static int sumCaloriesMap(List<Dish> menu) {
		return menu.stream().mapToInt(Dish::getCalories).sum();
	}
	
	public static OptionalInt getMaxCalories(List<Dish> menu) {
		return menu.stream().mapToInt(Dish::getCalories).max();
		
	}
	
	public static IntStream evenNumbers(int start,int end) {
		return IntStream.rangeClosed(start, end)
				.filter(n -> n % 2 == 0);
	}

}
