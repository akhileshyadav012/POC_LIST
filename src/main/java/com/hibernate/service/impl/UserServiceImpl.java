package com.hibernate.service.impl;

import com.hibernate.entity.User;
import com.hibernate.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public User addUser(User user){
        logger.info("UserServiceImpl - Inside addUser Method");
        hibernateTemplate.save(user);
        return user;
    }

    public User getUserById(Long id){
        logger.info("UserServiceImpl - Inside getUserById method");
        User user = hibernateTemplate.get(User.class, id);
        return user;
    }

    public List<User> getAllUsers(){
        logger.info("UserServiceImpl - Inside getAllUsers method");
        List<User> users = hibernateTemplate.loadAll(User.class);
        return users;
    }

    public void deleteUserById(Long id){
        logger.info("UserServiceImpl - Inside deleteUserById method");
        User user = hibernateTemplate.get(User.class, id);
        if (user != null){
            Transaction transaction = hibernateTemplate.getSessionFactory().getCurrentSession().beginTransaction();
            hibernateTemplate.delete(user);
            transaction.commit();
        }
    }

    public void updateUserById(Long id, User user){
        logger.info("UserServiceImpl - Inside deleteUserById method");
        User updateUser = hibernateTemplate.get(User.class, id);
        updateUser.setName(user.getName());
        updateUser.setLocation(user.getLocation());
        updateUser.setSalary(user.getSalary());
        Transaction transaction = hibernateTemplate.getSessionFactory().getCurrentSession().beginTransaction();
        hibernateTemplate.saveOrUpdate(updateUser);
        transaction.commit();
    }


}
