package br.com.luis.jest_airlines.controller;

import br.com.luis.jest_airlines.dto.user.UserLoginDTO;
import br.com.luis.jest_airlines.dto.user.UserRegisterDTO;
import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.infra.security.TokenJWT;
import br.com.luis.jest_airlines.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    public static final UUID ID =               UUID.randomUUID();
    public static final String FULL_NAME =      "Luis Henrique";
    public static final String EMAIL =          "luishenrique@gmail.com";
    public static final String PASSWORD =       "123456";
    public static final LocalDate BIRTH =        LocalDate.of(2004, 6, 16);
    public static final String CPF =            "123243535";
    public static final String PHONE_NUMBER =   "71941413132";

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService service;

    private UserRegisterDTO userRegisterDTO;

    private UserLoginDTO userLoginDTO;

    private UserResponseDTO userResponseDTO;


    @BeforeEach
    void setUp() {
        userResponseDTO = new UserResponseDTO(ID, FULL_NAME, EMAIL, BIRTH, CPF, PHONE_NUMBER);
        userRegisterDTO = new UserRegisterDTO(FULL_NAME, EMAIL, PASSWORD, BIRTH, CPF, PHONE_NUMBER);
        userLoginDTO = new UserLoginDTO(EMAIL, PASSWORD);
    }

    @Test
    @DisplayName("Should return a token when login")
    void shouldReturnATokenWhenLogin() {
        String expectedToken = "mockedTokenJWT";
        when(service.login(userLoginDTO)).thenReturn(expectedToken);

        ResponseEntity<TokenJWT> response = authController.login(userLoginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedToken, response.getBody().tokenJWT());
    }

    @Test
    @DisplayName("Should register a user with success")
    void shouldRegisterAUserWithSuccess() {
        when(service.register(any())).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = authController.register(userRegisterDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }
}