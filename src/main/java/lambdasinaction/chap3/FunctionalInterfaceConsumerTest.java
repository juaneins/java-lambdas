/**
 * 
 */
package lambdasinaction.chap3;

import java.util.Arrays;
import java.util.List;

/**
 * @author juaneins_uio
 *
 */
public class FunctionalInterfaceConsumerTest {
	
	public static void main(String[] args) {
		
		forEach(Arrays.asList(1,2,3,4,5,6,7,8,9), (Integer i) -> System.out.println(i));
		
	}
	
	@FunctionalInterface
	public interface Consumer<T> {
		void accept(T t);
	}
	
	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
			
		}
	}

}
