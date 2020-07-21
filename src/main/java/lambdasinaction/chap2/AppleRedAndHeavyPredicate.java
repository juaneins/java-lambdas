/**
 * 
 */
package lambdasinaction.chap2;

/**
 * @author Usuario
 *
 */
public class AppleRedAndHeavyPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple a) {
		return "red".equals(a.getColor()) && (a.getWeight() > 150);
	}

}
