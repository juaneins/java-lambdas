package lambdasinaction.chap6;

import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

public class Summarizing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Count dishes: " + countDishes(Dish.menu));
		System.out.println("Highest calories dish: " + getMaxCaloriesDish(Dish.menu).get());
		System.out.println("Highest calories dish 2: " + getMaxCaloriesDish2(Dish.menu).get());
		System.out.println("Total calories dish: " + sumTotalCalories(Dish.menu));
		System.out.println("Total calories dish 2: " + sumTotalCalories2(Dish.menu));
		System.out.println("Total calories dish 3: " + sumTotalCalories3(Dish.menu));
		System.out.println("Average calories dish: " + calculateAverageCalories(Dish.menu));
		System.out.println("Statistics calories dish: " + calculateMenuStatistics(Dish.menu));
		System.out.println("Concatenate menu names: " + getShortMenu(Dish.menu));
		System.out.println("getShortMenuCommaSeparated menu names: " + getShortMenuCommaSeparated(Dish.menu));
	}
	
	public static long countDishes(List<Dish> menu) {
//		return menu.stream().count();
		return menu.stream().collect(counting());		
	}
	
	public static Optional<Dish> getMaxCaloriesDish(List<Dish> menu) {
		return menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories)));
	}
	
	public static Optional<Dish> getMaxCaloriesDish2(List<Dish> menu) {
		return menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d1.getCalories() ? d1 : d2));
	}
	
	/*public static int getMaxCaloriesDishMap(List<Dish> menu) {
		return menu.stream().map(mapper)
	}*/
	
	public static int sumTotalCalories(List<Dish> menu) {
		return menu.stream().collect(summingInt(Dish::getCalories));
		
	}
	
	public static int sumTotalCalories2(List<Dish> menu) {
		return menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
		
	}
	
	public static int sumTotalCalories3(List<Dish> menu) {
		return menu.stream().mapToInt(Dish::getCalories).sum();
		
	}
	
	public static Double calculateAverageCalories(List<Dish> menu) {
		return menu.stream().collect(averagingInt(Dish::getCalories));
	}
	
	public static IntSummaryStatistics calculateMenuStatistics(List<Dish> menu) {
		return menu.stream().collect(summarizingInt(Dish::getCalories));
	}
	
	public static String getShortMenu(List<Dish> menu) {
		return menu.stream().map(Dish::getName).collect(joining());
	}
	
	public static String getShortMenuCommaSeparated(List<Dish> menu) {
		return menu.stream().map(Dish::getName).collect(joining(", "));
	}
	
	
	
	
	

}
