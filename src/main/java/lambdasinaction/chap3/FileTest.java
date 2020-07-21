/**
 * 
 */
package lambdasinaction.chap3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author juaneins_uio
 *
 */
public class FileTest {

	private static final String PATH = "src/main/resources/lambdasinaction/chap3/data.txt";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		System.out.println(new File(".").getAbsolutePath());

		System.out.println(new File(".").getCanonicalFile());

		System.out.println("The path is '" + PATH + "'");

		String result = processFileLimited();
		System.out.println(result);

		System.out.println("---");

		String oneLine = processFile((BufferedReader b) -> b.readLine());
		System.out.println("oneLine: " + oneLine);

		String twoLines = processFile((BufferedReader b) -> b.readLine() + " " + b.readLine());
		System.out.println("twoLines:" + twoLines);
		
		String threeLines = processFile((BufferedReader b) -> b.readLine() + " " + b.readLine()+ " " + b.readLine());
		System.out.println("twoLines:" + threeLines);

	}

	public static String processFileLimited() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
			return br.readLine();
		}
	}

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
			return p.process(br);
		}

	}

}
