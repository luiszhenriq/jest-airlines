package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.user.UserLoginDTO;
import br.com.luis.jest_airlines.dto.user.UserRegisterDTO;
import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.infra.security.TokenJWT;
import br.com.luis.jest_airlines.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @Operation(summary = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Logged in user"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid UserLoginDTO userLogin) {
        String tokenJWT = service.login(userLogin);
        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }

    @Operation(summary = "User register")
    @ApiResponses(value = {
            @ApiResponse(responseCode="201", description = "Registered user"),
            @ApiResponse(responseCode="400", description = "Email already registered"),
    })
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return new ResponseEntity<>(service.register(userRegisterDTO), HttpStatus.CREATED);
    }

}
