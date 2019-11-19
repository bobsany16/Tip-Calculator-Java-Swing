import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class handles the logic behind Tip Calculator
 * 
 * @author Bobby Nguyen
 * @version November 16 2019
 *
 */
public class TipModel {
	private double totalBill;
	private double tipPercentage;
	private double tipQuantum;
	private double tipOut;
	private List<ModelObserver> observers = new ArrayList<ModelObserver>();// Need instance variable for List of
																			// observers

	/**
	 * Adds parameter to list of observers
	 * 
	 * @param m ModelObserver
	 */
	public void addObserver(ModelObserver m) {
		observers.add(m);
	}

	/**
	 * To send "update(this)" to each observer in the list
	 */
	private void notifyObservers() {
		for (ModelObserver observe : observers) {
			observe.update(this);
		}
	}

	/**
	 * This method stores data to totalBill instance variable
	 * and calls notifyObservers()
	 * 
	 * @param amount value entered in GUI for total bill amount
	 */
	public void setTotalBill(double amount) {
		this.totalBill = amount;
		notifyObservers(); 

	}

	/**
	 * This method gets the total bill amount
	 * 
	 * @return totalBill total bill amount to be calculated on
	 */
	public double getTotalBill() {
		return totalBill;
	}

	/**
	 * This method stores percent data to tipPercentage
	 * and calls notifyObservers()
	 * 
	 * @param percent value entered in GUI for percentage
	 */
	public void setTipPercent(double percent) {
		this.tipPercentage = percent;
		this.tipPercentage /= 100;
		notifyObservers();
	}

	/**
	 * This method gets the tipPercentage
	 * 
	 * @return tipPercentage value of percentage user wants to tip
	 */
	public double getTipPercent() {

		return tipPercentage;
	}

	/**
	 * This method store quantum data to TipQuantum
	 * and calls notifyObservers()
	 * 
	 * @param quantum the value entered in GUI
	 */
	public void setTipQuantum(double quantum) {
		this.tipQuantum = quantum;
		notifyObservers();
	}

	/**
	 * This method gets the TipQuantum
	 * 
	 * @return tipQuantum the value of quantum user wants to round to
	 */
	public double getTipQuantum() {
		return tipQuantum;
	}

	/**
	 * Main method to calculate the tip amount
	 * 
	 * @param baseAmount the bill amount entered
	 * @param percentage the percentage user tip
	 * @throws IllegalArgumentException if baseAmount or percentage is negative
	 * @return the tip amount the result of calculation
	 */
	public double calcTip(double baseAmount, double percentage) {
		double result1 = 0.0;
		if ((baseAmount < 0.0) || (percentage < 0.0)) {
			throw new IllegalArgumentException("Can't compute due to negative values.");
		} else {
			result1 = baseAmount * percentage;
		}
		return result1;
	}

	/**
	 * This method gets the rounded tip amount after user enters in the amount to
	 * tip and percentage and quantum
	 * 
	 * @return tipOut the value of rounded tip
	 */
	public double getRoundedTip() {
		double tipCalculated = calcTip(getTotalBill(), getTipPercent());
		double overage = tipCalculated % getTipQuantum();
		if ((tipCalculated < 0) || (tipQuantum < 0)) {
			throw new IllegalArgumentException("Can't round due to negative value.");
		} else {
			if (overage >= (tipQuantum / 2)) {
				tipCalculated += (tipQuantum - overage);
			} else if (overage < (tipQuantum / 2)) {
				tipCalculated -= overage;
			}
		}

		tipOut = tipCalculated;
		return tipOut;
	}

	/**
	 * Method to format digits of rounded amount to a specific decimal point
	 * 
	 * @param num           the rounded tip amount
	 * @param decimalDigits the number of decimal digits to format number
	 * @see https://www.baeldung.com/java-round-decimal-number how to round number
	 *      to n decimal digits
	 * @return string result final amount when number is formatted
	 */
	public String formatNumber(double num, int decimalDigits) {
		if ((num < 0) || (decimalDigits < 0)) {
			throw new IllegalArgumentException("Can't format due to negative value.");
		}
		double scale = Math.pow(10, decimalDigits);
		String result = Double.toString(Math.round(num * scale) / scale);

		if ((result.substring(result.indexOf("."), result.length() - 1).length()) < decimalDigits) {
			for (int i = 1; i < decimalDigits; i++) {
				result += "0";
			}
		}
		return result;
	}

	/**
	 * This method calculates the effective percentage after tipping
	 * 
	 * @return effPercentOut the actual percentage after tipping formatted to 2
	 *         decimal places
	 */
	public double getEffectiveTipPercentage() {

		return (getRoundedTip() / getTotalBill()) * 100;
	}

}
