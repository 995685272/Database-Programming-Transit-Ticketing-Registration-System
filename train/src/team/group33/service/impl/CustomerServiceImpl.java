package team.group33.service.impl;

import team.group33.bean.Customer;
import team.group33.bean.ReservationInfo;
import team.group33.bean.TrainInfo;
import team.group33.dao.impl.CustomerDaoImpl;
import team.group33.service.CustomerService;

import java.util.ArrayList;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDaoImpl customerDaoImpl=new CustomerDaoImpl();
    @Override
    public Customer customerLogin(String username,String password) {
        return customerDaoImpl.queryByUsernamePassword(username,password);
    }

    @Override
    public ArrayList<TrainInfo> customerSearchTravel(String originStation, String destinationStation, String travelDate){
        return customerDaoImpl.customerSearchTravel(originStation,destinationStation,travelDate);
    }

    @Override
    public ArrayList<ReservationInfo> customerReservationInfo(String passengerUsername){
        return customerDaoImpl.customerReservationInfo(passengerUsername);
    }

    @Override
    public  void customerMakeReservation(ReservationInfo reservationInfo){
        customerDaoImpl.customerMakeReservation(reservationInfo);
    }

    @Override
    public boolean registerCustomer(String username,String password,String firstName,String lastName,String email){
        return customerDaoImpl.addCustomer(username,password,firstName,lastName,email);
    }
}
