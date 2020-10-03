package controller;

import model.ChatManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.Tool.checkReferer;

@WebServlet(name = "DeleteController")
public class DeleteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete Chatting history
        if (!checkReferer(request)) {
            response.sendError(403, "Forbidden");
            return;
        }
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
