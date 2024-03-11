package com.example.askproject.Service;
import java.util.List;
import com.example.askproject.Model.DTO.UserDTO;

public interface UserService {
    public void registUser(UserDTO userDTO);
    public void deleteUser(String userId);
    public void updateUser(UserDTO userDTO);
    public UserDTO findByUserId(String userId);
    public List<String> findAllUserId();
}
