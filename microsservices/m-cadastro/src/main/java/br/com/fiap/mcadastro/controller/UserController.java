package br.com.fiap.mcadastro.controller;

import br.com.fiap.mcadastro.entity.dto.UserCreateUpdateDTO;
import br.com.fiap.mcadastro.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createuser(@RequestBody UserCreateUpdateDTO userCreateUpdateDTO){

        return ResponseEntity.ok().body(userService.save(userCreateUpdateDTO));
    }

    @GetMapping()
    public ResponseEntity<?> findAll(){
        return  ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.delete(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserCreateUpdateDTO userCreateUpdateDTO){
        return  ResponseEntity.ok().body(userService.update(id,userCreateUpdateDTO));
    }

}
