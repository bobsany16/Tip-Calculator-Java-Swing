import javax.swing.JTextField;

/**
 * This class acts as a viewer for Effective Percentage Amount for the User
 * Interface/Controller
 * 
 * @author Bobby Nguyen
 * @version April 10th 2019
 */
public class effPercentViewer implements ModelObserver {
	private JTextField effPercentOutField;

	/**
	 * Initializes effPercentOutField
	 * 
	 * @param myOutput2 JTextField for output of effective percentage amount
	 */
	public effPercentViewer(JTextField myOutput2) {
		effPercentOutField = myOutput2;
	}

	/**
	 * Method to update the textfield once there are changes in the model
	 * 
	 * @param h The associated Tip Model
	 */
	@Override
	public void update(TipModel h) {
		double c = h.getEffectiveTipPercentage();
		effPercentOutField.setText(h.formatNumber(c, 2) + "%");

	}

}
