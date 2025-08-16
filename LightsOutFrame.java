package assign09;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LightsOutFrame extends JFrame implements ActionListener {
	private LightsOutButton[][] lightArray;
	private JButton setLight;
	private JButton setUp;
	private boolean setUpMode;
	
	public LightsOutFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel gridPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(4, 4));
	
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				lightArray[row][col] = new LightsOutButton(row, col);
				lightArray[row][col].addActionListener(this);
				gridPanel.add(lightArray[row][col]);
			}
		}
		
		JPanel controlPanel = new JPanel(new FlowLayout());
		
		JButton randomizeButtons = new JButton("Randomize");
		randomizeButtons.addActionListener(this);

		JButton manualSetUp = new JButton("Manual Setup");
		manualSetUp.addActionListener(this);
		
		controlPanel.add(manualSetUp);
		controlPanel.add(randomizeButtons);
		
		setContentPane(gridPanel);
		setTitle("Lights Out Game");
		setSize(600, 600);
	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
