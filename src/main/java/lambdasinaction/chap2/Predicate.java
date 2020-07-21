/**
 * 
 */
package lambdasinaction.chap2;

/**
 * @author Usuario
 *
 */
@FunctionalInterface
public interface Predicate<T> {
	boolean test(T t);
}
