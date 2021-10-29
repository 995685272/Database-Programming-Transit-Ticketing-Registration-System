package team.group33.service;

import team.group33.bean.ForumMessage;

import java.util.ArrayList;

public interface ForumService {
    ArrayList<ForumMessage> getForumMessage();

    void addForumMessage(String username,String message,String createTime,String userType);
}
