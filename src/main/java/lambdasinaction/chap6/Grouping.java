package lambdasinaction.chap6;
import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.dishTags;
import static lambdasinaction.chap6.Dish.menu;

import static java.util.Comparator.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class Grouping {
	
	enum CaloricLevel { DIET, NORMAL, FAT };

	public static void main(String[] args) {
		System.out.println("Dishes by type:" + dishesByType());
		System.out.println("Dishes by dishesByCaloricLevel:" + dishesByCaloricLevel());
		System.out.println("Dishes by dishesByTypeCaloricLevel:" + dishesByTypeCaloricLevel());
		System.out.println("Counting dishes by type: "  + countDishesByType());
		System.out.println("Most caloric by type: " + getMostCaloricByType());
		System.out.println("Most caloric by type 2: " + getMostCaloricByType2());
		System.out.println("Total calories by type: " + getTotalCaloriesByType());
		System.out.println("Caloric levels by type: " + getCaloricLevelsByType());
		System.out.println("Caloric levels by type 2: " + getCaloricLevelsByType2());
	}
	
	public static Map<Dish.Type, List<Dish>> dishesByType() {
		return menu.stream().collect(groupingBy(Dish::getType));
	}
	
	public static Map<CaloricLevel, List<Dish>> dishesByCaloricLevel() { 
		return menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() <= 400) return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
			else return CaloricLevel.FAT;
		}));
	}
	
	public static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel() {
		return menu.stream().collect(
				groupingBy(Dish::getType,
						groupingBy(dish -> {
							if (dish.getCalories() <= 400) return CaloricLevel.DIET;
							else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
							else return CaloricLevel.FAT;
						})
						)
				);
	}
	
	public static Map<Dish.Type, Long> countDishesByType() {
		return menu.stream().collect(
				groupingBy(Dish::getType,counting()));
	}
	
	public static Map<Dish.Type, Optional<Dish>> getMostCaloricByType() {
		return menu.stream().collect(
				groupingBy(Dish::getType, 
						maxBy(comparingInt(Dish::getCalories))));
	}
	
	public static Map<Dish.Type, Dish> getMostCaloricByType2() {
		return menu.stream().collect(
				groupingBy(Dish::getType, collectingAndThen(
						maxBy(comparingInt(Dish::getCalories)),
						Optional::get)));
	}
	
	public static Map<Dish.Type, Integer> getTotalCaloriesByType() {
		return menu.stream().collect(
				groupingBy(Dish::getType,summingInt(Dish::getCalories)));
	}
	
	public static Map<Dish.Type, Set<CaloricLevel>> getCaloricLevelsByType() {
		return menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}, toSet())));
	}
	
	public static Map<Dish.Type, Set<CaloricLevel>> getCaloricLevelsByType2() {
		return menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
			if (dish.getCalories() <= 400)
				return CaloricLevel.DIET;
			else if (dish.getCalories() <= 700)
				return CaloricLevel.NORMAL;
			else
				return CaloricLevel.FAT;
		}, toCollection(HashSet::new))));
	}

}
