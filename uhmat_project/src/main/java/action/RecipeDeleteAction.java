package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Param;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.RecipeDeleteService;
import vo.ActionForward;
import vo.RecipeDTO;

public class RecipeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeDeleteAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String nickname = request.getParameter("nickname");
		System.out.println("idx : " + idx);
		
		String file1 = request.getParameter("file1");
		System.out.println("file1 : " + file1);
		
		String filePath = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload" + file1;
		File deleteFile = new File(filePath);
		
		// RecipeDeleteProService - isDeleteMate() 메서드를 호출하여 삭제 요청
		// => 파라미터 : 글번호, 닉네임    리턴타입 : boolean(isDeleteSuccess)
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
			
			if(deleteFile.exists()) {
				deleteFile.delete();
				System.out.println("파일 삭제");
				
				forward = new ActionForward();
				forward.setPath("RecipeList.co?pageNum=" + request.getParameter("pageNum"));
				forward.setRedirect(true);
				
			} else {
				System.out.println("파일이 존재하지 않습니다");
			}
				
				
			
			
		}
		return forward;
	}

}
