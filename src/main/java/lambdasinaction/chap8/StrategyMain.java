package lambdasinaction.chap8;

public class StrategyMain {

	public static void main(String[] args) {
		// classic
		Validator lowerCase = new Validator(new IsAllLowerCase());
		System.out.println("lowerCase.validate: " + lowerCase.validate("freP"));
		Validator numeric = new Validator(new IsNumeric());
		System.out.println("numeric.validate: " + numeric.validate("freP"));
		
		//lambda
		Validator lowerCase2 = new Validator((String s) -> s.matches("[a-z]+"));
		System.out.println("lowerCase2.validate: " + lowerCase2.validate("asdft"));
		Validator numeric2 = new Validator((String s) -> s.matches("[\\d+]"));
		System.out.println("numeric.validate 2: " + numeric2.validate("12350"));
		

	}
	
	public static interface ValidationStrategy {
		boolean execute(String s);
	}
	
	public static class IsAllLowerCase implements ValidationStrategy {
		@Override
		public boolean execute(String s) {
			// TODO Auto-generated method stub
			return s.matches("[a-z]+");
		}		
	}
	
	static private class IsNumeric implements ValidationStrategy {
		public boolean execute(String s) {
			return s.matches("\\d+");
		}
	}
	
	static private class Validator {
		private final ValidationStrategy strategy;

		public Validator(ValidationStrategy v) {
			this.strategy = v;
		}

		public boolean validate(String s) {
			return strategy.execute(s);
		}
	}
	
	

}
