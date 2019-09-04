package javascript.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class boardServ
 */
@WebServlet("/boardServ")
public class boardServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardServ() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		BoardDAO dao = new BoardDAO(); 
		
		if(action.equals("list")) {
			List<BoardDTO> list = dao.getBoardList();
			out.print(JSONArray.fromObject(list).toString()); //out.print안에 넣음
						

		} else if(action.equals("insert")) {
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			BoardDTO board = new BoardDTO();
			board.setTitle(title);
			board.setContents(contents);
			int boardNo = dao.insertBoard(board); ////덜함
			out.print(boardNo);
			
		} else if(action.equals("delete")) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			dao.deleteBoard(boardNo);
			
		}else if(action.equals("update")) {
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			BoardDTO board = new BoardDTO();
			board.setBoardNo(boardNo); 
			board.setContents(contents);
			board.setTitle(title);  
			int cnt = dao.updateBoard(board);  
			
			if(cnt > 0)
				out.print("ok");	
			
			
			//덧글추가
		} else if(action.equals("cntList")) {
			
			List<BoardDTO> cntList = dao.getCntList();
			out.print(JSONArray.fromObject(cntList).toString()); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
