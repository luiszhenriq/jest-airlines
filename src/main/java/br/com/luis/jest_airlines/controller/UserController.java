package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.dto.user.UserUpdateDTO;
import br.com.luis.jest_airlines.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description="User listed successfully"),
            @ApiResponse(responseCode="404", description="User not found"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Return the user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description="User profile returned successfully"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> profile() {
        return new ResponseEntity<>(service.perfil(), HttpStatus.OK);
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description="User updated successfully"),
            @ApiResponse(responseCode="400", description="Invalid input data"),
            @ApiResponse(responseCode="404", description="User not found"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") UUID id,
                                                  @RequestBody @Valid UserUpdateDTO userUpdate) {
        return new ResponseEntity<>(service.update(id, userUpdate), HttpStatus.OK);
    }

    @Operation(summary = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description="User deleted successfully"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
