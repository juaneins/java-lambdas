package lambdasinaction.chap7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {

	/*
	 * public static void main(String[] args) {
	 * //System.out.println("Sequential sum: " + sequentialSum(1000000));
	 * //System.out.println("Interative sum: " + iterativeSum(1000000));
	 * //System.out.println("Parallel sum: " + parallelSum(1000000));
	 * System.out.println("availableProcessors: " +
	 * Runtime.getRuntime().availableProcessors()); System.out.println("maxMemory: "
	 * + Runtime.getRuntime().maxMemory()); System.out.println("totalMemory: " +
	 * Runtime.getRuntime().totalMemory()); System.out.println("freeMemory: " +
	 * Runtime.getRuntime().freeMemory());
	 * System.out.println("Sequential sum done in: " +
	 * measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");
	 * System.out.println("Interative sum done in: " +
	 * measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");
	 * System.out.println("parallelSum sum done in: " +
	 * measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs");
	 * System.out.println("rangedSum sum done in: " +
	 * measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + " msecs");
	 * System.out.println("parallelRangedSum sum done in: " +
	 * measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + " msecs");
	 * System.out.println("sideEffectSum sum done in: " +
	 * measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000) + " msecs");
	 * System.out.println("sideEffectParallelSum sum done in: " +
	 * measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000) +
	 * " msecs");
	 * 
	 * }
	 */
	
	public static long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1)
				.limit(n)
				.reduce(0L, Long::sum);
	}
	
	public static long parallelSum(long n) {
		return Stream.iterate(1L, i -> i + 1)
				.limit(n)
				.parallel()
				.reduce(0L, Long::sum);
	}
	
	
	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		
		return result;
	}
	
	public static long rangedSum(long n) {
		return LongStream.rangeClosed(1, n)
				.reduce(0L, Long::sum);
	}
	
	public static long parallelRangedSum(long n) {
		return LongStream.rangeClosed(1, n)
				.parallel()
				.reduce(0L, Long::sum);
	}
	
	
	
	/*
	 * public static long measureSumPerf(Function<Long, Long> adder, long n) { long
	 * fastest = Long.MAX_VALUE; for (int i = 0; i < 10; i++) { long start =
	 * System.nanoTime(); long sum = adder.apply(n); long duration =
	 * (System.nanoTime() - start) / 1_000_000; System.out.println("Result: " +
	 * sum); if (duration < fastest) { fastest = duration; } } return fastest; }
	 */
	
	public static long sideEffectSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).forEach(accumulator::add);
		return accumulator.total;
	}
	
	public static long sideEffectParallelSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n)
		.parallel()
		.forEach(accumulator::add);
		return accumulator.total;
	}
	
	public static class Accumulator {
		public long total = 0;
		public void add(long value) {
			total += value;
		}
	}

}
