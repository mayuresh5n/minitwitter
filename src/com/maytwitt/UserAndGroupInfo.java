package com.maytwitt;

import java.util.ArrayList;
import java.util.List;

public class UserAndGroupInfo {
	//Implementing singleton pattern
	
	private static  UserAndGroupInfo userAndGroupInfo = new UserAndGroupInfo();
	
	private static   List<AppUser>  appUserList= new ArrayList<AppUser>();
	private static   List<AppUserGroup>  appUserGroupList= new ArrayList<AppUserGroup>();
	
	private UserAndGroupInfo ()
	{
		
	}
	public static UserAndGroupInfo getInstance()
	{
		return  userAndGroupInfo;
	}

	public static List<AppUser> getAppUserList() {
		return appUserList;
	}

	public static void addAppUserList(AppUser appUser) {
		UserAndGroupInfo.appUserList.add(appUser);
	}

	public static List<AppUserGroup> getAppUserGroupList() {
		return appUserGroupList;
	}

	public static void addAppUserGroupList(AppUserGroup appUserGroup) {
		UserAndGroupInfo.appUserGroupList.add(appUserGroup);
	}

	public void init() {
		addGroups();
		addUsers();
		
	}

	private static void addUsers() { 
		
		AppUser one = new AppUser();
		one.setUserID("user1");		
		List<String> msgList = new ArrayList<>();
		msgList.add("user2: First message");
		msgList.add("user2: second message");		
		one.setMessageList(msgList);		
		one.setUserFollowers(null);		
		List<String> followingList = new ArrayList<>();
		followingList.add("user2");
		one.setUsersFollowing(followingList);
		one.setUserGrpID("100");		
		addAppUserList(one);
		one.setCreationTime(System.currentTimeMillis());
		one.setLastUpdateTime(System.currentTimeMillis());
		
		
		AppUser two = new AppUser();
		two.setUserID("user2");		 
		List<String> follower = new ArrayList<>();
		follower.add("user1");
		two.setUserFollowers(follower);		 
		two.setUserGrpID("102");
		two.setCreationTime(System.currentTimeMillis());
		two.setLastUpdateTime(System.currentTimeMillis());
		addAppUserList(two);
	}

	private static void addGroups() {
		AppUserGroup a = new AppUserGroup();
		 
		a.setUserGroupID("100");
		a.setCreationTime(System.currentTimeMillis());
		addAppUserGroupList(a) ;        
	     
		AppUserGroup b = new AppUserGroup();
		b.setParentGroupID("100");
		b.setUserGroupID("101");
		b.setCreationTime(System.currentTimeMillis());
		addAppUserGroupList(b) ;        
		
		AppUserGroup c = new AppUserGroup();
		c.setParentGroupID("100");
		c.setUserGroupID("102");
		c.setCreationTime(System.currentTimeMillis());
		addAppUserGroupList(c) ;        
	}
}
