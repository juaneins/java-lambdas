package lambdasinaction.chap4;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BasicStream {
	
	public static void main(String[] args) {
		
		getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
		System.out.println("-------------------------------------");
		getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
		System.out.println("-------------------------------------");
		getHighCaloricDishNames(3, Dish.menu).forEach(System.out::println);
		
		
	}
	
	public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish dish : dishes) {
			if(dish.getCalories() < 400) {
				lowCaloricDishes.add(dish);
			}
		}
		
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			@Override
			public int compare(Dish o1, Dish o2) {			
				return Integer.compare(o1.getCalories(), o2.getCalories());
			}			
		});
		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish dish :  lowCaloricDishes) {
			lowCaloricDishesName.add(dish.getName());
		}
		return lowCaloricDishesName;
	}
	
	public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
		List<String> lowCaloricDishesName = dishes.stream()
				.filter(d -> d.getCalories() < 400)
				.sorted(comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(toList());
		
		return lowCaloricDishesName;
		
	}
	
	public static List<String> getHighCaloricDishNames(int limit, List<Dish> dishes) {
		List<String> highCaloricDishesName = dishes.stream()
				.filter(d -> {
					System.out.println("Filtering: " + d.getName());
				         return d.getCalories() > 300;
				})
				.map(d-> {
					System.out.println("Mapping: " + d.getName());
					return d.getName();
				})
				.limit(limit)
				
				.collect(toList());
		System.out.println(highCaloricDishesName);

		return highCaloricDishesName;
	}

}
