package com.maytwitt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAppUser extends JFrame  {

	private JPanel contentPane;
    private String userID;
    JComboBox comboBox;
    AdminPanelWindow adm;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAppUser frame = new AddAppUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public AdminPanelWindow getAdm() {
		return adm;
	}

	public void setAdm(AdminPanelWindow adm) {
		this.adm = adm;
	}

	/**
	 * Create the frame.
	 */
	public AddAppUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultComboBoxModel dcm = new DefaultComboBoxModel();
	
		for(AppUserGroup apg : UserAndGroupInfo.getAppUserGroupList())
		{
		  // comboBox.addItem(new String(apg.getUserGroupID()));
			dcm.addElement(new String(apg.getUserGroupID()));
		}
		comboBox = new JComboBox(dcm);
		comboBox.setBounds(182, 60, 166, 20);
		contentPane.add(comboBox);
		
		JLabel lblSelectGroup = new JLabel("Select Group");
		lblSelectGroup.setBounds(21, 63, 130, 14);
		contentPane.add(lblSelectGroup);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Observer is called
				final MessageMainWindow observable = new MessageMainWindow();
		        observable.addObserver(getAdm());
				
				Object item = comboBox.getSelectedItem();
				String slectedGroup =(String)item;
				
				AppUser newUser = new AppUser();
				newUser.setUserID(getUserID());
				newUser.setUserGrpID(slectedGroup);
				newUser.setCreationTime(System.currentTimeMillis());
				
				UserAndGroupInfo.getAppUserList().add(newUser);
				
				observable.changeData(newUser);
				System.out.println("New user added to list : " + getUserID());
				System.out.println("User list size: "+ UserAndGroupInfo.getAppUserList().size());
				
				
				dispose(); 
			}
		});
		btnCreate.setBounds(124, 149, 89, 23);
		contentPane.add(btnCreate);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	 
	
}
