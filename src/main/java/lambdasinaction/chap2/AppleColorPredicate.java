/**
 * 
 */
package lambdasinaction.chap2;

/**
 * @author Usuario
 *
 */
public class AppleColorPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple a) {
		return "green".equals(a.getColor());
	}

}
