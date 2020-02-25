/**
 * 
 */
package lambdasinaction.chap2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lambdasinaction.chap2.Apple;

/**
 * @author Usuario
 *
 */
public class AppleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<Apple> inventory = Arrays.asList(new Apple(80, "green"),new Apple(180, "red"), new Apple(225, "red"), new Apple(155, "green"));
		
		List<Apple> greenApples = AppleFilter.filter(inventory, new AppleColorPredicate());		
		System.out.println(greenApples);
		
		List<Apple> heavyApples = AppleFilter.filter(inventory, new AppleWeightPredicate());
		System.out.println(heavyApples);
		
		List<Apple> redHeavyApples = AppleFilter.filter(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redHeavyApples);
		
		AppleFilter.prettyPrintApple(inventory, new AppleFancyFormatter());
		AppleFilter.prettyPrintApple(inventory, new AppleSimpleFormatter());
		
		List<Apple> redApples = AppleFilter.filter(inventory, new ApplePredicate() {		
			@Override
			public boolean test(Apple a) {
				return "red".equals(a.getColor());
			}
		});	
		
		System.out.println(redApples);
		
		List<Apple> result = AppleFilter.filter(inventory, (Apple apple) -> "green".equals(apple.getColor()));
		System.out.println(result);
		
		List<Apple> redGenericApples = AppleFilter.filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
		System.out.println(redGenericApples);
		
		List<Integer> numbers = Arrays.asList(1,3,5,7,8,9,10,12,14,16,18,20);
		
		List<Integer> evenNumbers = AppleFilter.filter(numbers, (Integer i) -> i%2 == 0);
		System.out.println(evenNumbers);
		
		/*
		 * inventory.sort(new Comparator<Apple>() {
		 * 
		 * @Override public int compare(Apple o1, Apple o2) { return
		 * o1.getWeight().compareTo(o2.getWeight()); }
		 * 
		 * });
		 */
		System.out.println(inventory);
		
		inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		
		System.out.println(inventory);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello world!");
				
			}
		});
		t.run();
		
		Thread t1 = new Thread(() -> System.out.println("Hello world!!!"));
		t1.run();

	}

}
