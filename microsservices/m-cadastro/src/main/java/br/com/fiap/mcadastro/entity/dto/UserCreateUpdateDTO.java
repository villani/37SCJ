package br.com.fiap.mcadastro.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserCreateUpdateDTO {

    private String name;
    private Date birthDate;
    private String maritalStatus;
    private String email;
    private String password;
    private Boolean allowNotifications;

}
