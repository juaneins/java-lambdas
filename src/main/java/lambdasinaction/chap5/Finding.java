package lambdasinaction.chap5;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;

import lambdasinaction.chap4.Dish;
import lambdasinaction.chap4.Dish.Type;

public class Finding {
	
	public static void main(String[] args) {
		System.out.println(vegetarianDishes(Dish.menu));
		
		System.out.println("------------------------------");
		System.out.println("--------- Unique numbers --------------");
		List<Integer> integers = Arrays.asList(1, 2, 5, 2, 3, 4, 3, 7, 9, 6, 3, 1, 0, 7, 8, 8, 1, 2, 9, 9);
		
		getDistinctEvenInteger(integers).forEach(System.out::println);
				
		System.out.println("------------------------------");
		System.out.println("--------- Dishes by calories --------------");
		getDishesByCalories(Dish.menu, 300, 3, 2).forEach(System.out::println);
		
		System.out.println("------------------------------");
		System.out.println("--------- Dishes by type --------------");
		getDishesByType(Dish.menu, Type.MEAT, 2).forEach(System.out::println);
		System.out.println("------------------------------");
		System.out.println("--------- Dish name --------------");
		getDishesDescription(Dish.menu).forEach(System.out::println);
		System.out.println("------------------------------");
		System.out.println("--------- Dish name number of characters --------------");
		getNumberOfCharsDishesDescription(Dish.menu).forEach(System.out::println);
		System.out.println("------------------------------");
		System.out.println("--------- Dish flat map --------------");
		String [] arrayOfWords = {"Hello", "World"};
		//List<String> listOfWords = Arrays.asList(arrayOfWords);
		getUniqueCharacters(arrayOfWords).forEach(System.out::println);
		System.out.println("------------------------------");
		System.out.println("--------- Squares --------------");
		List<Integer> integersList = Arrays.asList(1,2,3,4,5);
		getSquare(integersList).forEach(System.out::println);
		System.out.println("------------------------------");
		System.out.println("--------- Ordered pairs --------------");
		List<Integer> list1 = Arrays.asList(1,2,3);
		List<Integer> list2 = Arrays.asList(3,4);
		List<int[]> result = getOrderedPairs(list1, list2);
		//To do imprimir los pares ordenados
		System.out.println("------------------------------");
		System.out.println("--------- Is vegetarian(any match) --------------");
		System.out.println(isVegetarian(Dish.menu));
		System.out.println("--------- Is healthy(all match) --------------");
		System.out.println(isHealthy(Dish.menu));
		System.out.println("--------- Is healthy2(none match) --------------");
		System.out.println(isHealthy2(Dish.menu));
		System.out.println("--------- Is there vegetarian food (Optional<T>) --------------");
		isThereAnyVegetarianDish(Dish.menu);
		System.out.println("--------- sum all calories old jdk --------------");
		System.out.println("Total calories: " + sumCalories(Dish.menu));
		System.out.println("--------- sum all calories reduce --------------");
		/*int sumaTotal = Dish.menu.stream().reduce(0, (acum, d) -> 
		acum + d.getCalories(), Integer::sum);
		System.out.println("Total calories: " + Dish.menu.stream().reduce(0, (total, d) -> 
			total + d.getCalories(), Integer::sum
		));*/
		System.out.println("Total of calories: " + sumCaloriesMap(Dish.menu));
	}
	
	public static List<Dish> vegetarianDishes(List<Dish> menu) {
		List<Dish> vegetarianDishes = menu.stream()
				.filter(Dish::isVegetarian)
				.collect(toList());
		
		return  vegetarianDishes;
		
	}
	
	public static List<Integer> getDistinctEvenInteger(List<Integer> numbers) {
		return numbers.stream()
				.filter(n -> n % 2 == 0)
				.distinct()
				.collect(toList());
	}
	
	public static List<Dish> getDishesByCalories(List<Dish> menu, int calories, int limit, int skip) {
		return menu.stream()
				.filter(dish -> dish.getCalories() > calories)
				.limit(limit)
				.skip(skip)
				.collect(toList());
	}
	
	public static List<Dish> getDishesByType(List<Dish> menu, Type dishType, int limit) {
		return menu.stream()
				.filter(d -> d.getType().equals(dishType))
				.limit(limit)
				.collect(toList());
		
	}
	
	public static List<String> getDishesDescription(List<Dish> menu) {
		return menu.stream()
				.map(Dish::getName)
				.collect(toList());
		
	}
	
	public static List<Integer> getNumberOfCharsDishesDescription(List<Dish> menu) {
		return menu.stream()
				.map(Dish::getName)
				.map(String::length)
				.collect(toList());
		
	}
	
	public static List<String> getUniqueCharacters(String[] listOfStrings) {
		Stream<String> streamOfWords = Arrays.stream(listOfStrings);
		return streamOfWords
				.map(word -> word.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(toList());
		
	}
	
	public static List<Integer> getSquare(List<Integer> numbers)	{
		return numbers.stream().map(n -> n * n).collect(toList());
	}
	
	public static List<int[]> getOrderedPairs(List<Integer> numbers1,List<Integer> numbers2) {
		return numbers1.stream()
				.flatMap(i -> numbers2.stream()
						.map(j -> new int[]{i,j}))
				.collect(toList());
	}
	
	public static boolean isVegetarian(List<Dish> menu) {
		return menu.stream().anyMatch(Dish::isVegetarian);
	}
	
	public static boolean isHealthy(List<Dish> menu) {
		return menu.stream().allMatch(d -> d.getCalories() < 1000);
	}
	
	public static boolean isHealthy2(List<Dish> menu) {
		return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
	}
	
	public static void isThereAnyVegetarianDish(List<Dish> menu) {
		menu.stream()
		.filter(Dish::isVegetarian)
		.findAny()
		.ifPresent(d -> System.out.println(d.getName()));		
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
}
