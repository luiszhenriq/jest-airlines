package br.com.luis.jest_airlines.controller;

import br.com.luis.jest_airlines.dto.user.UserRegisterDTO;
import br.com.luis.jest_airlines.dto.user.UserUpdateDTO;
import br.com.luis.jest_airlines.model.User;
import br.com.luis.jest_airlines.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    public static final UUID ID =               UUID.randomUUID();
    public static final String FULL_NAME =      "Luis Henrique";
    public static final String EMAIL =          "luishenrique@gmail.com";
    public static final String PASSWORD =       "123456";
    public static final LocalDate BIRTH =        LocalDate.of(2004, 6, 16);
    public static final String CPF =            "123243535";
    public static final String PHONE_NUMBER =   "71941413132";

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    private User user;

    private UserRegisterDTO userDTO;

    private UserUpdateDTO userUpdateDTO;

    @BeforeEach
    void startUser() {
        user = new User(ID, FULL_NAME, EMAIL, PASSWORD, BIRTH, CPF, PHONE_NUMBER);
        userDTO = new UserRegisterDTO(FULL_NAME, EMAIL, PASSWORD, BIRTH, CPF, PHONE_NUMBER);
        userUpdateDTO = new UserUpdateDTO(FULL_NAME, EMAIL, PASSWORD, PHONE_NUMBER);
    }

}