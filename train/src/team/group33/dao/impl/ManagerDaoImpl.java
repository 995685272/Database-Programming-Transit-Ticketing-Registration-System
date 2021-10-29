package team.group33.dao.impl;

import team.group33.bean.*;
import team.group33.dao.ManagerDao;
import team.group33.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManagerDaoImpl implements ManagerDao{
    @Override
    public Manager queryByUsernamePassword(String username, String password) {
        //根据用户名与密码查询用户，并返回用户
        Manager manager = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select * from Manager where username=? AND password=?";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            if (resultSet.next()){
                manager = new Manager();
                manager.setUsername(resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return manager;
    }

    @Override
    public ArrayList<ReservationInfo> queryReservationInfoByTransitLineNameOrCustomerUsername(String transitLineName, String customerUsername){
        //根据用户名与密码查询用户，并返回用户
        ArrayList<ReservationInfo> list = new ArrayList<ReservationInfo>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql=null;
            if(transitLineName!=null){
                sql="select * from Reservation where transitLineName=?";
                preparedStatement = connection.prepareStatement(sql);//执行sql语句
                preparedStatement.setString(1,transitLineName);
            }else{
                sql="select * from Reservation where passengerUsername=?";
                preparedStatement = connection.prepareStatement(sql);//执行sql语句
                preparedStatement.setString(1,customerUsername);
            }
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                ReservationInfo reservationInfo=new ReservationInfo();
                reservationInfo.setTrainNumber(resultSet.getString("trainNumber"));
                reservationInfo.setTransitLineName(resultSet.getString("transitLineName"));
                reservationInfo.setOriginStation(resultSet.getString("originStation"));
                reservationInfo.setDestinationStation(resultSet.getString("destinationStation"));
                reservationInfo.setTravelDate(resultSet.getString("travelDate"));
                reservationInfo.setDepartureTime(resultSet.getString("departureTime"));
                reservationInfo.setArriveTime(resultSet.getString("arriveTime"));
                reservationInfo.setRunningTime(resultSet.getString("runningTime"));
                reservationInfo.setFare(resultSet.getString("fare"));
                reservationInfo.setPassengerUsername(resultSet.getString("passengerUsername"));
                reservationInfo.setPassengerFirstName(resultSet.getString("passengerFirstName"));
                reservationInfo.setPassengerLastName(resultSet.getString("passengerLastName"));
                reservationInfo.setOrderCreateDateTime(resultSet.getString("orderCreateDateTime"));
                reservationInfo.setPassengerUsername(resultSet.getString("passengerUsername"));
                reservationInfo.setPassengerFirstName(resultSet.getString("passengerFirstName"));
                reservationInfo.setPassengerLastName(resultSet.getString("passengerLastName"));
                list.add(reservationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }

    @Override
    public int querySumRevenueByTransitLineNameOrCustomerUsername(String transitLineName, String customerUsername){
        int SumRevenue=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql=null;
            if(transitLineName!=null){
                sql="select sum(fare) as total from Reservation where transitLineName=?";
                preparedStatement = connection.prepareStatement(sql);//执行sql语句
                preparedStatement.setString(1,transitLineName);
            }else{
                sql="select sum(fare) as total from Reservation where passengerUsername=?";
                preparedStatement = connection.prepareStatement(sql);//执行sql语句
                preparedStatement.setString(1,customerUsername);
            }
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            if (resultSet.next()){
                SumRevenue=Integer.parseInt(resultSet.getString("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return SumRevenue;
    }

    @Override
    public ArrayList<RevenueInfo> queryRevenuePerMonth(){
        ArrayList<RevenueInfo> list = new ArrayList<RevenueInfo>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql="SELECT sum(fare) as total,date_format(orderCreateDateTime,'%Y-%m') as orderCreateDate,count(reservationNo) as reservationNum FROM Reservation group by date_format(orderCreateDateTime,'%Y-%m') order by  date_format(orderCreateDateTime,'%Y-%m') desc";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                RevenueInfo revenueInfo=new RevenueInfo();
                revenueInfo.setTotal(resultSet.getString("total"));
                revenueInfo.setReservationNum(resultSet.getString("reservationNum"));
                revenueInfo.setOrderCreateDate(resultSet.getString("orderCreateDate"));
                list.add(revenueInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }

    @Override
    public  ArrayList<TransitLineInfo> query5mostTransitLine(){
        ArrayList<TransitLineInfo> list = new ArrayList<TransitLineInfo>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql="select count(*) as reservationNum,transitLineName from Reservation group by transitLineName order by reservationNum desc limit 5;";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                TransitLineInfo transitLineInfo=new TransitLineInfo();
                transitLineInfo.setReservationNum(resultSet.getString("reservationNum"));
                transitLineInfo.setTransitLineName(resultSet.getString("transitLineName"));
                list.add(transitLineInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }

    @Override
    public Customer queryMostTotalRevenueCustomer(){
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select sum(fare) as total,passengerUsername,passengerFirstName,passengerLastName from Reservation group by transitLineName order by total desc limit 1;";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            if (resultSet.next()){
                customer = new Customer();
                customer.setUsername(resultSet.getString("passengerUsername"));
                customer.setFirstName(resultSet.getString("passengerFirstName"));
                customer.setLastName(resultSet.getString("passengerLastName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return customer;
    }

    @Override
    public   ArrayList<Employee> queryEmployeeList(){
        ArrayList<Employee> list = new ArrayList<Employee>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql="select *  from Employee";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                Employee employee=new Employee();
                employee.setUsername(resultSet.getString("username"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setPassword(resultSet.getString("password"));
                employee.setSSN(resultSet.getString("SSN"));
                list.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }

    @Override
    public void deleteEmployee(String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "delete from Employee where username=?";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,username);
            preparedStatement.executeUpdate();//获得查询结果集
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
    }

    @Override
    public void addEmployee(String username,String password,String SSN,String firstName,String lastName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "INSERT INTO Employee (username, password, SSN, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,SSN);
            preparedStatement.setString(4,firstName);
            preparedStatement.setString(5,lastName);
            preparedStatement.executeUpdate();//获得查询结果集
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
    }

}
