package team.group33.service;

import team.group33.bean.Customer;
import team.group33.bean.ReservationInfo;
import team.group33.bean.TrainInfo;

import java.util.ArrayList;

public interface CustomerService {
    Customer customerLogin(String username,String password);

    ArrayList<TrainInfo> customerSearchTravel(String originStation, String destinationStation, String travelDate);

    ArrayList<ReservationInfo> customerReservationInfo(String passengerUsername);

    void customerMakeReservation(ReservationInfo reservationInfo);

    boolean registerCustomer(String username,String password,String firstName,String lastName,String email);
}
