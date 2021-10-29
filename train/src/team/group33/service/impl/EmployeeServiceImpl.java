package team.group33.service.impl;

import team.group33.bean.Customer;
import team.group33.bean.Employee;
import team.group33.bean.ScheduleInfo;
import team.group33.dao.impl.EmployeeDaoImpl;
import team.group33.service.EmployeeService;

import java.util.ArrayList;


public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDaoImpl employeeDaoImpl=new EmployeeDaoImpl();
    @Override
    public Employee employeeLogin(String username, String password) {
        return employeeDaoImpl.queryByUsernamePassword(username,password);
    }

    @Override
    public ArrayList<Customer> employeeSearchReservationCustomer(String transitLineName, String travelDate){
        return employeeDaoImpl.queryCustomerByTransitLineNameAndtravelDate(transitLineName,travelDate);
    }

    @Override
    public ArrayList<ScheduleInfo> employeeFindStationSchedule(String stationName){
        return employeeDaoImpl.queryScheduleInfoByStationName(stationName);
    }
}
