package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MateReplyWriteService;
import vo.ActionForward;
import vo.CommentDTO;
import vo.MateDTO;

public class MateReplyWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateReplyProAction");
		
		ActionForward forward = null;
		
		CommentDTO mateComment = new CommentDTO();
		mateComment.setIdx(Integer.parseInt(request.getParameter("idx")));
		mateComment.setNickname(request.getParameter("nickname"));
		mateComment.setContent(request.getParameter("content"));
//		mateComment.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
//		mateComment.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
//		mateComment.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
//		mateComment.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		System.out.println(mateComment);
		
		// MateReplyProService 의 replyMate() 메서드를 호출하여 답글 등록 작업 요청
		// => 파라미터 : CommentDTO 객체   리턴타입 : boolean(isReplySuccess)
		MateReplyWriteService service = new MateReplyWriteService();
		boolean isReplySuccess = service.replyMate(mateComment);
		
		// 답글 등록 작업 요청 처리 결과 판별
		if(!isReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
//			forward.setPath("MateDetail.co?idx="+ request.getParameter("idx") +"&pageNum=" +request.getParameter("pageNum"));
//			forward.setPath("MateDetail.co?pageNum=" + pageNum + "&idx=" + idx);
			forward.setPath("MateReplyList.co?idx=" + request.getParameter("idx"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
