package com.maytwitt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class AdminPanelWindow extends Driver implements Observer{

	protected JFrame frame;
	JScrollPane scrollPane;
	protected  List<AppUser> appUserList ;
	protected  List<AppUserGroup> appUserGroupList;
	/**
	 * Create the application.
	 */
	public AdminPanelWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame  = new JFrame(); 
		frame.setBounds(100, 100, 536, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(2, 0, 189, 373);
		frame.getContentPane().add(scrollPane);

		JTree tree = updateJtree(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(231, 15, 86, 23);
		textArea.setToolTipText("Enter User Name");
		frame.getContentPane().add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(231, 51, 86, 23);
		textArea_1.setToolTipText("Enter Group Name");
		frame.getContentPane().add(textArea_1);

		JButton btnNewButton = new JButton("Add User");
		btnNewButton.setBounds(327, 16, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textArea.getText().trim().equals("") && !isUserAlreadyPresent(textArea.getText().trim()))
				{
				AddAppUser frame = new AddAppUser();				 
				frame.setUserID(textArea.getText());
				frame.setAdm(AdminPanelWindow.this);
				frame.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Enter valid /non duplicate userName");
				}
			}

			
		});
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Add Group");
		btnNewButton_1.setBounds(327, 51, 97, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textArea_1.getText().trim().equals("") && !isUserGroupAlreadyPresent(textArea.getText().trim()))
				{
				
				AddAppUserGroup frame = new AddAppUserGroup();
				frame.setVisible(true);
				frame.setUserGroupId(textArea_1.getText().trim());
				frame.setAdm(AdminPanelWindow.this);
				
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Enter valid /non duplicate user Group Name");
				}
				
			}

			
		});
		frame.getContentPane().add(btnNewButton_1);

		JButton btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				openUserInformationPage();

			}

			private void openUserInformationPage() {
				if (null == tree.getSelectionPath() || !isValidSelection()) {
					JOptionPane.showMessageDialog(frame, "Select user from the tree");
				}
				else
				{
					UserInfoWindow frame = new UserInfoWindow();
					DefaultMutableTreeNode t =(DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				
					AppUser u =(AppUser) t.getUserObject();
					frame.setUserId(u.getUserID());
					 
					frame.init();
					System.out.println("User select is : "+ u.getUserID());
					frame.setVisible(true);
				}
			}

			private boolean isValidSelection() {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				if (node.getUserObject() instanceof AppUser) {
					return true;
				}
				return false;
			}
		});
		btnOpenUserView.setBounds(259, 98, 115, 23);
		frame.getContentPane().add(btnOpenUserView);

		JButton btnNewButton_2 = new JButton("Total Users");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Implementing VisitorDesign pattern
				AppUser u = new AppUser();
				AddDataVisitor adVisitor = new AddDataVisitor();
				JOptionPane.showMessageDialog(frame, "Total User Count : " +adVisitor.visit(u));
			}
		});
		btnNewButton_2.setBounds(201, 168, 98, 23);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnTotalGroups = new JButton("Total Groups");
		btnTotalGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Implementing VisitorDesign pattern
				AppUserGroup userGroup = new AppUserGroup();
				AddDataVisitor adVisitor = new AddDataVisitor();
				JOptionPane.showMessageDialog(frame, "Total UserGroup Count : " +adVisitor.visit(userGroup));
			}
		});
		btnTotalGroups.setBounds(309, 168, 115, 23);
		frame.getContentPane().add(btnTotalGroups);

		JButton btnTotalMessages = new JButton("Total Messages");
		btnTotalMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer count = 0;
				for(AppUser a: UserAndGroupInfo.getAppUserList())
				{
					count = count+a.getMessageList().size();
				}
				JOptionPane.showMessageDialog(frame, "Total Message Count : " +count);
			}
		});
		btnTotalMessages.setBounds(202, 227, 97, 23);
		frame.getContentPane().add(btnTotalMessages);

		JButton btnPositivePercentage = new JButton("Positive Percentage");
		
		btnPositivePercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Float totalMessages = (float) 0;
				List<AppUser> appUserList2 = UserAndGroupInfo.getAppUserList();
				for(AppUser a: appUserList2) 
				{
					totalMessages = totalMessages+a.getMessageList().size();
				}
			
			
				Float positiveCount = (float) 0;	
				for(AppUser a: appUserList2)
				{
					
					List<String> message  = a.getMessageList();
					for(String s :message)
					{
						System.out.println(s);
						if(s.contains("happy") || s.contains("good"))
						{
							positiveCount++;
						}
					}
				}
				
				Float percent = (float) (positiveCount/totalMessages);
				JOptionPane.showMessageDialog(frame, "Total positive percentage :"+percent*100+"%");
				
			}
			});
		btnPositivePercentage.setBounds(309, 227, 125, 23);
		frame.getContentPane().add(btnPositivePercentage);
	}

	private JTree updateJtree(JScrollPane scrollPane) {
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		tree.setBorder(new LineBorder(new Color(0, 0, 0)));

		AppUserGroup apGrpRoot= UserAndGroupInfo.getAppUserGroupList().get(0);
		
		
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(apGrpRoot) {
			{
				 
				generateNodes();
				/*
				 * node_1 = new DefaultMutableTreeNode("sports"); node_1.add(new
				 * DefaultMutableTreeNode("basketball")); 
				 * node_1.add(new  DefaultMutableTreeNode("soccer")); 
				 * node_1.add(new
				 * DefaultMutableTreeNode("football")); node_1.add(new
				 * DefaultMutableTreeNode("hockey")); add(node_1); node_1 = new
				 * DefaultMutableTreeNode("food"); node_1.add(new
				 * DefaultMutableTreeNode("hot dogs")); node_1.add(new
				 * DefaultMutableTreeNode("pizza")); node_1.add(new
				 * DefaultMutableTreeNode("ravioli")); node_1.add(new
				 * DefaultMutableTreeNode("bananas")); add(node_1);
				 */
			}

			private void generateNodes() {
				DefaultMutableTreeNode root;
				
				List<AppUser> appUserList2 = UserAndGroupInfo.getAppUserList();
				List<AppUserGroup> appUserGroupList2 = UserAndGroupInfo.getAppUserGroupList();
				
				System.out.println("User list size: "+ appUserList2.size());
				
				 int i = appUserGroupList2.size();
				 while (i!=0)
				 {
					AppUserGroup g = appUserGroupList2.get(i-1);
					
					System.out.println("Processing : " + g.getUserGroupID());
					if (g.getParentGroupID() == null)
					{	
						
						for(AppUser a:UserAndGroupInfo.getAppUserList())
						{
							if(a.getUserGrpID().equals(g.getUserGroupID()))
							{
							  add(new  DefaultMutableTreeNode(a));
							}
						}
						
						i--;						 
					} 
					else
					{
						if (parentAddedInTree(g))
						{
							DefaultMutableTreeNode child = addLeafs(g);
							
							getParentNode(g.getParentGroupID()).add(child);
							i--;
						}
					}
				}
				
				/* for(AppUser a : appUserList2)
				 {
					 root = new DefaultMutableTreeNode(a);
					 add(root);
				 }	*/		
				 
				 
				 
				
			}

			

			private DefaultMutableTreeNode getParentNode(String string)
			{
				
				for (Enumeration e = this.depthFirstEnumeration(); e.hasMoreElements();)
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
					if (node.getUserObject() instanceof AppUserGroup)
					{
						AppUserGroup a = (AppUserGroup) node.getUserObject();
						if (a.getUserGroupID().equals(string))
						{
							return node;
						}
					}
				}
				return null;
			}

			private boolean parentAddedInTree(AppUserGroup g)
			{
				  Enumeration e = this.preorderEnumeration();
				    while(e.hasMoreElements()){
				    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
				    	
				    	AppUserGroup a = (AppUserGroup) node.getUserObject();
				    	if(g.getParentGroupID().equals(a.getUserGroupID()))
				    	{
				    		System.out.println("---" + a.getUserGroupID());
				    		return true;
				    		
				    	}
				      
				    }
				    
				return false;
			}

			
		}));
		tree.setShowsRootHandles(true);
		tree.setDragEnabled(true);
		return tree;
	}
	private DefaultMutableTreeNode addLeafs(AppUserGroup g)
	{
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(g);
		
		for(AppUser a:UserAndGroupInfo.getAppUserList())
		{
			if(a.getUserGrpID().equals(g.getUserGroupID()))
			{
			 child.add(new  DefaultMutableTreeNode(a));
			}
		}
		return child;
	}
	private boolean isUserAlreadyPresent(String trim) {
		List<AppUser> list = UserAndGroupInfo.getAppUserList();
		for(AppUser a:list)
		{
			if(a.getUserID().equals(trim))
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	private boolean isUserGroupAlreadyPresent(String trim) {
		List<AppUserGroup> list = UserAndGroupInfo.getAppUserGroupList();
		for(AppUserGroup a:list)
		{
			if(a.getUserGroupID().equals(trim))
			{
				return true;
			}
		}
		return false;
	}
	
//Implementing Observer pattern : Adding New User child Widow will reflect on Parent window
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Total users: " + UserAndGroupInfo.getAppUserList().size());
		updateJtree(scrollPane);
		
	}
}
