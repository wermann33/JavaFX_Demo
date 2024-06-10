package com.example.javafx_demo.DAL.repositories;
import com.example.javafx_demo.BL.models.UserModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UserDAO  extends BaseDAO<UserModel> {
    private final Logger logger;

    public UserDAO() {
        logger = LoggerFactory.getLogger(UserDAO.class);
    }

    public UserModel findByUsername(String username){
        logger.debug("Finding user by username: {}", username);

        try(Session session = getSession()){
            Query<UserModel> query = session.createQuery("FROM UserModel WHERE username = :username",
                    UserModel.class);
            query.setParameter("username", username);
            logger.debug("Found user: {}", username);
            return query.uniqueResult();
        }
    }

    public List<UserModel> findALl(){
        try(Session session = getSession()){
            return session.createQuery("FROM UserModel", UserModel.class).list();
        }
    }

    @Override
    public void save(UserModel user){
        logger.debug("Saving user: {}", user);
        super.save(user);
    }

    @Override
    public void delete(UserModel user){
        logger.debug("Deleting user: {}", user);
        super.delete(user);
    }
}
