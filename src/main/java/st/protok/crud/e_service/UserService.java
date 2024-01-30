package st.protok.crud.e_service;

import st.protok.crud.c_model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user);

    void removeUser(int id);

    void updateUser(User user);
}
