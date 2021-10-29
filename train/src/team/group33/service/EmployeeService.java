package team.group33.service;

import team.group33.bean.Customer;
import team.group33.bean.Employee;
import team.group33.bean.ScheduleInfo;

import java.util.ArrayList;

public interface EmployeeService {
    Employee employeeLogin(String username, String password);

    ArrayList<Customer> employeeSearchReservationCustomer(String transitLineName, String travelDate);

    ArrayList<ScheduleInfo> employeeFindStationSchedule(String stationName);
}
