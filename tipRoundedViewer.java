import javax.swing.JTextField;

/**
 * This class acts as a viewer for rounded tip amount for the Controller
 * 
 * @author Bobby Nguyen
 * @version April 10th 2019
 *
 */
public class tipRoundedViewer implements ModelObserver {

	private JTextField outputField;

	/**
	 * Initializes outputField
	 * 
	 * @param myOutput JTextField for output of rounded tip amount
	 */
	public tipRoundedViewer(JTextField myOutput) {
		outputField = myOutput;
	}

	/**
	 * Method to update the rounded tip textfield once there are changes in the
	 * model
	 * 
	 * @param TipModel a a TipModel
	 */
	@Override
	public void update(TipModel a) {
		
		double b = a.getRoundedTip();
		outputField.setText("$" + a.formatNumber(b, 2));

	}

}
