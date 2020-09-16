package Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class StatusPanel extends JPanel {
	private JLabel turnLabel;
	private JLabel moveLabel;
	private JButton endTurnButton;	
	
	public StatusPanel(int w, int h) {
		setLayout(new GridLayout(1,3));
		setPreferredSize(new Dimension(w, h));
		setBackground(Color.BLACK);
		turnLabel = new JLabel("Player 1's Turn", SwingConstants.CENTER);
		turnLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		turnLabel.setForeground(Color.WHITE);
		moveLabel = new JLabel("Moves: 0", SwingConstants.CENTER);	
		moveLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
		moveLabel.setForeground(Color.WHITE);
		endTurnButton = new JButton("End Turn");
		endTurnButton.setBackground(Color.WHITE);
		endTurnButton.setForeground(Color.BLACK);
		endTurnButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		add(turnLabel);
		add(moveLabel);
		add(endTurnButton);
	}
}