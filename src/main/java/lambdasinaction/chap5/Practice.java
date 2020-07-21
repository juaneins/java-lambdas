package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice {

	public static void main(String[] args) {
		
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
			
		List<Transaction> transactions = Arrays.asList(
	            new Transaction(brian, 2011, 300), 
	            new Transaction(raoul, 2012, 1000),
	            new Transaction(raoul, 2011, 400),
	            new Transaction(mario, 2012, 710),	
	            new Transaction(mario, 2012, 700),
	            new Transaction(alan, 2012, 950)
	        );	
		
		System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high).");
		getTransactionsByYear(transactions, 2011).forEach(System.out::println);
		System.out.println("2. What are all the unique cities where the traders work?");
		getUniqueCities(transactions).forEach(System.out::println);
		System.out.println("3. Find all traders from Cambridge and sort them by name.");
		getTradersByCity(transactions, "Cambridge").forEach(System.out::println);
		System.out.println("4. Return a string of all traders’ names sorted alphabetically.");
		System.out.println(getTraderNames(transactions));
		System.out.println("5. Are any traders based in Milan?");
		System.out.println(areThereTradersInCity(transactions, "Milan"));
		System.out.println("6. Print all transactions’ values from the traders living in Cambridge.");
		getTransactionValuesByCity(transactions, "Cambridge").forEach(System.out::println);
		System.out.println("7. What’s the highest value of all the transactions?");
		getMaxValueTransaction(transactions).ifPresent(System.out::println);
		System.out.println("8. Find the transaction with the smallest value.");
		getMinValueTransaction(transactions).ifPresent(System.out::println);
	}
	
	public static List<Transaction> getTransactionsByYear(List<Transaction> transactions, int year) {
		return transactions.stream()
		.filter(t -> t.getYear() == year)
		.sorted(Comparator.comparing(Transaction::getValue))
		.collect(Collectors.toList());
	}
	
	public static List<String> getUniqueCities(List<Transaction> transactions) {
		return transactions.stream()				
				.map(t -> t.getTrader().getCity())
				.distinct()
				.collect(Collectors.toList());
	}
	
	public static List<Trader> getTradersByCity(List<Transaction> transactions, String city) {
		return transactions.stream()
				.filter(t -> t.getTrader().getCity().equals(city))		
				.map(Transaction::getTrader)
				.distinct()
				.sorted(Comparator.comparing(Trader::getName))
				.collect(Collectors.toList());		
	}
	
	public static String getTraderNames(List<Transaction> transactions) {
		return transactions.stream()
				.map(t -> t.getTrader().getName())
				.distinct()
				.sorted()
				//.collect(Collectors.toList());
				.reduce(" ", (n1, n2) -> n1 + " " + n2);
				
	}
	
	public static boolean areThereTradersInCity(List<Transaction> transactions, String city) {
		return transactions.stream().anyMatch(t -> t.getTrader().getCity().equals(city));
	}
	
	public static List<Integer> getTransactionValuesByCity(List<Transaction> transactions, String city) {
		return transactions.stream()
				.filter(t -> t.getTrader().getCity().equals(city))
				.map(Transaction::getValue)
				.collect(Collectors.toList());
	}
	
	public static Optional<Integer> getMaxValueTransaction(List<Transaction> transactions) {
		return transactions.stream()
				.map(t -> t.getValue())
				.reduce(Integer::max);
				
	}
	
	public static Optional<Integer> getMinValueTransaction(List<Transaction> transactions) {
		/*return transactions.stream()
				.map(t -> t.getValue())
				.reduce(Integer::min);*/
		return transactions.stream()
				.min(Comparator.comparing(Transaction::getValue))
				.map(Transaction::getValue);
	}
	

}
