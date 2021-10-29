package team.group33.service.impl;

import team.group33.bean.*;
import team.group33.dao.impl.ManagerDaoImpl;
import team.group33.service.ManagerService;

import java.util.ArrayList;

public class ManagerServiceImpl implements ManagerService {
    private ManagerDaoImpl managerDaoImpl=new ManagerDaoImpl();
    @Override
    public Manager managerLogin(String username, String password) {
        return managerDaoImpl.queryByUsernamePassword(username,password);
    }

    @Override
    public ArrayList<ReservationInfo> managerGetReservationInfo(String transitLineName, String customerUsername){
        return managerDaoImpl.queryReservationInfoByTransitLineNameOrCustomerUsername(transitLineName,customerUsername);
    }

    @Override
    public int managerGetSumRevenue(String transitLineName, String customerUsername){
        return managerDaoImpl.querySumRevenueByTransitLineNameOrCustomerUsername(transitLineName,customerUsername);
    }

    @Override
    public ArrayList<RevenueInfo> managerGetRevenueReport(){
        return managerDaoImpl.queryRevenuePerMonth();
    }

    @Override
    public Customer managerGetMostTotalRevenueCustomer(){
        return managerDaoImpl.queryMostTotalRevenueCustomer();
    }

    @Override
    public ArrayList<TransitLineInfo> managerGet5mostTransitLine(){
        return managerDaoImpl.query5mostTransitLine();
    }

    @Override
    public ArrayList<Employee> managerGetEmployeeList(){
        return managerDaoImpl.queryEmployeeList();
    }

    @Override
    public  void managerDeleteEmployee(String username){
        managerDaoImpl.deleteEmployee(username);
    }

    @Override
    public void managerAddEmployee(String username,String password,String SSN,String firstName,String lastName){
        managerDaoImpl.addEmployee(username,password,SSN,firstName,lastName);
    }
}
