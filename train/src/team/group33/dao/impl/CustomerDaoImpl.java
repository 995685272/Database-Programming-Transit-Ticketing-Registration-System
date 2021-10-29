package team.group33.dao.impl;

import team.group33.bean.Customer;
import team.group33.bean.ReservationInfo;
import team.group33.bean.TrainInfo;
import team.group33.dao.CustomerDao;
import team.group33.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer queryByUsernamePassword(String username,String password) {
        //根据用户名与密码查询用户，并返回用户
        Customer customer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select * from Customer where username=? AND password=?";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            if (resultSet.next()){
                customer = new Customer();
                customer.setUsername(resultSet.getString("username"));
                customer.setFirstName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return customer;
    }

    @Override
    public ArrayList<TrainInfo> customerSearchTravel(String originStation, String destinationStation, String travelDate){
        ArrayList<TrainInfo> list = new ArrayList<TrainInfo>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "SELECT round(TrainSchedule.fare/TrainSchedule.stops*c.stationNoDIS,0)as fare,c.trainNumber,c.originStation,c.destinationStation,c.travelDate,c.departureTime,c.arriveTime,c.runningTime,TrainSchedule.transitLineName from TrainSchedule," +
                    "(select a.trainNumber,a.stationName as originStation,b.stationName as destinationStation,a.scheduleDate as travelDate, a.departureTime,b.arriveTime,subtime(b.arriveTime,a.departureTime) as runningTime,b.scheduleId,b.stationNo-a.stationNo as stationNoDis  from TrainStop as a,TrainStop as b where a.stationName=? and b.stationName=? and a.scheduleId=b.scheduleId and a.stationNo<b.stationNo and a.scheduleDate=?)as c " +
                    "where c.scheduleId=TrainSchedule.scheduleId";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,originStation);
            preparedStatement.setString(2,destinationStation);
            preparedStatement.setString(3,travelDate);
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                TrainInfo trainInfo=new TrainInfo();
                trainInfo.setTrainNumber(resultSet.getString("trainNumber"));
                trainInfo.setOriginStation(resultSet.getString("originStation"));
                trainInfo.setDestinationStation(resultSet.getString("destinationStation"));
                trainInfo.setTravelDate(resultSet.getString("travelDate"));
                trainInfo.setDepartureTime(resultSet.getString("departureTime"));
                trainInfo.setArriveTime(resultSet.getString("arriveTime"));
                trainInfo.setRunningTime(resultSet.getString("runningTime"));
                trainInfo.setFare(resultSet.getString("fare"));
                trainInfo.setTransitLineName(resultSet.getString("transitLineName"));
                System.out.println(trainInfo);
                list.add(trainInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }

    @Override
    public ArrayList<ReservationInfo> customerReservationInfo(String passengerUsername){
        ArrayList<ReservationInfo> list = new ArrayList<ReservationInfo>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select * from Reservation where passengerUsername=? order by travelDate desc,departureTime desc,orderCreateDateTime desc";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,passengerUsername);
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
    public void customerMakeReservation(ReservationInfo reservationInfo){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "INSERT INTO Reservation (orderCreateDateTime, passengerUsername, passengerLastName, passengerFirstName, travelDate, departureTime, originStation, arriveTime, destinationStation, fare, transitLineName, trainNumber, runningTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,reservationInfo.getOrderCreateDateTime());
            preparedStatement.setString(2,reservationInfo.getPassengerUsername());
            preparedStatement.setString(3,reservationInfo.getPassengerLastName());
            preparedStatement.setString(4,reservationInfo.getPassengerFirstName());
            preparedStatement.setString(5,reservationInfo.getTravelDate());
            preparedStatement.setString(6,reservationInfo.getDepartureTime());
            preparedStatement.setString(7,reservationInfo.getOriginStation());
            preparedStatement.setString(8,reservationInfo.getArriveTime());
            preparedStatement.setString(9,reservationInfo.getDestinationStation());
            preparedStatement.setString(10,reservationInfo.getFare());
            preparedStatement.setString(11,reservationInfo.getTransitLineName());
            preparedStatement.setString(12,reservationInfo.getTrainNumber());
            preparedStatement.setString(13,reservationInfo.getRunningTime());
            preparedStatement.executeUpdate();//获得查询结果集
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
    }

    @Override
    public boolean addCustomer(String username,String password,String firstName,String lastName,String email){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "INSERT INTO Customer (username, password, lastName, firstName, emailAddress) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,lastName);
            preparedStatement.setString(4,firstName);
            preparedStatement.setString(5,email);
            preparedStatement.executeUpdate();//获得查询结果集
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return true;
    }
}
