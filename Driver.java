import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) throws IOException {
		double [] coeff = {1, 1, 2};
		int [] expo = {0, 2, 1};
		Polynomial poly1 = new Polynomial(coeff, expo);
		System.out.println("Poly1 is:");
		for (int i = 0; i < poly1.coefficients.length; i++) {
			System.out.println(poly1.coefficients[i] + "x" + poly1.exponents[i]);
		}

		System.out.println("\nREAD FILE");
		File file = new File("C:\\Users\\Nikolos\\b07lab1\\polyin.txt");
		Polynomial poly2 = new Polynomial(file);
		System.out.println("Poly2 is:");
		for (int i = 0; i < poly2.coefficients.length; i++) {
			System.out.println(poly2.coefficients[i] + "x" + poly2.exponents[i]);
		}
		
		Polynomial added = poly1.add(poly2);
		System.out.println("\nlength is:" + added.exponents.length + "\nAdding both polynomials gives:");
		for (int i = 0; i < added.coefficients.length; i++) {
			System.out.println(added.coefficients[i] + "x" + added.exponents[i]);
		}

		Polynomial mult = poly1.multiply(poly2);
		System.out.println("\nMultiplying both polynomials gives:");
		for (int i = 0; i < mult.coefficients.length; i++) {
			System.out.println(mult.coefficients[i] + "x" + mult.exponents[i]);
		}		

		System.out.println("poly1(2) = " + poly1.evaluate(2));
		
		System.out.println("\nWRITE FILE");
		mult.saveToFile("C:\\Users\\Nikolos\\b07lab1\\polyout.txt");
		System.out.println("Done");
	}
}