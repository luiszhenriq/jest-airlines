package br.com.luis.jest_airlines.controller;

import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.dto.user.UserUpdateDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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

    private UserUpdateDTO userUpdateDTO;

    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void startUser() {
        userResponseDTO = new UserResponseDTO(ID, FULL_NAME, EMAIL, BIRTH, CPF, PHONE_NUMBER);
        userUpdateDTO = new UserUpdateDTO(FULL_NAME, EMAIL, PASSWORD, PHONE_NUMBER);
    }

    @Test
    @DisplayName("Should return a user by id with success")
    void shouldReturnAUserByIdWithSuccess() {
        when(service.findById(ID)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserResponseDTO.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    @DisplayName("Should register a user with success")
    void shouldUpdateAUserWithSuccess() {
        when(service.update(ID, userUpdateDTO)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = controller.update(ID, userUpdateDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserResponseDTO.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @DisplayName("Should delete a user with sucess")
    void shouldDeleteAUserWithSuccess(){
        doNothing().when(service).deleteById(ID);

        ResponseEntity<Void> response = controller.delete(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).deleteById(ID);
    }

}