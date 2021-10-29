package team.group33.controller;

import team.group33.bean.*;
import team.group33.service.impl.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        ManagerServiceImpl managerServiceImpl=new ManagerServiceImpl();
        String url=null;
        if("managerLineRevenue.do".equals(requestPath)|| "managerCustomerRevenue.do".equals(requestPath)){
            String transitLineNameOrCustomerUsername=request.getParameter("transitLineNameOrCustomerUsername");
            int sumRevenue=0;
            if("managerLineRevenue.do".equals(requestPath)){
                sumRevenue= managerServiceImpl.managerGetSumRevenue(transitLineNameOrCustomerUsername,null);
            }else{
                sumRevenue= managerServiceImpl.managerGetSumRevenue(null,transitLineNameOrCustomerUsername);
            }
            request.getSession().setAttribute("sumRevenue",sumRevenue);
            url="manager.jsp";
        }else if("managerLineReservation.do".equals(requestPath)|| "managerCustomerReservation.do".equals(requestPath)){
            String transitLineNameOrCustomerUsername=request.getParameter("transitLineNameOrCustomerUsername");
            ArrayList<ReservationInfo> reservationInfoList=null;
            if("managerLineReservation.do".equals(requestPath)){
                reservationInfoList= managerServiceImpl.managerGetReservationInfo(transitLineNameOrCustomerUsername,null);
            }else{
                reservationInfoList= managerServiceImpl.managerGetReservationInfo(null,transitLineNameOrCustomerUsername);
            }
            request.getSession().setAttribute("reservationInfoList",reservationInfoList);
            url="manager.jsp";
        }else if("managerSaleReport.do".equals(requestPath)){
            ArrayList<RevenueInfo> revenueInfoList= managerServiceImpl.managerGetRevenueReport();
            request.getSession().setAttribute("revenueInfoList",revenueInfoList);
            url="manager.jsp";
        }else if("managerGetMostRevenueCustomer.do".equals(requestPath)){
            Customer customer=managerServiceImpl.managerGetMostTotalRevenueCustomer();
            request.getSession().setAttribute("customer",customer);
            url="manager.jsp";
        }else if("managerGet5mostActiveLine.do".equals(requestPath)){
            ArrayList<TransitLineInfo> transitLineInfoList=managerServiceImpl.managerGet5mostTransitLine();
            request.getSession().setAttribute("transitLineInfoList",transitLineInfoList);
            url="manager.jsp";
        }else if("managerGetEmployeeList.do".equals(requestPath)){
            ArrayList<Employee> employeeList=managerServiceImpl.managerGetEmployeeList();
            request.getSession().setAttribute("employeeList",employeeList);
            url="manager.jsp";
        }else if("managerDeleteEmployee.do".equals(requestPath)){
            String username=request.getParameter("username");
            managerServiceImpl.managerDeleteEmployee(username);
            url="managerGetEmployeeList.do";
        }else if("managerAddEmployee.do".equals(requestPath)){
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String SSN=request.getParameter("SSN");
            managerServiceImpl.managerAddEmployee(username,password,SSN,firstName,lastName);
            url="managerGetEmployeeList.do";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
