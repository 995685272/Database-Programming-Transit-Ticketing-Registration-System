package team.group33.service.impl;

import team.group33.bean.ForumMessage;
import team.group33.dao.ForumDao;
import team.group33.dao.impl.ForumDaoImpl;
import team.group33.service.ForumService;

import java.util.ArrayList;

public class ForumServiceImpl implements ForumService{
    private ForumDaoImpl forumDaoImpl=new ForumDaoImpl();
    @Override
    public ArrayList<ForumMessage> getForumMessage(){
        return forumDaoImpl.queryForumMessage();
    }

    @Override
    public void addForumMessage(String username,String message,String createTime,String userType){
        forumDaoImpl.addForumMessage(username,message,createTime,userType);
    }
}
