package team.group33.dao;
import team.group33.bean.Customer;
import team.group33.bean.ReservationInfo;
import team.group33.bean.TrainInfo;

import java.sql.ResultSet;
import java.util.ArrayList;


public interface CustomerDao {
    Customer queryByUsernamePassword(String username,String Password);//返回查询到的用户

    ArrayList<TrainInfo> customerSearchTravel(String originStation, String destinationStation, String travelDate);
    ArrayList<ReservationInfo> customerReservationInfo(String passengerUsername);
    void customerMakeReservation(ReservationInfo reservationInfo);

    boolean addCustomer(String username,String password,String firstName,String lastName,String email);
}
