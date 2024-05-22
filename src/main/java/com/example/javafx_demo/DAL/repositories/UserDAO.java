package com.example.javafx_demo.DAL.repositories;
import com.example.javafx_demo.BL.models.UserModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO  extends BaseDAO<UserModel> {
    public UserModel findByUsername(String username){
        try(Session session = getSession()){
            Query<UserModel> query = session.createQuery("FROM UserModel WHERE username = :username",
                    UserModel.class);
            query.setParameter("username", username);
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
        super.save(user);
    }

    @Override
    public void delete(UserModel user){
        super.delete(user);
    }
}
