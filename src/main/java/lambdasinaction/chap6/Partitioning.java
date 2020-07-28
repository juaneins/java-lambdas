package lambdasinaction.chap6;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static lambdasinaction.chap6.Dish.menu;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.comparingInt;

public class Partitioning {

	public static void main(String[] args) {
		System.out.println("getPartitionedMenu is vegetarian: " + getPartitionedMenu());
		System.out.println("Get vegetarian menu: " + getVegetarianMenu());
		System.out.println(" ------------------ getVegetarianDishesByType --------------");
		System.out.println(getVegetarianDishesByType());
		System.out.println(" ------------------ mostCaloricPartitionedByVegetarian --------------");
		System.out.println(mostCaloricPartitionedByVegetarian());
		System.out.println(" ------------------ vegetarianMoreThan500Calories --------------");
		System.out.println(vegetarianMoreThan500Calories());
		System.out.println(" ------------------ Count vegetarian dishes --------------");
		System.out.println(countVegetarianDishes());
	}

	private static Map<Boolean, List<Dish>> getPartitionedMenu() {
		return menu.stream().collect(partitioningBy(Dish::isVegetarian));
	}

	private static List<Dish> getVegetarianMenu() {
		return getPartitionedMenu().get(true);
	}

	private static Map<Boolean, Map<Dish.Type, List<Dish>>> getVegetarianDishesByType() {
		return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
	}

	private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian() {
		return menu.stream().collect(partitioningBy(Dish::isVegetarian,
				collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

	}
	
	private static Map<Boolean, Map<Boolean, List<Dish>>> vegetarianMoreThan500Calories() {
		return menu.stream().collect(partitioningBy(Dish::isVegetarian,
				partitioningBy(dish -> dish.getCalories() > 500)));
	}
	
	/*private static Map<Boolean, Map<Boolean, List<Dish>>> vegetarianByType() {
		return menu.stream().collect(partitioningBy(Dish::isVegetarian,
				partitioningBy(Dish::getType)));
	}*/
	
	private static Map<Boolean, Long> countVegetarianDishes() {
		return menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
	}

}
