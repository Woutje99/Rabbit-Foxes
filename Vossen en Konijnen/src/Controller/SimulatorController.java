package Controller;

import Main.*;
import Model.*;
import View.*;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;




public class SimulatorController {
	private SimulatorView simview;
	private Simulator simulator;
	private boolean gestart = false;



	public SimulatorController (SimulatorView simview, Simulator simulator)
    {
        this.simview=simview;
        this.simulator=simulator;
        
    }	



public void buttonEvent() {
	simview.getOneStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			simulator.simulateOneStep();
		}});
	
	simview.getHunderdStep().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Thread() {
				public void run() {
					simulator.simulate(100);
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
	


