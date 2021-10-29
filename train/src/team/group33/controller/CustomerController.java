package team.group33.controller;

import team.group33.bean.*;
import team.group33.service.impl.CustomerServiceImpl;
import team.group33.service.impl.EmployeeServiceImpl;
import team.group33.service.impl.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost(request, response);
        String uri = request.getRequestURI();
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl();
        String url=null;
        if("customerReservationInfo.do".equals(requestPath)){
            Object object  = request.getSession(false).getAttribute("user");
            Customer customer=(Customer) object;
            ArrayList<ReservationInfo> reservationInfoList=customerServiceImpl.customerReservationInfo(customer.getUsername());
            request.setAttribute("reservationInfoList",reservationInfoList);
            url="customer.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String requestPath = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl();
        String url=null;
        if("customerSearchTravel.do".equals(requestPath)){
            String originStation=request.getParameter("originStation");
            String destinationStation=request.getParameter("destinationStation");
            String travelDate=request.getParameter("travelDate");
            ArrayList<TrainInfo> trainInfoList=customerServiceImpl.customerSearchTravel(originStation,destinationStation,travelDate);
            request.getSession().setAttribute("trainInfoList",trainInfoList);
            url="customer.jsp";
        }else if("customerMakeReservation.do".equals(requestPath)){
            String trainInfoListIndex=request.getParameter("trainInfoListIndex");
            Object object1  = request.getSession(false).getAttribute("trainInfoList");

            ArrayList<TrainInfo> trainInfoList=(ArrayList<TrainInfo>) object1;
            TrainInfo trainInfo=trainInfoList.get(Integer.valueOf(trainInfoListIndex));
            Object object2  = request.getSession(false).getAttribute("user");
            Customer customer=(Customer) object2;
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            ReservationInfo reservationInfo=new ReservationInfo();
            reservationInfo.setOrderCreateDateTime(ft.format(dNow));
            reservationInfo.setPassengerUsername(customer.getUsername());
            reservationInfo.setPassengerLastName(customer.getLastName());
            reservationInfo.setPassengerFirstName(customer.getFirstName());
            reservationInfo.setTravelDate(trainInfo.getTravelDate());
            reservationInfo.setDepartureTime(trainInfo.getDepartureTime());
            reservationInfo.setOriginStation(trainInfo.getOriginStation());
            reservationInfo.setArriveTime(trainInfo.getArriveTime());
            reservationInfo.setDestinationStation(trainInfo.getDestinationStation());
            reservationInfo.setFare(trainInfo.getFare());
            reservationInfo.setTransitLineName(trainInfo.getTransitLineName());
            reservationInfo.setTrainNumber(trainInfo.getTrainNumber());
            reservationInfo.setRunningTime(trainInfo.getRunningTime());
            customerServiceImpl.customerMakeReservation(reservationInfo);
            url="customer.jsp";
        }else if("customerRegister.do".equals(requestPath)){
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String firstName=request.getParameter("firstName");
            String lastName=request.getParameter("lastName");
            String email=request.getParameter("email");
            boolean result=customerServiceImpl.registerCustomer(username,password,firstName,lastName,email);
            if(result){
                url="login.jsp";
            }else{
                url="register.jsp?error=register_error";
            }

        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
