package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.user.UserRequestDTO;
import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.dto.user.UserUpdateDTO;
import br.com.luis.jest_airlines.service.UserService;

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

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(service.register(userRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/perfil")
    public ResponseEntity<UserResponseDTO> perfil(@RequestParam("email") String email) {
        return new ResponseEntity<>(service.perfil(email), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") UUID id,
                                                  @RequestBody UserUpdateDTO userUpdate) {
        return new ResponseEntity<>(service.update(id, userUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
