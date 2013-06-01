package com.askvikrant.noticeboard.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.askvikrant.noticeboard.model.Attachment;
import com.askvikrant.noticeboard.model.Department;
import com.askvikrant.noticeboard.model.Notice;
import com.askvikrant.noticeboard.model.NoticeSearchParams;
import com.askvikrant.noticeboard.model.QuickLink;
import com.askvikrant.noticeboard.model.User;

public class BusinessLogic {
	private transient DatabaseManager databaseManager = null;
	private void connectToDatabase(){
		try{
			databaseManager = new DatabaseManager();
		}catch(Exception ex){
			System.out.println("BusinessLogic: connectToDatabase(): "+ex.getMessage());
		}
	}

	public BusinessLogic(){
		connectToDatabase();
	}
	public String getAppMessage(){
		return databaseManager.getDbMessage();
	}

	//=====================================================================================================
	//users
	public User getGuestUser(){
		User user = new User();
		user.setRole(User.ROLE_GUEST);
		user.setUserId("guest");
		user.setName("Guest");
		return user;
	}

	public User authenticate(final String userId, final String password){
		User user = null;

		//if userid = "nb.root", authenticate from properties file
		//else authenticate from database

		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			if(userId.equalsIgnoreCase(User.ROOT_USER_ID)){
				String rootPassword = getRootPassword();
				if(password.equals(rootPassword)){
					user = new User();
					user.setUserId(User.ROOT_USER_ID);
					user.setName(User.ROOT_USER_NAME);
					user.setRole(User.ROLE_ROOT);
				}else{
					databaseManager.setDbMessage("Invalid password");
				}
			}else{
				user = databaseManager.select_user(userId, password);
				if(user==null){
					databaseManager.setDbMessage("Invalid user id and/or password");
				}
			}
			databaseManager.closeConnection();
		} catch (Exception ex) {
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return user;
	}

	private String getRootPassword(){
		String rootPassword = null;
		try{
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			rootPassword = databaseManager.select_configuration_rootPassword();	
		}
		catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return rootPassword;
	}

	public ArrayList<User> getUsers(){
		ArrayList<User> users = null;
		try{
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			users = databaseManager.select_users();	
		}
		catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return users;
	}

	public boolean saveNewUser(
			final String userId,
			final String userName,
			final String password,
			final String role,
			final String department){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}
			if(userId.equals(User.ROOT_USER_ID)){
				databaseManager.setDbMessage("Don't try to fool the system.");
				return false;
			}
			boolean alreadyExists = databaseManager.checkIfExists_user(userId);
			if(alreadyExists){
				databaseManager.setDbMessage("User already exists.");
				return false;
			}

