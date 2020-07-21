/**
 * 
 */
package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.List;

import lambdasinaction.chap3.Apple;
import lambdasinaction.chap3.ApplePredicate;

/**
 * @author juaneins_uio
 *
 */
public class AppleFilter {
	
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	} 

}
