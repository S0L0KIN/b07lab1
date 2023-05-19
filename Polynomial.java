public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = new double[1];
		//for (int i = 0; i < coefficients.length; i++) coefficients[i] = 0;
	}

	public Polynomial(double[] newCoefficients) {
		coefficients = newCoefficients;
	}

	public Polynomial add(Polynomial x) {
		int len = Math.max(coefficients.length, x.coefficients.length); //get length of new array
		double[] added = new double[len]; 
								// treat arrays different bc diff lengths
		for (int i = 0; i < coefficients.length; i++) { //add from first array
			added[i] += coefficients[i];
		}
		for (int i=0; i<x.coefficients.length; i++) { 	//add from second array
			added[i] += x.coefficients[i];
		}	
	
		Polynomial result = new Polynomial(added);
		return result; // return
	}

	public double evaluate(double x) {
		double ans = 0;
		for (int i = 0; i < coefficients.length; i++) {
			ans += (coefficients[i])*(Math.pow(x, i));
		}
		return ans;
	}

	public boolean hasRoot(double x) {
		double ans = evaluate(x);
		return (ans == 0);
	}
}
