/**
 * 
 */
package lambdasinaction.chap3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author juaneins_uio
 *
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
	public String process(BufferedReader reader) throws IOException;
}
