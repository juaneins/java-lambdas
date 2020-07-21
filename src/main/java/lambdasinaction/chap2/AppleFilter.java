/**
 * 
 */
package lambdasinaction.chap2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Usuario
 *
 */
public class AppleFilter {
	
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if(p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
		for (Apple apple : inventory) {
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}
	
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<T>();
		for (T e : list) {
			if(p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}

}
