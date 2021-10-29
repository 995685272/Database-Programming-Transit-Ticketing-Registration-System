package team.group33.dao;

import team.group33.bean.*;

import java.util.ArrayList;

public interface ManagerDao {
    Manager queryByUsernamePassword(String username, String Password);//返回查询到的用户

    ArrayList<ReservationInfo> queryReservationInfoByTransitLineNameOrCustomerUsername(String transitLineName, String customerUsername);

    int querySumRevenueByTransitLineNameOrCustomerUsername(String transitLineName, String customerUsername);

    ArrayList<RevenueInfo> queryRevenuePerMonth();

    Customer queryMostTotalRevenueCustomer();

    ArrayList<TransitLineInfo> query5mostTransitLine();

    ArrayList<Employee> queryEmployeeList();

    void deleteEmployee(String username);

    void addEmployee(String username,String password,String SSN,String firstName,String lastName);
}
