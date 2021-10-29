package team.group33.service;

import team.group33.bean.*;

import java.util.ArrayList;

public interface ManagerService {
    Manager managerLogin(String username, String password);

    ArrayList<ReservationInfo> managerGetReservationInfo(String transitLineName, String customerUsername);

    int managerGetSumRevenue(String transitLineName, String customerUsername);

    ArrayList<RevenueInfo> managerGetRevenueReport();


    Customer managerGetMostTotalRevenueCustomer();

    ArrayList<TransitLineInfo> managerGet5mostTransitLine();

    ArrayList<Employee> managerGetEmployeeList();

    void managerDeleteEmployee(String username);

    void managerAddEmployee(String username,String password,String SSN,String firstName,String lastName);
}
