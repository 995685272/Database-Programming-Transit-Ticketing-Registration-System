package team.group33.dao;

import team.group33.bean.ForumMessage;

import java.util.ArrayList;

public interface ForumDao {
    ArrayList<ForumMessage> queryForumMessage();

    void addForumMessage(String username,String message,String createTime,String userType);
}
