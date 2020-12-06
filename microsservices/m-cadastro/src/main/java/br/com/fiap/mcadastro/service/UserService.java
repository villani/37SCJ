package br.com.fiap.mcadastro.service;

import br.com.fiap.mcadastro.entity.User;
import br.com.fiap.mcadastro.entity.dto.UserCreateUpdateDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    User save(UserCreateUpdateDTO userCreateUpdateDTO);
    User update(Long id, UserCreateUpdateDTO userCreateUpdateDTO);
    List<User> getAll();
    User getById(Long id);
    HttpStatus delete(Long id);
}
