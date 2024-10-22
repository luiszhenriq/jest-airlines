package br.com.luis.jest_airlines.service;

import br.com.luis.jest_airlines.dto.user.UserRegisterDTO;
import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.infra.exception.IdNotFoundException;
import br.com.luis.jest_airlines.model.User;
import br.com.luis.jest_airlines.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final UUID ID =               UUID.randomUUID();
    public static final String FULL_NAME =      "Luis Henrique";
    public static final String EMAIL =          "luishenrique@gmail.com";
    public static final String PASSWORD =       "123456";
    public static final LocalDate BIRTH =        LocalDate.of(2004, 6, 16);
    public static final String CPF =            "123243535";
    public static final String PHONE_NUMBER =   "71941413132";

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    private User user;

    private UserRegisterDTO userDTO;

    @BeforeEach
    void startUser() {
        user = new User(ID, FULL_NAME, EMAIL, PASSWORD, BIRTH, CPF, PHONE_NUMBER);
        userDTO = new UserRegisterDTO(FULL_NAME, EMAIL, PASSWORD, BIRTH, CPF, PHONE_NUMBER);
    }

    @Test
    @DisplayName("Should Return A User By Id")
    void shouldReturnAUserById() {
        when(repository.findById(ID)).thenReturn(Optional.of(user));

        UserResponseDTO response = service.findById(ID);

        assertNotNull(response);
        assertEquals(UserResponseDTO.class, response.getClass());
        assertEquals(ID, response.id());
    }

    @Test
    @DisplayName("Should return an id not found exception")
    void shouldReturnAnIdNotFoundException() {
        when(repository.findById(ID)).thenThrow(new IdNotFoundException("Usuário não encontrado"));

        try {
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(IdNotFoundException.class, ex.getClass());
            assertEquals("Usuário não encontrado", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Should register a user success")
    void shouldRegisterAUserSuccess() {
        when(repository.save(any())).thenReturn(user);

        UserResponseDTO response = service.register(userDTO);

        assertNotNull(response);
        assertEquals(UserResponseDTO.class, response.getClass());
        assertEquals(ID, response.id());
    }

}