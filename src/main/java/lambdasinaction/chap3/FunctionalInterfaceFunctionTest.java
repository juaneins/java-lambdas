/**
 * 
 */
package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lambdasinaction.chap2.Predicate;

/**
 * @author juaneins_uio
 *
 */
public class FunctionalInterfaceFunctionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());

		System.out.println(l);
		System.out.println("-----------------------------------");
		
		IntPredicate evenNumbers = (int i) -> i % 2 == 0;
		System.out.println(evenNumbers.test(1000));
		
		Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
		System.out.println(oddNumbers.test(1000));
		
		

	}

	@FunctionalInterface
	public interface Function<T, R> {
		R apply(T t);
	}
	
	@FunctionalInterface
	public interface IntPredicate {
		boolean test(int t);
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T s : list) {
			result.add(f.apply(s));
		}
		return result;
	}

}
