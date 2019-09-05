package javascript.testboard;

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
 * Servlet implementation class TestBoardServ
 */
@WebServlet("/TestBoardServ")
public class TestBoardServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestBoardServ() {
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
		
		if(action.equals("list")) { //에러
			List<BoardDTO> list = dao.getBoardList();
			out.print(JSONArray.fromObject(list).toString()); //out.print안에 넣음
			System.out.println(JSONArray.fromObject(list).toString());

		} else if(action.equals("insert")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			BoardDTO board = new BoardDTO();
			board.setTitle(title);
			board.setContent(content);
			int boardNo = dao.insertBoard(board); 
			out.print(boardNo);
			
		} else if(action.equals("delete")) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			dao.deleteBoard(boardNo);
			
		}else if(action.equals("update")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			BoardDTO board = new BoardDTO();
			board.setBoardNo(boardNo); 
			board.setContent(content);
			board.setTitle(title);  
			int cnt = dao.updateBoard(board);  
			
			if(cnt > 0)
				out.print("ok");	
			
		}else if(action.equals("insertCmt")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			BoardDTO board = new BoardDTO();
			board.setTitle(title);
			board.setContent(content);
			int boardNo = dao.insertCnt(board); 
			out.print(boardNo);		
			
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
