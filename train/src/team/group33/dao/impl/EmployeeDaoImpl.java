package team.group33.dao.impl;

import team.group33.bean.Customer;
import team.group33.bean.Employee;
import team.group33.bean.ScheduleInfo;
import team.group33.dao.EmployeeDao;
import team.group33.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Employee queryByUsernamePassword(String username, String password) {
        //根据用户名与密码查询用户，并返回用户
        Employee employee = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select * from Employee where username=? AND password=?";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            if (resultSet.next()){
                employee = new Employee();
                employee.setUsername(resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return employee;
    }

    @Override
    public ArrayList<Customer> queryCustomerByTransitLineNameAndtravelDate(String transitLineName, String travelDate){
        ArrayList<Customer> list = new ArrayList<Customer>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select passengerUsername,passengerFirstName,passengerLastName from Reservation where travelDate=? and transitLineName=? group by passengerUsername";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,travelDate);
            preparedStatement.setString(2,transitLineName);
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                Customer customer=new Customer();
                customer.setUsername(resultSet.getString("passengerUsername"));
                customer.setFirstName(resultSet.getString("passengerFirstName"));
                customer.setLastName(resultSet.getString("passengerLastName"));
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }

    @Override
    public ArrayList<ScheduleInfo> queryScheduleInfoByStationName(String stationName){
        ArrayList<ScheduleInfo> list = new ArrayList<ScheduleInfo>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select * from TrainSchedule where originStation=? or destinationStation=?;";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,stationName);
            preparedStatement.setString(2,stationName);
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                ScheduleInfo scheduleInfo=new ScheduleInfo();
                scheduleInfo.setTransitLineName(resultSet.getString("transitLineName"));
                scheduleInfo.setTrainNumber(resultSet.getString("trainNumber"));
                scheduleInfo.setOriginStation(resultSet.getString("originStation"));
                scheduleInfo.setDestinationStation(resultSet.getString("destinationStation"));
                scheduleInfo.setScheduleDate(resultSet.getString("scheduleDate"));
                scheduleInfo.setDepartureTime(resultSet.getString("departureTime"));
                scheduleInfo.setArriveTime(resultSet.getString("arriveTime"));
                scheduleInfo.setFare(resultSet.getString("fare"));
                scheduleInfo.setRunningTime(resultSet.getString("runningTime"));
                scheduleInfo.setStops(resultSet.getString("stops"));
                list.add(scheduleInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }
}
