package com.askvikrant.noticeboard.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.askvikrant.noticeboard.model.Attachment;
import com.askvikrant.noticeboard.model.Department;
import com.askvikrant.noticeboard.model.Notice;
import com.askvikrant.noticeboard.model.QuickLink;
import com.askvikrant.noticeboard.model.User;

public class DatabaseManager {

    private DataSource pool;

    private Connection conn = null;

    private String dbMessage = "";

    public DatabaseManager() throws Exception {
        try {
            InitialContext ctx = new InitialContext();
            pool = (DataSource) ctx.lookup("java:comp/env/jdbc/nbpool");
            if (pool == null) throw new Exception("Unknown DataSource 'jdbc/nbpool'");
        } catch (NamingException ex) {
            System.out.println("DatabaseManager(): " + ex.getMessage());
        }
    }

    public void resetDbMessage() {
        dbMessage = "";
    }

    public void setDbMessage(String message) {
        dbMessage = message;
    }

    public String getDbMessage() {
        return dbMessage;
    }

    public boolean getConnection() {
        boolean success = false;
        try {
            if (conn == null || conn.isClosed()) {
                conn = pool.getConnection();
            }
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: getConnection(): " + ex.getMessage());
        }
        return success;
    }

    public boolean closeConnection() {
        boolean success = false;
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception ex) {
                setDbMessage(ex.getMessage());
                System.out.println("DB: closeConnection(): " + ex.getMessage());
            }
        }
        success = true;
        return success;
    }

    boolean beginTransaction(String callingMethod) {
        boolean success = false;
        try {
            conn.setAutoCommit(false);
            success = true;
            System.out.println("INFO: Transaction started by: " + callingMethod);
        } catch (Exception ex) {
            System.out.println("ERROR: beginTransaction(): " + ex.getMessage());
        }
        return success;
    }

    boolean commitTransaction(String callingMethod) {
        boolean success = false;
        try {
            conn.commit();
            conn.setAutoCommit(true);
            success = true;
            System.out.println("INFO: Transaction committed by: " + callingMethod);
        } catch (Exception ex) {
            System.out.println("ERROR: commitTransaction(): " + ex.getMessage());
        }
        return success;
    }

    boolean rollbackTransaction(String callingMethod) {
        boolean success = false;
        try {
            conn.rollback();
            conn.setAutoCommit(true);
            success = true;
            System.out.println("INFO: Transaction rolled back by: " + callingMethod);
        } catch (Exception ex) {
            System.out.println("ERROR: rollbackTransaction(): " + ex.getMessage());
        }
        return success;
    }

    public String getNextAvailablePrimaryKeyValue(String tableName) {
        String returnValue = null;
        String existingPkValue = null;
        String nextPkValue = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("select id_value from _last_used_id where table_name = ?  ");
            stmt.setString(1, tableName);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                existingPkValue = rs.getString("id_value");
            }
            String prefix = existingPkValue.substring(0, 2);
            String existingPkValueWithPrefixStripped = existingPkValue.substring(2);
            int completeLength = existingPkValueWithPrefixStripped.length();
            int existingIdNum = 0;
            existingIdNum = Integer.parseInt(existingPkValueWithPrefixStripped.trim());
            int nextIdNum = existingIdNum + 1;
            String nextId = String.valueOf(nextIdNum);
            int newLength = nextId.length();
            int zeroes = completeLength - newLength;
            for (int i = 1; i <= zeroes; i++) {
                nextId = "0" + nextId;
            }
            nextPkValue = prefix + nextId;
            stmt = conn.prepareStatement("update _last_used_id set id_value = ? where table_name = ? ");
            stmt.setString(1, nextPkValue);
            stmt.setString(2, tableName);
            stmt.executeUpdate();
            rs.close();
            stmt.close();
            returnValue = nextPkValue;
        } catch (NumberFormatException nfe) {
            setDbMessage(nfe.getMessage());
            System.out.println("DB: Exception: getNextAvailablePrimaryKeyValue_TENANT" + nfe.getMessage());
        } catch (SQLException sqlex) {
            setDbMessage(sqlex.getMessage());
            System.out.println("DB: Exception: getNextAvailablePrimaryKeyValue_TENANT" + sqlex.getMessage());
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: Exception: getNextAvailablePrimaryKeyValue_TENANT" + ex.getMessage());
        }
        return returnValue;
    }

    String select_configuration_rootPassword() {
        String password = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select c.param_value from _configuration c " + " where c.param_name = ? ");
            stmt.setString(1, "root_password");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                password = rs.getString("c.param_value");
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: select_configuration_rootPassword(): " + ex.getMessage());
        }
        return password;
    }

    boolean update_configuration_rootPassword(String rootPassword) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" update _configuration c " + " set c.param_value = ? " + " where c.param_name = ? ");
            stmt.setString(1, rootPassword);
            stmt.setString(2, "root_password");
            stmt.executeUpdate();
            success = true;
            stmt.close();
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: select_configuration_rootPassword(): " + ex.getMessage());
        }
        return success;
    }

    String select_configuration_defaultDays() {
        String defaultDays = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select c.param_value from _configuration c " + " where c.param_name = ? ");
            stmt.setString(1, "default_days");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                defaultDays = rs.getString("c.param_value");
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: select_configuration_defaultDays(): " + ex.getMessage());
        }
        return defaultDays;
    }

    boolean update_configuration_defaultDays(String defaultDays) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" update _configuration c " + " set c.param_value = ? " + " where c.param_name = ? ");
            stmt.setString(1, defaultDays);
            stmt.setString(2, "default_days");
            stmt.executeUpdate();
            success = true;
            stmt.close();
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: select_configuration_defaultDays(): " + ex.getMessage());
        }
        return success;
    }

    User select_user(String userId, String password) {
        User user = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select u.* from _user u " + " where user_id = ? " + " and password = ? ");
            stmt.setString(1, userId);
            stmt.setString(2, password);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("u.user_id"));
                user.setName(rs.getString("u.user_name"));
                user.setRole(rs.getString("u.user_role"));
                user.setDepartment(rs.getString("u.department"));
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: select_user(): " + ex.getMessage());
        }
        return user;
    }

    ArrayList<User> select_users(String departmentName) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement stmt = conn.prepareStatement(" select u.user_id from _user u " + " where department = ? ");
            stmt.setString(1, departmentName);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("u.user_id"));
                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: select_user(): " + ex.getMessage());
        }
        return users;
    }

    ArrayList<User> select_users() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement stmt = conn.prepareStatement(" select u.* from _user u ");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("u.user_id"));
                user.setName(rs.getString("u.user_name"));
                user.setPassword(rs.getString("u.password"));
                user.setRole(rs.getString("u.user_role"));
                user.setDepartment(rs.getString("u.department"));
                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: select_users(): " + ex.getMessage());
        }
        return users;
    }

    boolean checkIfExists_user(String userId) {
        boolean exists = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select u.user_id from _user u " + " where u.user_id = ? ");
            stmt.setString(1, userId);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            int count = 0;
            while (rs.next()) {
                ++count;
            }
            if (count != 0) {
                exists = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            exists = false;
            setDbMessage(ex.getMessage());
            System.out.println("DB: Exception: checkIfExists_user(): " + ex.getMessage());
        }
        return exists;
    }

    boolean insert_user(String userId, String userName, String password, String role, String departmentName) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" insert into _user " + " (user_id, user_name,user_role,department,password) " + " values(?,?,?,?,?) ");
            stmt.setString(1, userId);
            stmt.setString(2, userName);
            stmt.setString(3, role);
            stmt.setString(4, departmentName);
            stmt.setString(5, password);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: insert_user(): " + ex.getMessage());
        }
        return success;
    }

    boolean update_user(String userId, String userName, String password, String role, String departmentName) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" update _user " + " set user_name = ?, user_role = ?, department = ?, password = ? " + " where user_id = ? ");
            stmt.setString(1, userName);
            stmt.setString(2, role);
            stmt.setString(3, departmentName);
            stmt.setString(4, password);
            stmt.setString(5, userId);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: insert_user(): " + ex.getMessage());
        }
        return success;
    }

    boolean update_userPassword(String userId, String password) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" update _user " + " set password = ? " + " where user_id = ? ");
            stmt.setString(1, password);
            stmt.setString(2, userId);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: update_userPassword(): " + ex.getMessage());
        }
        return success;
    }

    boolean delete_user(String userId) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" delete from _user " + " where user_id = ? ");
            stmt.setString(1, userId);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: delete_user(): " + ex.getMessage());
        }
        return success;
    }

    ArrayList<String> select_noticeIds(String searchText) {
        searchText = "%" + searchText + "%";
        ArrayList<String> ids = new ArrayList<String>();
        try {
            PreparedStatement stmt = conn.prepareStatement(" select n.id from _notice n " + "where n.title like ? or n.body like ? ");
            stmt.setString(1, searchText);
            stmt.setString(2, searchText);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                ids.add(rs.getString("n.id"));
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_noticeIds(): " + ex.getMessage());
        }
        return ids;
    }

    ArrayList<String> select_NoticeIdsHavingDays(int days) {
        ArrayList<String> ids = new ArrayList<String>();
        try {
            PreparedStatement stmt = conn.prepareStatement(" select n.id from _notice n " + "where datediff(curdate(),n.date) <= ? ");
            stmt.setInt(1, days);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                ids.add(rs.getString("n.id"));
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_NoticeIdsHavingDays(): " + ex.getMessage());
        }
        return ids;
    }

    Notice select_notice(String noticeId) {
        Notice notice = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select datediff(curdate(),n.date),n.*,u.user_name " + " from _notice n join _user u " + " on n.user_id = u.user_id " + "where n.id = ? ");
            stmt.setString(1, noticeId);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                notice = new Notice();
                notice.setDays(rs.getInt(1));
                notice.setId(rs.getString("n.id"));
                notice.setTitle(rs.getString("n.title"));
                notice.setBody(rs.getString("n.body"));
                String dateStr = rs.getString("n.date");
                notice.setDate(dateFormat.parse(dateStr));
                notice.setUserId(rs.getString("n.user_id"));
                notice.setUserName(rs.getString("u.user_name"));
                notice.setDepartment(rs.getString("n.department"));
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_notice(): " + ex.getMessage());
        }
        return notice;
    }

    ArrayList<Notice> select_notices(ArrayList<String> ids) {
        ArrayList<Notice> notices = new ArrayList<Notice>();
        try {
            String arguments = null;
            if (ids != null) {
                if (ids.size() != 0) {
                    for (int i = 0; i < ids.size(); i++) {
                        if (arguments == null) {
                            arguments = "?";
                        } else {
                            arguments = arguments + "," + "?";
                        }
                    }
                }
            }
            PreparedStatement stmt = conn.prepareStatement(" select datediff(curdate(),n.date),n.* from _notice n " + "where n.id in ( " + arguments + " ) " + "order by n.date desc ");
            for (int i = 0; i < ids.size(); i++) {
                stmt.setString(i + 1, ids.get(i));
            }
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            while (rs.next()) {
                Notice notice = new Notice();
                notices.add(notice);
                notice.setDays(rs.getInt(1));
                notice.setId(rs.getString("n.id"));
                notice.setTitle(rs.getString("n.title"));
                notice.setBody(rs.getString("n.body"));
                String dateStr = rs.getString("n.date");
                notice.setDate(dateFormat.parse(dateStr));
                notice.setUserId(rs.getString("n.user_id"));
                notice.setDepartment(rs.getString("n.department"));
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_notices(): " + ex.getMessage());
        }
        return notices;
    }

    int select_noticesCountForUser(String userId) {
        int count = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select count(*) from _notice n " + "where n.user_id = ? ");
            stmt.setString(1, userId);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_noticesCountForUser(): " + ex.getMessage());
        }
        return count;
    }

    ArrayList<Attachment> select_attachments(String noticeId) {
        ArrayList<Attachment> attachments = new ArrayList<Attachment>();
        try {
            PreparedStatement stmt = conn.prepareStatement(" select a.* from _attachment a " + " where a.notice_id = ? ");
            stmt.setString(1, noticeId);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Attachment attachment = new Attachment();
                String id = rs.getString("a.id");
                attachment.setId(id);
                String fileName = rs.getString("a.file_name");
                String contentType = rs.getString("a.content_type");
                attachment.setContentType(contentType);
                Blob blob = rs.getBlob("a.file_blob");
                if (blob != null) {
                    InputStream is = (InputStream) blob.getBinaryStream();
                    final File parentDir = new File("noticeboard_temp");
                    if (!parentDir.exists()) {
                        parentDir.mkdir();
                    }
                    final File file = new File(parentDir, fileName);
                    file.createNewFile();
                    OutputStream os = new FileOutputStream(file);
                    byte[] b = new byte[(int) blob.length()];
                    is.read(b);
                    os.write(b);
                    attachment.setFile(file);
                }
                attachments.add(attachment);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_attachments(): " + ex.getMessage());
        }
        return attachments;
    }

    Attachment select_attachment(String attachmentId) {
        Attachment attachment = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select a.* from _attachment a " + " where a.id = ? ");
            stmt.setString(1, attachmentId);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                attachment = new Attachment();
                String _id = rs.getString("a.id");
                attachment.setId(_id);
                String fileName = rs.getString("a.file_name");
                String contentType = rs.getString("a.content_type");
                attachment.setContentType(contentType);
                Blob blob = rs.getBlob("a.file_blob");
                if (blob != null) {
                    InputStream is = (InputStream) blob.getBinaryStream();
                    final File parentDir = new File("noticeboard_temp");
                    if (!parentDir.exists()) {
                        parentDir.mkdir();
                    }
                    final File file = new File(parentDir, fileName);
                    file.createNewFile();
                    OutputStream os = new FileOutputStream(file);
                    byte[] b = new byte[(int) blob.length()];
                    is.read(b);
                    os.write(b);
                    attachment.setFile(file);
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_attachments(): " + ex.getMessage());
        }
        return attachment;
    }

    boolean insert_notice(String noticeId, String title, String body, String date, String userId, String department) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" insert into _notice " + " (id,title,body,date,user_id,department ) " + " values(?,?,?,?,?,?) ");
            stmt.setString(1, noticeId);
            stmt.setString(2, title);
            stmt.setString(3, body);
            stmt.setString(4, date);
            stmt.setString(5, userId);
            stmt.setString(6, department);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: insert_notice(): " + ex.getMessage());
        }
        return success;
    }

    boolean update_notice(String noticeId, String title, String body) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" update _notice " + " set title = ?, body = ? where id = ? ");
            stmt.setString(1, title);
            stmt.setString(2, body);
            stmt.setString(3, noticeId);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: update_notice(): " + ex.getMessage());
        }
        return success;
    }

    boolean delete_notice(String noticeId) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" delete from _notice " + " where id = ? ");
            stmt.setString(1, noticeId);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: delete_notice(): " + ex.getMessage());
        }
        return success;
    }

    boolean insert_attachment(String attachmentId, String fileName, String contentType, File file, String noticeId) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" insert into _attachment " + " (id, file_name,content_type, file_blob, notice_id) " + " values(?,?,?,?,?) ");
            InputStream is = new FileInputStream(file);
            stmt.setString(1, attachmentId);
            stmt.setString(2, fileName);
            stmt.setString(3, contentType);
            stmt.setBinaryStream(4, (InputStream) is, file.length());
            stmt.setString(5, noticeId);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: Exception: insert_attachment(): " + ex.getMessage());
        }
        return success;
    }

    boolean delete_attachments(String noticeId) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" delete from _attachment " + " where notice_id = ? ");
            stmt.setString(1, noticeId);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: Exception: delete_attachments(): " + ex.getMessage());
        }
        return success;
    }

    ArrayList<Department> select_departments() {
        ArrayList<Department> departments = new ArrayList<Department>();
        try {
            PreparedStatement stmt = conn.prepareStatement(" select d.* from _department d ");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Department department = new Department();
                department.setName(rs.getString("d.name"));
                departments.add(department);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_departments(): " + ex.getMessage());
        }
        return departments;
    }

    boolean checkIfExists_department(String name) {
        boolean exists = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select d.* from _department d " + " where d.name = ? ");
            stmt.setString(1, name);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            int count = 0;
            while (rs.next()) {
                ++count;
            }
            if (count != 0) {
                exists = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            exists = false;
            setDbMessage(ex.getMessage());
            System.out.println("DB: Exception: checkIfExists_department(): " + ex.getMessage());
        }
        return exists;
    }

    boolean insert_department(String departmentName) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" insert into _department " + " (name) " + " values(?) ");
            stmt.setString(1, departmentName);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: insert_department(): " + ex.getMessage());
        }
        return success;
    }

    boolean delete_department(String departmentName) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" delete from _department " + " where name = ? ");
            stmt.setString(1, departmentName);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: delete_department(): " + ex.getMessage());
        }
        return success;
    }

    boolean update_department(String oldDepartmentName, String newDepartmentName) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" update _department " + " set name = ? where name = ? ");
            stmt.setString(1, newDepartmentName);
            stmt.setString(2, oldDepartmentName);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: update_department(): " + ex.getMessage());
        }
        return success;
    }

    ArrayList<QuickLink> select_quickLinks() {
        ArrayList<QuickLink> quickLinks = new ArrayList<QuickLink>();
        try {
            PreparedStatement stmt = conn.prepareStatement(" select ql.* from _quick_link ql" + " order by ql.name ");
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                QuickLink quickLink = new QuickLink();
                quickLink.setName(rs.getString("ql.name"));
                quickLink.setUrl(rs.getString("ql.url"));
                quickLinks.add(quickLink);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("ERROR: DB: select_quickLinks(): " + ex.getMessage());
        }
        return quickLinks;
    }

    boolean update_quickLink(String oldName, String newName, String url) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" update _quick_link " + " set name = ?, url = ? where name = ? ");
            stmt.setString(1, newName);
            stmt.setString(2, url);
            stmt.setString(3, oldName);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: update_quickLink(): " + ex.getMessage());
        }
        return success;
    }

    boolean delete_quickLink(String linkName) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" delete from _quick_link " + " where name = ? ");
            stmt.setString(1, linkName);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: delete_quickLink(): " + ex.getMessage());
        }
        return success;
    }

    boolean checkIfExists_quickLink(String name) {
        boolean exists = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" select ql.* from _quick_link ql " + " where ql.name = ? ");
            stmt.setString(1, name);
            stmt.executeQuery();
            ResultSet rs = stmt.getResultSet();
            int count = 0;
            while (rs.next()) {
                ++count;
            }
            if (count != 0) {
                exists = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            exists = false;
            setDbMessage(ex.getMessage());
            System.out.println("DB: Exception: checkIfExists_quickLink(): " + ex.getMessage());
        }
        return exists;
    }

    boolean insert_quickLink(String linkName, String linkUrl) {
        boolean success = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(" insert into _quick_link " + " (name, url) " + " values(?,?) ");
            stmt.setString(1, linkName);
            stmt.setString(2, linkUrl);
            stmt.executeUpdate();
            success = true;
        } catch (Exception ex) {
            setDbMessage(ex.getMessage());
            System.out.println("DB: insert_quickLink(): " + ex.getMessage());
        }
        return success;
    }
}
