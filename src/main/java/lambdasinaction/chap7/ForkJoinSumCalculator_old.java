package lambdasinaction.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator_old extends RecursiveTask<Long> {
	
	private final long[] numbers;
	private final int start;
	private final int end;
	
	public static final long THRESHOLD = 10_000;
	

	public ForkJoinSumCalculator_old(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	private ForkJoinSumCalculator_old(long[] numbers, int start, int end) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		int lenght = end -start;
		if (lenght <= THRESHOLD) {
			computeSequentially();
		}
		ForkJoinSumCalculator_old leftTask = 
				new ForkJoinSumCalculator_old(numbers, start, start + lenght / 2);		
		leftTask.fork();
		
		ForkJoinSumCalculator_old rightTask = 
				new ForkJoinSumCalculator_old(numbers, start + lenght / 2, end);
		
		long rightResult = rightTask.compute();
		long leftResult = leftTask.join();
		return leftResult + rightResult;	
	}

	private long computeSequentially() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum+= numbers[i];
		}
		return sum;
	}
	
	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator_old(numbers);
		return new ForkJoinPool().invoke(task);
	}

}
