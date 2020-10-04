package controller;

import model.ChatManager;
import model.Message;
import utils.XMLTransformer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static utils.Tool.checkReferer;


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

        ChatManager chatManager = ChatManager.getInstance();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String format = request.getParameter("format");
        ArrayList<Message> messages = null;

        if (from != null && to != null && !from.equals("") && !to.equals("")) {
            long start = Long.parseLong(from);
            long end = Long.parseLong(to);

            messages = chatManager.listMessages(start, end);
        } else {
            messages = chatManager.listMessages();
        }
        //probably also need (begin,null) and (null,end) for simplicity
        //After getting the arraylist, print it

        if (format == null || format.isEmpty() || format.equals("text")) {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println(messages);
        } else {
            XMLTransformer transformer = XMLTransformer.getInstance();
            String str = transformer.toXMLString(messages);

            response.setContentType("application/xml");
            PrintWriter out = response.getWriter();
            out.println(str);
        }

    }
}
