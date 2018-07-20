package com.maytwitt;

public class AppUserGroup implements Visitor{
	private String userGroupID;
	private String ParentGroupID;
	private Long creationTime;
	
	public String getUserGroupID() {
		return userGroupID;
	}
	public void setUserGroupID(String userGroupID) {
		this.userGroupID = userGroupID;
	}
	public String getParentGroupID() {
		return ParentGroupID;
	}
	public void setParentGroupID(String parentGroupID) {
		ParentGroupID = parentGroupID;
	}
	 public String toString()
	  {
	    // then you can avoid using toString
	    return getUserGroupID();
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
	 
	 
}
