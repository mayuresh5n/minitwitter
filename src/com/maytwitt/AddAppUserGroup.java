package com.maytwitt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAppUserGroup extends JFrame {

	private JPanel contentPane;
	private String userGroupId;
	JComboBox comboBox;
	 AdminPanelWindow adm;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { AddAppUserGroup frame = new
	 * AddAppUserGroup(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	public AdminPanelWindow getAdm() {
		return adm;
	}

	public void setAdm(AdminPanelWindow adm) {
		this.adm = adm;
	}

	/**
	 * Create the frame.
	 */
	public AddAppUserGroup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelectParentGroup = new JLabel("Select Parent Group");
		lblSelectParentGroup.setBounds(42, 70, 138, 14);
		contentPane.add(lblSelectParentGroup);

		DefaultComboBoxModel dcm = new DefaultComboBoxModel();

		for (AppUserGroup apg : UserAndGroupInfo.getAppUserGroupList()) {
			// comboBox.addItem(new String(apg.getUserGroupID()));
			dcm.addElement(new String(apg.getUserGroupID()));
		}

		comboBox = new JComboBox(dcm);
		comboBox.setBounds(170, 67, 173, 20);
		contentPane.add(comboBox);

		JButton btnCreateGroup = new JButton("Create Group");
		btnCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Implementing Observer pattern to update parent window
				final MessageMainWindow observable = new MessageMainWindow();
		        observable.addObserver(getAdm());
				
				
				Object item = comboBox.getSelectedItem();
				String slectedGroup =(String)item;
		 
				AppUserGroup apGrp = new AppUserGroup();
				apGrp.setParentGroupID(slectedGroup);
				apGrp.setUserGroupID(getUserGroupId());
				
				UserAndGroupInfo.addAppUserGroupList(apGrp);

				observable.changeData(apGrp);
				dispose(); 

			}
		});
		btnCreateGroup.setBounds(146, 161, 173, 23);
		contentPane.add(btnCreateGroup);
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

}
