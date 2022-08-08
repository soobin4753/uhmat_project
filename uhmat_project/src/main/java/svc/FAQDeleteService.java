package svc;

import java.sql.*;

import dao.*;
import db.*;

public class FAQDeleteService {

	public boolean removeFAQ(int idx) {
		System.out.println("FAQDeleteService - removeFAQ");
		boolean deleteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		deleteSuccess = dao.deleteFAQ(idx);
		System.out.println("deleteSuccess : "+ deleteSuccess);
		
		if(!deleteSuccess) {
			JdbcUtil.rollback(con);
<<<<<<< HEAD
<<<<<<< HEAD
			
=======
>>>>>>> master
=======
>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6
		} else {
			JdbcUtil.commit(con);
		}
		
		return deleteSuccess;
	}

<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> master
=======

>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6
	public boolean removeReplyFAQ(int idx) {
		boolean deleteReplySuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQReplyDAO dao = FAQReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		deleteReplySuccess = dao.deleteReplyFAQ(idx);
		
		if(!deleteReplySuccess) {
			JdbcUtil.rollback(con);
		} else {
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
		
		return deleteReplySuccess;
	}

<<<<<<< HEAD
<<<<<<< HEAD
	public boolean checkReply(int idx) {
		boolean checkReply = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQReplyDAO dao = FAQReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		checkReply = dao.selectFAQReply(idx);
		
		JdbcUtil.close(con);
		
		return checkReply;
	}
=======
>>>>>>> master
=======
>>>>>>> 10b9fd608ca9e4a54c35035f699131a4a851f3d6

}
