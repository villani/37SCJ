package br.com.fiap.mcadastro;

import br.com.fiap.mcadastro.entity.User;
import br.com.fiap.mcadastro.entity.dto.UserCreateUpdateDTO;
import br.com.fiap.mcadastro.repository.UserRepository;
import br.com.fiap.mcadastro.service.UserService;
import br.com.fiap.mcadastro.service.UserServiceImpl;
import br.com.fiap.mcadastro.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MCadastroApplicationTests {

	@Test
	public void findAll() {

		UserRepository userRepository = Mockito.mock(UserRepository.class);
		List<User> userList = new ArrayList<>();
		User user1 = new User(1L, "Fulano",new Date(),"Solteiro",
				"fulano@gmail.com","senha123",true);
		User user2 = new User(2L, "Ciclano",new Date(),"Casado",
				"ciclano@gmail.com","senha456",true);
		userList.add(user1);
		userList.add(user2);

		Mockito.when(userRepository.findAll()).thenReturn(userList);

		UserService userService = new UserServiceImpl(userRepository);

		List<User> answer = userService.getAll();
		Assertions.assertEquals(2,userList.size());
		Assertions.assertEquals("Ciclano",userList.get(1).getName());

	}

	@Test
	public void findById(){

		UserRepository userRepository = Mockito.mock(UserRepository.class);
		List<User> userList = new ArrayList<>();
		User user1 = new User(1L, "Fulano",new Date(),"Solteiro",
				"fulano@gmail.com","senha123",true);
		User user2 = new User(2L, "Ciclano",new Date(),"Casado",
				"ciclano@gmail.com","senha456",true);
		userList.add(user1);
		userList.add(user2);

		Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user1));
		UserService userService = new UserServiceImpl(userRepository);
		User busca = userService.getById(1L);

		Assertions.assertEquals("Fulano",busca.getName());

	}

	@Test
	public void save(){
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserCreateUpdateDTO userCreateUpdateDTO = new UserCreateUpdateDTO("Fulano",new Date(),
				"Solteiro","tes@gmail.com","teste123",true);

		UserService userService = new UserServiceImpl(userRepository);
		Mockito.when(userRepository.save(Mockito.any(User.class)))
				.thenAnswer(i -> i.getArguments()[0]);

		User userResponse = userService.save(userCreateUpdateDTO);
		Assertions.assertEquals(userResponse.getName(),userCreateUpdateDTO.getName());
	}

	@Test
	public void validateEmail(){
		String email1 = "fulano@gmail.com";
		String email2 = "fulano_ciclano@hotmail.com";
		String email3 = "ciclano-beltrano@ig.com";
		String email4 = "tste";

		Assertions.assertEquals(true, Util.validate(email1));
		Assertions.assertEquals(true, Util.validate(email2));
		Assertions.assertEquals(true, Util.validate(email3));
		Assertions.assertEquals(false, Util.validate(email4));
	}

}
