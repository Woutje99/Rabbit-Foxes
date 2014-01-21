package controller;

import main.*;
import model.*;
import view.*;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;




public class SimulatorController {
	private SimulatorView simview;
	private Simulator simulator;
	private boolean gestart = false;
	private CircleStats circlestatsview;

	public SimulatorController (SimulatorView simview, Simulator simulator, CircleStats circlestatsview)
    {
        this.simview=simview;
        this.simulator=simulator;
        this.circlestatsview = circlestatsview;
    }	



public void buttonEvent() {
	simview.getOneStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			simulator.simulateOneStep();
			circlestatsview.repaint();
		}});
	
	simview.getHunderdStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Thread() {
				public void run() {
					simulator.simulate(100);
					circlestatsview.repaint();
				}
			}.start();
			
		}
	});
	
	simview.getGiveStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Thread() {
				public void run() {
					String s = (String) JOptionPane.showInputDialog(null,
							"Aantal stappen");
					int steps = Integer.parseInt(s);
					simulator.simulate(steps);
					circlestatsview.repaint();
				}
			}.start();
		}
	});
	
	/*
	leftPanel.add(oneStep); x
	leftPanel.add(hundredStep); x
	leftPanel.add(reset);
	leftPanel.add(start); x
	leftPanel.add(stop);
	leftPanel.add(giveStep); x
	*/
	
	simview.getStartStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
    		new Thread() {   					    		   			
				public void run() {
					gestart = true;
					while(gestart){
    					simulator.simulate(1);
    					circlestatsview.repaint();
					}
				
			}
		}.start();
		}
	});
	
	simview.getStopStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			gestart = false;
		}
	});
	
	simview.getResetStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			simulator.reset();
		}
	});
}
}
	


