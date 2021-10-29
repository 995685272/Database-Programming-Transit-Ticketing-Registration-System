package team.group33.controller;

import team.group33.bean.*;
import team.group33.service.impl.CustomerServiceImpl;
import team.group33.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        EmployeeServiceImpl employeeServiceImpl=new EmployeeServiceImpl();
        String url=null;
        if("employeeSearchReservationCustomer.do".equals(requestPath)){
            String transitLineName=request.getParameter("transitLineName");
            String travelDate=request.getParameter("travelDate");
            ArrayList<Customer> customerList= employeeServiceImpl.employeeSearchReservationCustomer(transitLineName,travelDate);
            request.getSession().setAttribute("customerList",customerList);
            url="employee.jsp";
        }else if("employeeFindStationSchedule.do".equals(requestPath)){
            String stationName=request.getParameter("stationName");
            ArrayList<ScheduleInfo> scheduleInfoList=employeeServiceImpl.employeeFindStationSchedule(stationName);
            request.getSession().setAttribute("scheduleInfoList",scheduleInfoList);
            url="employee.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
