package com.maytwitt;

import java.awt.EventQueue;

public class Driver {
	
      
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAndGroupInfo u = UserAndGroupInfo.getInstance();
					u.init();
					AdminPanelWindow window = new AdminPanelWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
