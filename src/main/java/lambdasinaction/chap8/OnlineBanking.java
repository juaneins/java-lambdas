package lambdasinaction.chap8;

public abstract class OnlineBanking {
	
	public void processCustomer(int id) {
		Customer c = Database.getCuistomerWithId(id);
		makeCustomerHappy(c);		
	}
	
	abstract void makeCustomerHappy(Customer c);
	
	static class Customer {
		
	}
	
	static class Database {
		static Customer getCuistomerWithId(int id) {
			return new Customer();
		}
	}

}
