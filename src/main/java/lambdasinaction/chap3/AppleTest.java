package lambdasinaction.chap3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lambdasinaction.chap2.Predicate;
import lambdasinaction.chap3.Apple;

public class AppleTest {

	public static void main(String[] args) {
		// Filtering with lambdas
		List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"), new Apple(99, "green"));
		// filter green apples
		List<Apple> greenApples = AppleFilter.filter(inventory, (Apple a) -> "green".equals(a.getColor()));
		
		System.out.println(greenApples);
		System.out.println(inventory);
		
		System.out.println("---------------------- Comparator ----------------");
		//inventory.sort(new AppleComparator());
		//inventory.sort((Apple a1,Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		//inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
		//Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
		//inventory.sort(Comparator.comparing((a) -> a.getWeight()));
		inventory.sort(Comparator.comparing(Apple::getWeight));
		inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
		System.out.println(inventory);
		
		List<Apple> redApples = AppleFilter.filter(inventory, a -> "red".equals(a.getColor()));
		
		//Predicate<Apple> notRedApple = redApples
		
	}
}
