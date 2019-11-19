import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * User interface for tip calculator
 * 
 * @author Bobby Nguyen
 * @version April 10th 2019
 *
 */
public class TipCalUI implements ActionListener {
	private JFrame frame;
	private JButton calculate;
	private JTextField totalAmountToTip;
	private JTextField percentField;
	private JTextField roundField;
	private JTextField tipOutField;
	private JTextField percentTipOutField;
	private tipRoundedViewer roundedTip;
	private effPercentViewer effPercentOut;
	private TipModel m;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TipCalUI window = new TipCalUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public TipCalUI() {
		m = new TipModel();
		initialize();
		calculate.addActionListener(this);
		totalAmountToTip.addActionListener(this);
		percentField.addActionListener(this);
		roundField.addActionListener(this);
		roundedTip = new tipRoundedViewer(tipOutField);
		effPercentOut = new effPercentViewer(percentTipOutField);
		m.addObserver(roundedTip);
		m.addObserver(effPercentOut);
	}

	public void initialize() {

		frame = new JFrame("Tip Calculator");
		frame.setBounds(650, 325, 350, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel amtLabel = new JLabel("Amount:", JLabel.CENTER);
		JPanel l = new JPanel();
		l.setBounds(75, 10, 150, 40);
		totalAmountToTip = new JTextField();
		totalAmountToTip.setPreferredSize(new Dimension(75, 30));
		totalAmountToTip.setActionCommand("Enter");
		l.add(amtLabel);
		l.add(totalAmountToTip);

		JLabel percentLabel = new JLabel("Percent:", JLabel.CENTER);
		JPanel h = new JPanel();
		h.setBounds(75, 60, 150, 40);
		percentField = new JTextField();
		percentField.setPreferredSize(new Dimension(75, 30));
		percentField.setActionCommand("Enter2");
		h.add(percentLabel);
		h.add(percentField);

		JLabel roundLabel = new JLabel("Round to:", JLabel.CENTER);
		JPanel a = new JPanel();
		a.setBounds(75, 110, 150, 40);
		roundField = new JTextField();
		roundField.setPreferredSize(new Dimension(75, 30));
		roundField.setActionCommand("Enter3");
		a.add(roundLabel);
		a.add(roundField);

		calculate = new JButton("Calculate");
		calculate.setBounds(75, 160, 200, 40);
		calculate.addActionListener(this);
		calculate.setActionCommand("calculate");

		JLabel tipOutLabel = new JLabel("Tip is:", JLabel.CENTER);
		JPanel b = new JPanel();
		b.setBounds(75, 210, 150, 40);
		tipOutField = new JTextField();
		tipOutField.setPreferredSize(new Dimension(75, 30));
		b.add(tipOutLabel);
		b.add(tipOutField);

		JLabel percentTipOutLabel = new JLabel("You tipped:", JLabel.CENTER);
		JPanel c = new JPanel();
		c.setBounds(75, 260, 180, 70);
		percentTipOutField = new JTextField();
		percentTipOutField.setPreferredSize(new Dimension(75, 30));
		c.add(percentTipOutLabel);
		c.add(percentTipOutField);

		frame.getContentPane().add(l);
		frame.getContentPane().add(h);
		frame.getContentPane().add(a);
		frame.getContentPane().add(calculate);
		frame.getContentPane().add(b);
		frame.getContentPane().add(c);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// One for each field (named)
		// get the text from the field
		// convert it to a double
		// "set" the appropriate method of the model

		if (arg0.getActionCommand().equals("calculate")) {
			m.setTotalBill(Double.parseDouble(totalAmountToTip.getText()));
			m.setTipPercent(Double.parseDouble(percentField.getText()));
			m.setTipQuantum(Double.parseDouble(roundField.getText()));

		}
	}
}
