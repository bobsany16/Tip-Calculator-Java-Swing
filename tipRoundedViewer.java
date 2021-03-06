import javax.swing.JTextField;

/**
 * This class acts as a viewer for rounded tip amount for the User
 * Interface/Controller
 * 
 * @author Bobby Nguyen
 * @version November 19, 2019
 *
 */
public class tipRoundedViewer implements ModelObserver {

	private JTextField tipOutPutField;

	/**
	 * Initializes outputField
	 * 
	 * @param myOutput JTextField for output of rounded tip amount
	 */
	public tipRoundedViewer(JTextField myOutput) {
		tipOutPutField = myOutput;
	}

	/**
	 * Method to update the rounded tip textfield once there are changes in the
	 * model
	 * 
	 * @param a The associated Tip Model
	 */
	@Override
	public void update(TipModel a) {

		double b = a.getRoundedTip();
		tipOutPutField.setText("$" + a.formatNumber(b, 2));

	}

}
