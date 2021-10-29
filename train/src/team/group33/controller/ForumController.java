package team.group33.controller;

import team.group33.bean.*;
import team.group33.service.impl.CustomerServiceImpl;
import team.group33.service.impl.ForumServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForumController extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        ForumServiceImpl forumServiceImpl=new ForumServiceImpl();
        String url=null;
        if("forumMessage.do".equals(requestPath)) {
            ArrayList<ForumMessage> forumMessageList=forumServiceImpl.getForumMessage();
            request.getSession().setAttribute("forumMessageList",forumMessageList);
            url="forum.jsp";
            System.out.println("sdsfs");
        }else if("addForumMessage.do".equals(requestPath)){
            String message=request.getParameter("message");
            Object object1  = request.getSession(false).getAttribute("user");
            String type=null;
            String username=null;
            if(object1 instanceof Customer){
                type="customer";
                Customer c=(Customer) object1;
                username=c.getUsername();
            }else if(object1 instanceof Employee){
                type="employee";
                Employee e=(Employee) object1;
                username=e.getUsername();
            }else{
                type="manager";
                Manager m=(Manager) object1;
                username=m.getUsername();
            }
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            forumServiceImpl.addForumMessage(username,message,ft.format(dNow),type);
            url="forumMessage.do";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
