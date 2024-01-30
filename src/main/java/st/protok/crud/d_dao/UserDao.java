package st.protok.crud.d_dao;

import st.protok.crud.c_model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user);

    void removeUser(int id);

    void updateUser(User user);
}
