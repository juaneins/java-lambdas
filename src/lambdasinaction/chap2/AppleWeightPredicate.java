/**
 * 
 */
package lambdasinaction.chap2;

/**
 * @author Usuario
 *
 */
public class AppleWeightPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple a) {
		return a.getWeight() > 150;
	}

}
