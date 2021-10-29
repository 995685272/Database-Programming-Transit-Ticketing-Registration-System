package team.group33.filter;
import team.group33.bean.Customer;
import team.group33.bean.Employee;
import team.group33.bean.Manager;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginFilter implements Filter{
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        String uri = request.getRequestURI();
        System.out.println("do filter-"+uri);
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        System.out.println(requestPath.length());
        //arg2.doFilter(request, response);
        if ("login.jsp".equals(requestPath)||"customerLogin.do".equals(requestPath)
                ||"employeeLogin.do".equals(requestPath)||"managerLogin.do".equals(requestPath)
                ||"register.jsp".equals(requestPath)||"customerRegister.do".equals(requestPath)){
            arg2.doFilter(request, response);
        } else{
            HttpSession httpSession = request.getSession(false);
            if (httpSession != null) {
                Object object = httpSession.getAttribute("user");
                if (object == null) {
                    response.sendRedirect("login.jsp");
                }else if("".equals(requestPath)){
                    if(object instanceof Customer){
                        request.getRequestDispatcher("customer.jsp").forward(request, response);
                    } else if(object instanceof Employee){
                        request.getRequestDispatcher("employee.jsp").forward(request, response);
                    }else if(object instanceof Manager){
                        request.getRequestDispatcher("manager.jsp").forward(request, response);
                    }
                }else{
                    arg2.doFilter(request, response);
                }
            }else{
                response.sendRedirect("login.jsp");
            }
        }

        //response.sendRedirect("/login.jsp");
        /*HttpSession httpSession = request.getSession(false);
        String url=null;
        if (httpSession != null) {
            Object object = httpSession.getAttribute("user");
            if (object == null) {
                response.sendRedirect("/login.jsp");
            }
        }else{
            response.sendRedirect("/login.jsp");
        }
        //arg2.doFilter(request, response);
        response.sendRedirect("/login.jsp");
        //request.getRequestDispatcher(requestPath).forward(request, response);
        /*if (requestPath.length()==0){
            requestPath = "login.jsp";
            System.out.println("go login");
            request.getRequestDispatcher(requestPath).forward(request, response);
        } else if ("login.jsp".equals(requestPath)||"customerLogin".equals(requestPath)
                ||"employeeLogin".equals(requestPath)||"managerLogin".equals(requestPath)) {
            arg2.doFilter(request, response);
        } else {
            HttpSession httpSession = request.getSession(false);
            if (httpSession != null) {
                Object object = httpSession.getAttribute("user");
                if (object != null) {
                    if((object instanceof Customer && "customer.jsp".equals(requestPath))
                            || (object instanceof Employee && "employee.jsp".equals(requestPath))
                            || (object instanceof Manager && "manager.jsp".equals(requestPath))
                            ){
                        arg2.doFilter(request, response);
                    }else{
                        System.out.println("no right");
                        requestPath="404.html";
                        request.getRequestDispatcher(requestPath).forward(request, response);
                    }
                } else {
                    System.out.println("no object");
                    requestPath = "login.jsp";
                    request.getRequestDispatcher(requestPath).forward(request, response);
                }
            } else {
                System.out.println("no session");
                requestPath = "login.jsp";
                request.getRequestDispatcher(requestPath).forward(request, response);
            }
        }*/
    }
}
