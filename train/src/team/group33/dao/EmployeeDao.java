package team.group33.dao;

import team.group33.bean.Customer;
import team.group33.bean.Employee;
import team.group33.bean.ReservationInfo;
import team.group33.bean.ScheduleInfo;

import java.util.ArrayList;

public interface EmployeeDao {
    Employee queryByUsernamePassword(String username, String Password);//返回查询到的用户

    ArrayList<Customer> queryCustomerByTransitLineNameAndtravelDate(String transitLineName,String travelDate);

    ArrayList<ScheduleInfo> queryScheduleInfoByStationName(String stationName);

}
