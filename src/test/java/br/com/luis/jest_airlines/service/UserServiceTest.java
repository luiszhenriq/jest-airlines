package br.com.luis.jest_airlines.service;

import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.model.User;
import br.com.luis.jest_airlines.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;


    @Test
    @DisplayName("Should Return A User By Id")
    void shouldReturnAUserById() {
        User newUser = new User(UUID.randomUUID(), "Luis Henrique", "luishenrique@gmail.com",
                "123456", LocalDate.of(2004, 6, 16), "123243535",
                "71941413132");

        Mockito.when(repository.findById(newUser.getId())).thenReturn(Optional.of(newUser));

        UserResponseDTO response = service.findById(newUser.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(UserResponseDTO.class, response.getClass());
        Assertions.assertEquals(newUser.getId(), response.id());
    }

}