			success = databaseManager.beginTransaction("saveNewUser()");
			if(success){

				if(success){
					success = databaseManager.insert_user(
							userId,
							userName,
							password,
							role,
							department);		
				}

				if(success){
					success = databaseManager.commitTransaction("saveNewUser()");
				}else{
					databaseManager.rollbackTransaction("saveNewUser()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean updateUserPassword(
			final String userId,
			final String oldPassword,
			final String password1,
			final String password2){

		boolean success = false;
		try {

			User user = authenticate(userId,oldPassword);

			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}

			if (user != null) {
				if (password1 != null && password1.length() != 0
						&& password2 != null && password2.length() != 0) {
					if (password1.equals(password2)) {
						if(user.getUserId().equals(User.ROOT_USER_ID)){
							success = databaseManager.beginTransaction("updateUserPassword()");
							if(success){
								success = databaseManager.update_configuration_rootPassword(password1);
								if(success){
									success = databaseManager.commitTransaction("updateUserPassword()");
								}else{
									databaseManager.rollbackTransaction("updateUserPassword()");
									success = false;
								}
							}
						}else{
							success = databaseManager.beginTransaction("updateUserPassword()");
							if(success){
								success = databaseManager.update_userPassword(user.getUserId(), password1);
								if(success){
									success = databaseManager.commitTransaction("updateUserPassword()");
								}else{
									databaseManager.rollbackTransaction("updateUserPassword()");
									success = false;
								}
							}
						}
					} else {
						success = false;
						databaseManager
						.setDbMessage("The two passwords do not match.");
					}
				} else {
					success = false;
					databaseManager.setDbMessage("Blank password not allowed.");
				}
			} else {
				success = false;
				databaseManager
				.setDbMessage("Current password provided is incorrect. Please try again");
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean updateUser(
			final String userId,
			final String userName,
			final String role,
			final String password,
			final String department){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}

			success = databaseManager.beginTransaction("updateUser()");
			if(success){

				if(success){
					success = databaseManager.update_user(
							userId,
							userName,
							password,
							role,
							department);		
				}

				if(success){
					success = databaseManager.commitTransaction("updateUser()");
				}else{
					databaseManager.rollbackTransaction("updateUser()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean deleteUser(
			String userId){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}

			int count = databaseManager.select_noticesCountForUser(userId);
			if(count>0){
				success = false;
				databaseManager.setDbMessage("Notices exist posted by this user. " +
						"You should change the user password instead, to restrict user access.");
			}else{
				success = true;
			}

			if(success){
				success = databaseManager.beginTransaction("deleteUser()");
				if(success){

					if(success){
						success = databaseManager.delete_user(userId);
					}

					if(success){
						success = databaseManager.commitTransaction("deleteUser()");
					}else{
						success = false;
						databaseManager.rollbackTransaction("deleteUser()");
					}
				}

			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	//=====================================================================================================
	//notices
	private ArrayList<String> getNoticeIds(NoticeSearchParams searchParams){
		ArrayList<String> ids = new ArrayList<String>();
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			if (searchParams == null) {
				// return empty list
				// do nothing
			} else {
				String searchType = searchParams.getSearchType();
				if(searchType.equals(NoticeSearchParams.TYPE_TEXT_SEARCH)){
					String searchText = searchParams.getSearchText();
					if(searchText==null){
						searchText = "";
					}
					ids = databaseManager.select_noticeIds(searchText);
				}else if(searchType.equals(NoticeSearchParams.TYPE_07_DAYS)){
					ids = databaseManager.select_NoticeIdsHavingDays(7);
				}else if(searchType.equals(NoticeSearchParams.TYPE_15_DAYS)){
					ids = databaseManager.select_NoticeIdsHavingDays(15);
				}else if(searchType.equals(NoticeSearchParams.TYPE_30_DAYS)){
					ids = databaseManager.select_NoticeIdsHavingDays(30);
				}
			}
		} catch (Exception ex) {
			// log the exception
			System.out.println("ERROR: BL: getNoticeIds(): "
					+ ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return ids;
	}

	public Notice getNotice(String noticeId){
		Notice notice = null;
		try{
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			notice = databaseManager.select_notice(noticeId);
		}catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return notice;
	}

	public ArrayList<Notice> getNotices(final NoticeSearchParams noticeSearchParams){
		ArrayList<String> ids = null;
		ArrayList<Notice> notices = null;
		try{
			ids = getNoticeIds(noticeSearchParams);
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			notices = databaseManager.select_notices(ids);
		}catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return notices;
	}

	public boolean saveNewNotice(
			final String title,
			final String body,
			final ArrayList<Attachment> attachments,
			final String userId,
			final String department){

		boolean success = false;

		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}
			success = databaseManager.beginTransaction("saveNewNotice()");
			if(success){

				String noticeId = databaseManager.getNextAvailablePrimaryKeyValue("_notice");
				if(noticeId==null){
					success = false;
				}else{
					success = true;
				}

				if(success){
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					success = databaseManager.insert_notice(
							noticeId,
							title,
							body,
							dateFormat.format(date),
							userId,
							department);		
				}

				//insert attachments
				if(success){
					for(int i=0;i<attachments.size();i++){
						String attachmentId = databaseManager.getNextAvailablePrimaryKeyValue("_attachment");
						if(attachmentId==null){
							success = false;
						}else{
							success = databaseManager.insert_attachment(attachmentId, attachments.get(i).getFile().getName(), attachments.get(i).getContentType(),attachments.get(i).getFile(), noticeId);
							if(!success){
								break;
							}
						}
					}
				}
				if(success){
					success = databaseManager.commitTransaction("saveNewNotice()");
				}else{
					databaseManager.rollbackTransaction("saveNewNotice()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean updateNotice(
			final String noticeId,
			final String title,
			final String body, 
			final ArrayList<Attachment> attachments){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}

			success = databaseManager.beginTransaction("updateNotice()");
			if(success){

				success = databaseManager.update_notice(
						noticeId,
						title,
						body);		

				//delete existing attachments
				if(success){
					success = databaseManager.delete_attachments(noticeId);
				}
				//insert attachments
				if(success){
					for(int i=0;i<attachments.size();i++){
						String attachmentId = databaseManager.getNextAvailablePrimaryKeyValue("_attachment");
						if(attachmentId==null){
							success = false;
						}else{
							success = databaseManager.insert_attachment(attachmentId, attachments.get(i).getFile().getName(), attachments.get(i).getContentType(),attachments.get(i).getFile(), noticeId);
							if(!success){
								break;
							}
						}
					}
				}
				if(success){
					success = databaseManager.commitTransaction("updateNotice()");
				}else{
					databaseManager.rollbackTransaction("updateNotice()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}

		return success;
	}

	public boolean deleteNotice(
			final String noticeId){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}

			success = databaseManager.beginTransaction("deleteNotice()");
			if(success){

				success = databaseManager.delete_attachments(noticeId);
				//delete existing attachments
				if(success){
					success = databaseManager.delete_notice(noticeId);
				}
				if(success){
					success = databaseManager.commitTransaction("deleteNotice()");
				}else{
					databaseManager.rollbackTransaction("deleteNotice()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}

		return success;
	}

	public Attachment getAttachment(final String attachmentId){
		Attachment attachment = null;
		try{
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			attachment = databaseManager.select_attachment(attachmentId);
		}catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}

		return attachment;
	}

	public ArrayList<Attachment> getAttachments(final String noticeId){
		ArrayList<Attachment> attachments = null;
		try{
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			attachments = databaseManager.select_attachments(noticeId);
		}catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}

		return attachments;
	}

	//=====================================================================================================
	//departments

	public boolean saveNewDepartment(
			final String departmentName){
		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}
			boolean alreadyExists = databaseManager.checkIfExists_department(departmentName);
			if(alreadyExists){
				databaseManager.setDbMessage("Department already exists.");
				return false;
			}

			success = databaseManager.beginTransaction("saveNewDepartment()");
			if(success){

				if(success){
					success = databaseManager.insert_department(
							departmentName);		
				}

				if(success){
					success = databaseManager.commitTransaction("saveNewDepartment()");
				}else{
					databaseManager.rollbackTransaction("saveNewDepartment()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean updateDepartment(
			String oldDepartmentName,
			String newDepartmentName){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}

			success = databaseManager.beginTransaction("updateDepartment()");
			if(success){

				if(success){
					success = databaseManager.update_department(
							oldDepartmentName,newDepartmentName);		
				}

				if(success){
					success = databaseManager.commitTransaction("updateDepartment()");
				}else{
					databaseManager.rollbackTransaction("updateDepartment()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}
	public boolean deleteDepartment(
			String departmentName){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}
			ArrayList<User> users = databaseManager.select_users(departmentName);
			if(users.size()!=0){
				success = false;
				databaseManager.setDbMessage("Users exist belonging to this department.");
			}else{
				success = true;
			}

			if(success){
				success = databaseManager.beginTransaction("deleteDepartment()");
				if(success){

					if(success){
						success = databaseManager.delete_department(departmentName);
					}

					if(success){
						success = databaseManager.commitTransaction("deleteDepartment()");
					}else{
						success = false;
						databaseManager.rollbackTransaction("deleteDepartment()");
					}
				}

			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public ArrayList<Department> getDepartments(){
		ArrayList<Department> departments = null;
		try{
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			departments = databaseManager.select_departments();
		}catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}

		return departments;
	}
	//=====================================================================================================
	//quick links

	public ArrayList<QuickLink> getQuickLinks(){
		ArrayList<QuickLink> quickLinks = null;
		try{
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			quickLinks = databaseManager.select_quickLinks();
		}catch(Exception ex){
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}

		return quickLinks;
	}

	public boolean saveNewQuickLink(
			final String linkName,
			final String linkUrl){
		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}
			boolean alreadyExists = databaseManager.checkIfExists_quickLink(linkName);
			if(alreadyExists){
				databaseManager.setDbMessage("QuickLink already exists.");
				return false;
			}

			success = databaseManager.beginTransaction("saveNewQuickLink()");
			if(success){

				if(success){
					success = databaseManager.insert_quickLink(
							linkName,linkUrl);		
				}

				if(success){
					success = databaseManager.commitTransaction("saveNewQuickLink()");
				}else{
					databaseManager.rollbackTransaction("saveNewQuickLink()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean updateQuickLink(
			final String oldName,
			final String newName,
			final String url){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}

			success = databaseManager.beginTransaction("updateQuickLink()");
			if(success){

				if(success){
					success = databaseManager.update_quickLink(
							oldName,newName,url);		
				}

				if(success){
					success = databaseManager.commitTransaction("updateQuickLink()");
				}else{
					databaseManager.rollbackTransaction("updateQuickLink()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean deleteQuickLink(
			final String linkName){

		boolean success = false;
		try {
			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}
			success = databaseManager.beginTransaction("deleteQuickLink()");
			if(success){

				if(success){
					success = databaseManager.delete_quickLink(linkName);
				}

				if(success){
					success = databaseManager.commitTransaction("deleteQuickLink()");
				}else{
					success = false;
					databaseManager.rollbackTransaction("deleteQuickLink()");
				}
			}

		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public boolean updateDefaultDays(
			final String defaultDays){

		boolean success = false;
		try {

			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return false;
			}
			success = databaseManager.beginTransaction("updateUserPassword()");
			if(success){
				success = databaseManager.update_configuration_defaultDays(defaultDays);
				if(success){
					success = databaseManager.commitTransaction("updateDefaultDays()");
				}else{
					databaseManager.rollbackTransaction("updateDefaultDays()");
					success = false;
				}
			}
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return success;
	}

	public String getDefaultDays(){

		String defaultDays = null;
		try {

			databaseManager.resetDbMessage();
			if(!databaseManager.getConnection()){
				databaseManager.setDbMessage("Error connecting Database");
				return null;
			}
			defaultDays = databaseManager.select_configuration_defaultDays();
		} catch (Exception ex) {
			// log the exception
			databaseManager.setDbMessage(ex.getMessage());
		}
		finally{
			databaseManager.closeConnection();
		}
		return defaultDays;
	}

}
