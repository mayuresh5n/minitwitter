package com.maytwitt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class UserInfoWindow extends JFrame {

	private JPanel contentPane;
	JList list_1;
	private String userId;
	JTextArea textArea_2;
	JTextPane textPane;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoWindow frame = new UserInfoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	JTextArea textArea_1 ;
	public void init()
	{
	 
		AppUser user =getUserObject(getUserId());
		StringBuilder b = new StringBuilder();
		for(String s :user.getMessageList())
		{
			b.append(s + "\n");
		}
		textArea_1.setText(b.toString());		
		updatetextArea();
		
		/* DefaultListModel demoList = new DefaultListModel();
		 demoList.addElement(user.getMessageList().toString());
		 list_1.removeAll();
		 list_1.setModel(demoList);
		 this.invalidate();
	        this.validate();
	        this.repaint();*/
	}

	/**
	 * Create the frame.
	 */
	public UserInfoWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		  textPane = new JTextPane();
		textPane.setBounds(10, 21, 124, 20);
		  
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("Follow User");
		btnNewButton.setBounds(158, 21, 159, 23);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String user = textPane.getText().trim();
				
				System.out.println("))))))))))"+user);
				if(!user.isEmpty())
				{
				AppUser apUser = getUserObject(user);
				
				  if(apUser == null)
				  {
					  JOptionPane.showMessageDialog(UserInfoWindow.this, "Enter valid user to follow");
				  }
				  else
				  {
					  apUser.getUserFollowers().add(getUserId());
					  AppUser apUser1 = getUserObject(getUserId());
					  apUser1.getUsersFollowing().add(apUser.getUserID());
				  }
				  
				}
				updatetextArea();
			}
			});
		
		
		contentPane.add(btnNewButton);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBackground(Color.WHITE);
		list.setBounds(308, 82, -293, 34);
		contentPane.add(list);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 127, 124, 22);
		
		
		 
		
		contentPane.add(textArea);
		
		JButton btnNewButton_1 = new JButton("Message tweet");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String message = textArea.getText().toString();
				
						 updateFollowersMessageList(getUserId(),message);  
				
			}

			private void updateFollowersMessageList(String userId,String message) {
				AppUser apUser = getUserObject(userId);
				if(apUser!=null)
				{
					List<String> followers = apUser.getUserFollowers();
					for(String followerID : followers)
					{
						AppUser apUserFollower = getUserObject(followerID);
						apUserFollower.getMessageList().add(message);
						System.out.println("Message twitted to: " +followerID );
					}
				}
				
				
			}
			
			

	
		});
		
		
		btnNewButton_1.setBounds(158, 128, 159, 23);
		contentPane.add(btnNewButton_1);
		
		
		
	 
		
		list_1 = new JList();
		list_1.setValueIsAdjusting(true);
		list_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_1.setBounds(25, 235, 92, -30);
		
		 
		 
		contentPane.add(list_1);
		
		 textArea_1 = new JTextArea();
		textArea_1.setBounds(158, 175, 203, 45);
		
		
		
		contentPane.add(textArea_1);
		
		 textArea_2 = new JTextArea();
		 
		textArea_2.setBounds(161, 66, 200, 50);
		
	
		
		
		contentPane.add(textArea_2);
		
		JLabel lblCurrentlyFollowing = new JLabel("Currently Following");
		lblCurrentlyFollowing.setBounds(10, 71, 124, 14);
		contentPane.add(lblCurrentlyFollowing);
		
		JLabel lblMessagefeed = new JLabel("MessageFeed");
		lblMessagefeed.setBounds(10, 191, 124, 14);
		contentPane.add(lblMessagefeed);
	}
	
	
	public void updatetextArea()
	{
		AppUser apUser = getUserObject(userId);
		if(apUser!=null)
		{
			List<String> followers = apUser.getUsersFollowing();
			for(String followerID : followers)
			{
				textArea_2.setText(textArea_2.getText()+"\n"+followerID);
			}
		}
		
	}


	public AppUser getUserObject(String userid) {
		List<AppUser>  listOfUsers = UserAndGroupInfo.getAppUserList();
		
		for(AppUser a :listOfUsers)
		{
			if(userid.equals(a.getUserID()))
			{
		            return a;
			}
		}
		return null;
	}
}
