package com.ultramotor.dao;

import com.ultramotor.entity.User;
import com.ultramotor.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

public class UserDAO implements UltraDAO<User, String> {

    String SELECT_BY_ID = "select * from user where id = ?";

    @Override
    public User selectByID(String id) {
        User user = null;
        try (ResultSet rs = XJdbc.query(SELECT_BY_ID, id)) {
            user = new User(rs.getString(1), rs.getString(2));
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public List<User> selectBySQL() {
        return null;
    }

    @Override
    public CachedRowSet getRowSet() {
        return null;
    }

    @Override
    public void insert(User e) {
    }

    @Override
    public void update(User e) {
    }

    @Override
    public void delete(String id) {
    }
}
