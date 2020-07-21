/**
 * 
 */
package lambdasinaction.chap2;

/**
 * @author Usuario
 *
 */
public class AppleSimpleFormatter implements AppleFormatter {

	@Override
	public String accept(Apple a) {
		return "An apple of " + a.getWeight() + "g";
	}

}
