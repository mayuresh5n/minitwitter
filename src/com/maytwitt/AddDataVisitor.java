package com.maytwitt;

public class AddDataVisitor implements CountDataInList{

	 
	@Override
	public Integer visit(AppUser user) {		
		
		Integer count = UserAndGroupInfo.getAppUserList().size();
		return count;
		 
		
	}

	@Override
	public Integer visit(AppUserGroup userGroup) {
		
		Integer count = UserAndGroupInfo.getAppUserGroupList().size();
		return count;
		 
		
	}

	 

}
