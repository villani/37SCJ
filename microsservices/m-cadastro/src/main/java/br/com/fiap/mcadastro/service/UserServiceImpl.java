package br.com.fiap.mcadastro.service;

import br.com.fiap.mcadastro.entity.User;
import br.com.fiap.mcadastro.entity.dto.UserCreateUpdateDTO;
import br.com.fiap.mcadastro.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserCreateUpdateDTO userCreateUpdateDTO) {

        User user = new User();
        user.setName(userCreateUpdateDTO.getName());
        user.setAllowNotifications(userCreateUpdateDTO.getAllowNotifications());
        user.setBirthDate(userCreateUpdateDTO.getBirthDate());
        user.setEmail(userCreateUpdateDTO.getEmail());
        user.setMaritalStatus(userCreateUpdateDTO.getMaritalStatus());
        user.setPassword(userCreateUpdateDTO.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User update(Long id, UserCreateUpdateDTO userCreateUpdateDTO) {

        User userDB = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(userCreateUpdateDTO.getName() != null && !userCreateUpdateDTO.getName().isEmpty()){
            userDB.setName(userCreateUpdateDTO.getName());
        }
        if(userCreateUpdateDTO.getAllowNotifications() != null){
            userDB.setAllowNotifications(userCreateUpdateDTO.getAllowNotifications());
        }
        if(userCreateUpdateDTO.getBirthDate() != null){
            userDB.setBirthDate(userCreateUpdateDTO.getBirthDate());
        }
        if(userCreateUpdateDTO.getEmail() != null && !userCreateUpdateDTO.getEmail().isEmpty()){
            userDB.setEmail(userCreateUpdateDTO.getEmail());
        }
        if(userCreateUpdateDTO.getMaritalStatus() != null && !userCreateUpdateDTO.getMaritalStatus().isEmpty()){
            userDB.setMaritalStatus(userCreateUpdateDTO.getMaritalStatus());
        }
        if(userCreateUpdateDTO.getPassword() != null && !userCreateUpdateDTO.getPassword().isEmpty()){
            userDB.setPassword(userCreateUpdateDTO.getPassword());
        }

        return userRepository.save(userDB);
    }


    @Override
    public List<User> getAll() {
       return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {

        User userDB = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return userDB;
    }

    @Override
    public HttpStatus delete(Long id) {

        User userDB = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.delete(userDB);
        return HttpStatus.ACCEPTED;
    }
}
