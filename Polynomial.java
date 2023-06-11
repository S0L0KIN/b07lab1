import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Polynomial {
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		coefficients = new double[1];
		exponents = new int[1];
	}

	public Polynomial(double[] newCoefficients, int[] newExponents) {
		coefficients = newCoefficients;
		exponents = newExponents;
	}

	public Polynomial(File f) {
		try {
			Scanner r = new Scanner(f);
			String poly = r.nextLine();
			r.close();

			poly = poly.replace("-", "+-");
			poly = poly.replace("x+", "x1+");
			poly = poly.replace("+x", "+1x");
			poly = poly.replace("-x", "-1x"); //this should make sure every polynomial has the same format
			if (poly.charAt(0) == '+') poly = poly.substring(1); //check first term isnt a '+'
			String[] broken = poly.split("\\+", 0);

			coefficients = new double[broken.length];
			exponents = new int[broken.length];
			
			String[] temp;
			for(int i=0; i<broken.length; i++) {
				temp = broken[i].split("x", 0);
				if (temp.length == 1) {
					coefficients[i] = Double.parseDouble(temp[0]);
					exponents[i] = 0;
				}
				else {
					coefficients[i] = Double.parseDouble(temp[0]);
					exponents[i] = Integer.parseInt(temp[1]);
				}
			}


		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
      			e.printStackTrace();
		}
	}
	
	public Polynomial add(Polynomial x) {
		int maxDeg = 0;
		int constant = 0;

		for (int i=0; i<this.exponents.length; i++) {
			if (this.exponents[i] > maxDeg) maxDeg = this.exponents[i];
			if (this.exponents[i] == 0) constant = 1;
		}
		for (int i=0; i<x.exponents.length; i++) {
			if (x.exponents[i] > maxDeg) maxDeg = x.exponents[i];
			if (x.exponents[i] == 0) constant = 1;
		}		//get max deg

		double[] tempCoeff = new double[maxDeg+constant];
		int[] tempExp = new int[maxDeg + constant];

		for(int i=0; i<this.exponents.length; i++) {
			tempCoeff[this.exponents[i]] += this.coefficients[i];
		}
		for(int i=0; i<x.exponents.length; i++) {
			tempCoeff[x.exponents[i]] += x.coefficients[i];
		}

		int values=0;
		for(int i=0; i<maxDeg+constant; i++) {
			if (tempCoeff[i] != 0) values++;
		}
		double[] newCoeff = new double[values];
		int[] newExp = new int[values];

		int j=0;
		for (int i=0; i<values; i++) {
			if (tempCoeff[i] != 0) {
				newCoeff[j] = tempCoeff[i];
				newExp[j] = i;
				j++;
			}
		}

		Polynomial result = new Polynomial(newCoeff, newExp);
		return result;

	}

	public double evaluate(double x) {
		double ans = 0;
		for (int i = 0; i < coefficients.length; i++) {
			ans += (coefficients[i])*(Math.pow(x, exponents[i]));
		}
		return ans;
	}

	public boolean hasRoot(double x) {
		double ans = evaluate(x);
		return (x == 0);
	}

	public Polynomial multiply(Polynomial x) {

		int maxDeg = 0;
		int curDeg = 0;
		for (int i=0; i<this.exponents.length; i++) {
			if (this.exponents[i] > maxDeg) maxDeg = this.exponents[i];
		}
		for (int i=0; i<x.exponents.length; i++) {
			if (x.exponents[i] > curDeg) curDeg = x.exponents[i];
		}
		maxDeg += curDeg;
		curDeg = 0;

		double[] tempCoeff = new double[maxDeg+1];

		for(int i=0; i<this.exponents.length; i++) {
			for (int j=0; j<x.exponents.length; j++) {
				curDeg = this.exponents[i] + x.exponents[j];
				tempCoeff[curDeg] += this.coefficients[i]*x.coefficients[j];
			}
		}

		int values=0;
		for(int i=0; i<maxDeg+1; i++) {
			if (tempCoeff[i] != 0) values++;
		}
		double[] newCoeff = new double[values];
		int[] newExp = new int[values];

		int j=0;
		for (int i=0; i<values; i++) {
			if (tempCoeff[i] != 0) {
				newCoeff[j] = tempCoeff[i];
				newExp[j] = i;
				j++;
			}
		}

		Polynomial poly = new Polynomial(newCoeff, newExp);
		return poly;
	}

	public void saveToFile(String filename) throws IOException {
		try {
			String[] attach = new String[coefficients.length];

			for (int i=0; i<attach.length; i++) {
				if(exponents[i] == 0) { 
					attach[i] = String.valueOf(coefficients[i]);
				}
				else {
					attach[i] = String.valueOf(coefficients[i]) + "x" + String.valueOf(exponents[i]);
				}
			}
			String poly = String.join("+", attach);
			poly = poly.replace("-1x", "-x");
			poly = poly.replace("+1x", "+x");
			poly = poly.replace("x1+", "x+");
			poly = poly.replace("+-", "-");

			FileWriter writer = new FileWriter(filename);
			writer.write(poly);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
      			e.printStackTrace();
		}
		
	}
}
