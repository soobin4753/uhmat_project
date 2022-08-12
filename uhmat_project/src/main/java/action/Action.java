package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.MultipartResponse;

import vo.ActionForward;

public interface Action { 

	public abstract ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
} 

