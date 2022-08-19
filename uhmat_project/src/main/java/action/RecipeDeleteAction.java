package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecipeDeleteService;
import vo.ActionForward;

public class RecipeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeDeleteAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String nickname = request.getParameter("nickname");
		System.out.println("idx : " + idx);
		
		// RecipeDeleteProService - isDeleteMate() 메서드를 호출하여 삭제 요청
		// => 파라미터 : 글번호    리턴타입 : boolean(isDeleteSuccess)
		RecipeDeleteService service = new RecipeDeleteService();
		boolean isDeleteSuccess = service.deleteRecipe(idx, nickname);
		
		// 게시판 삭제하면 댓글도 삭제
//		service.deleteRecipeReply(idx);
		
		if(!isDeleteSuccess) { // 게시글 삭제 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 게시글 삭제 성공 
			
			forward = new ActionForward();
			forward.setPath("RecipeList.co?pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
			
		}
		return forward;
	}

}
