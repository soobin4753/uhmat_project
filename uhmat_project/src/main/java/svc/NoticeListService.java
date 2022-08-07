package svc;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class NoticeListService {

	public int getListCount() {
//		System.out.println("NoticeListService - getListCount");
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectListcount();
//		System.out.println(listCount);
		
		JdbcUtil.close(con);
		
		return listCount;
	}

	public ArrayList<NoticeDTO> getNoticeList(int pageNum, int listLimit) {
//		System.out.println("NoticeListService - getNoticeList ");
		ArrayList<NoticeDTO> list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectNoticeList(pageNum, listLimit);
//		System.out.println(list);
		
		JdbcUtil.close(con);
		
		return list;
	}

}
