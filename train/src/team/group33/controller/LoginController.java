package team.group33.controller;
import team.group33.bean.Customer;
import team.group33.bean.Employee;
import team.group33.bean.Manager;
import team.group33.service.impl.CustomerServiceImpl;
import team.group33.service.impl.EmployeeServiceImpl;
import team.group33.service.impl.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String uri = request.getRequestURI();
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        String url=null;
        if("customerLogin.do".equals(requestPath)){
            CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
            Customer customer = customerServiceImpl.customerLogin(username,password);
            if(customer!=null){
                url ="customer.jsp";
                request.getSession().setAttribute("user", customer);
            } else{
                url ="login.jsp?error=login_error";
            }
        }else if("employeeLogin.do".equals(requestPath)){
            EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
            Employee employee = employeeServiceImpl.employeeLogin(username,password);
            if(employee!=null){
                url ="employee.jsp";
                request.getSession().setAttribute("user", employee);
            } else{
                url ="login.jsp?error=login_error";
            }
        }else if("managerLogin.do".equals(requestPath)){
            ManagerServiceImpl managerServiceImpl = new ManagerServiceImpl();
            Manager manager = managerServiceImpl.managerLogin(username,password);
            if(manager!=null){
                url ="manager.jsp";
                request.getSession().setAttribute("user", manager);
            } else{
                url ="login.jsp?error=login_error";
            }
        }else if("logout.do".equals(requestPath)){
            request.getSession().setAttribute("user",null);
            url="login.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);


    }

}