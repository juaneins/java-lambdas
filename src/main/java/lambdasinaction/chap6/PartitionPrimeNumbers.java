package lambdasinaction.chap6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitionPrimeNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" -------------- Primes -------------------");
		System.out.println(partitionPrimes(60));

	}
	
	private static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot)
				.noneMatch(i -> candidate % i == 0);
	}
	
	
	private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n).boxed()
				.collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
	}
	

}
