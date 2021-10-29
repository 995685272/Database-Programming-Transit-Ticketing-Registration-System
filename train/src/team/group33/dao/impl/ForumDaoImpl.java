package team.group33.dao.impl;

import team.group33.bean.ForumMessage;
import team.group33.bean.ReservationInfo;
import team.group33.dao.ForumDao;
import team.group33.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ForumDaoImpl implements ForumDao{
    @Override
    public ArrayList<ForumMessage> queryForumMessage(){
        ArrayList<ForumMessage> list = new ArrayList<ForumMessage>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "select * from Forum ";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            resultSet = preparedStatement.executeQuery();//获得查询结果集
            while (resultSet.next()){
                ForumMessage forumMessage=new ForumMessage();
                forumMessage.setUsername(resultSet.getString("username"));
                forumMessage.setCreateTime(resultSet.getString("createTime"));
                forumMessage.setMessage(resultSet.getString("message"));
                forumMessage.setUserType(resultSet.getString("userType"));
                list.add(forumMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
        return list;
    }

    @Override
    public void addForumMessage(String username,String message,String createTime,String userType){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnect();//首先连接数据库
            String sql = "INSERT INTO Forum (username, createTime, message, userType) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);//执行sql语句
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,createTime);
            preparedStatement.setString(3,message);
            preparedStatement.setString(4,userType);
            preparedStatement.executeUpdate();//获得查询结果集
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(preparedStatement,connection,resultSet);
        }
    }
}
