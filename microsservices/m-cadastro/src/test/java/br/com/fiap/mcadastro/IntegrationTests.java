package br.com.fiap.mcadastro;

import br.com.fiap.mcadastro.entity.dto.UserCreateUpdateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.event.ExceptionEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:database")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postUser() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(new UserCreateUpdateDTO( "Guilherme",new Date(),"Solteiro","guilherme@gmail.com","teste123",true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(new UserCreateUpdateDTO( "Fulano",new Date(),"Solteiro","fulanogmail.com","teste123",true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getUsers() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(new UserCreateUpdateDTO( "Guilherme",new Date(),"Solteiro","guilherme@gmail.com","teste123",true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());

        mockMvc.perform( MockMvcRequestBuilders
                .get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name").isNotEmpty());
    }

    @Test
    void getUserById() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(new UserCreateUpdateDTO( "Guilherme",new Date(),"Solteiro","guilherme@gmail.com","teste123",true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform( MockMvcRequestBuilders
                .get("/users/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Guilherme"));

    }

    @Test
    void deleteUser() throws Exception{

        mockMvc.perform( MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(new UserCreateUpdateDTO( "Guilherme",new Date(),"Solteiro","guilherme@gmail.com","teste123",true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform( MockMvcRequestBuilders.delete("/users/{id}", "1") )
                .andExpect(status().isOk());

    }

    @Test
    void putUser() throws Exception{

        mockMvc.perform( MockMvcRequestBuilders
                .post("/users")
                .content(asJsonString(new UserCreateUpdateDTO( "Guilherme",new Date(),"Solteiro","guilherme@gmail.com","teste123",true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform( MockMvcRequestBuilders
                .put("/users/{id}", "1")
                .content(asJsonString(new UserCreateUpdateDTO( "Fulano",new Date(),"Casado","fulano@gmail.com","teste123",true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Fulano"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("fulano@gmail.com"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
