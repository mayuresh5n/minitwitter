package com.maytwitt;

import java.util.ArrayList;
import java.util.List;

public class AppUser implements Visitor{

	private String userID;
	private String userGrpID;
	private  List<String>  userFollowers;
	private List<String>  usersFollowing ;
	private List<String>  messageList;
	private Long creationTime;
	private Long lastUpdateTime;
	
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public List<String> getUserFollowers() {
		 if(userFollowers!=null)
		 {
			 return userFollowers;
		 }
		return new ArrayList<>();
	}
	public void setUserFollowers(List<String> userFollowers) {
		this.userFollowers = userFollowers;
	}
	public List<String> getUsersFollowing() {
		
		 if(usersFollowing!=null)
		 {
			 return usersFollowing;
		 }
		return new ArrayList<>();
		
	}
	public void setUsersFollowing(List<String> usersFollowing) {
		this.usersFollowing = usersFollowing;
	}
	public List<String> getMessageList() {
		
		
		 if(messageList!=null)
		 {
			 return messageList;
		 }
		return new ArrayList<>();
		
	}
	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}	
	 public String toString()
	  {
	    // then you can avoid using toString
	    return getUserID() ;
	  }
	public String getUserGrpID() {
		return userGrpID;
	}
	public void setUserGrpID(String userGrpID) {
		this.userGrpID = userGrpID;
	}
	@Override
	public void countDataInlist(AddDataVisitor v) {
		v.visit(this);		
	}
	public Long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	 
	 
	 
}
