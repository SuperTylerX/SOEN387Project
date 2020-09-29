package Controller;

import Model.ChatManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PostController")
public class PostController extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        @TODO: add new message to
        ChatManager chatManager = ChatManager.getInstance();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        @TODO: Download Chat History
        request.getParameter("from");
        request.getParameter("to");
        request.getParameter("format");


    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete Chatting history
        ChatManager chatManager = ChatManager.getInstance();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        if (from != null && to != null && !from.equals("") && !to.equals("")) {
            long begin = Long.parseLong(from);
            long end = Long.parseLong(to);
            chatManager.clearChat(begin, end);
        } else {
            chatManager.clearChat();
        }
        response.sendRedirect("/index.jsp");
    }

}
