package br.com.fiap.mcadastro.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "usuario")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "data_nascimento")
    private Date birthDate;

    @Column(name = "estado_civil")
    private String maritalStatus;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String password;

    @Column(name = "receber_notificacoes")
    private Boolean allowNotifications;
}
