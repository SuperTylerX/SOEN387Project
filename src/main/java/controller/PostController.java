package controller;

import model.ChatManager;
import utils.ChatConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PostController")
public class PostController extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkReferer(request)) {
            response.sendError(403, "Forbidden");
            return;
        }
        ChatManager chatManager = ChatManager.getInstance();
        String user = request.getParameter("username");
        String messageContent = request.getParameter("message");

        // validate the parameter and store data in Message object
        if (messageContent != null && !messageContent.isEmpty()) {
            chatManager.postMessage(user, messageContent);
        }//TODO: when no message text, pass the error to the front-page to be displayed

        response.sendRedirect("/index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkReferer(request)) {
            response.sendError(403, "Forbidden");
            return;
        }
//        @TODO: Download Chat History
        request.getParameter("from");
        request.getParameter("to");
        request.getParameter("format");


    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public static boolean checkReferer(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        referer = referer == null ? request.getHeader("Referer") : referer;
        if (referer != null) {
            boolean flag = false;
            for (String item : ChatConfig.REFERRER_WHITELIST) {
                if (referer.contains(item)) {
                    flag = true;
                    break;
                }
            }
            return flag;
        } else {
            return false;
        }
    }

}
