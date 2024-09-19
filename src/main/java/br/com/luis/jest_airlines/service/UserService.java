package br.com.luis.jest_airlines.service;

import br.com.luis.jest_airlines.dto.UserRequestDTO;
import br.com.luis.jest_airlines.dto.UserResponseDTO;
import br.com.luis.jest_airlines.dto.UserUpdateDTO;
import br.com.luis.jest_airlines.model.User;
import br.com.luis.jest_airlines.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;


    public UserResponseDTO register(UserRequestDTO userRequest) {

        User newUser = new User(userRequest);

        User savedUser = repository.save(newUser);

        return userResponseDTO(savedUser);
    }

    public UserResponseDTO findById(UUID id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id não encontrado"));

        return userResponseDTO(user);
    }

    public UserResponseDTO update(UUID id, UserUpdateDTO userUpdate) {

        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id não encontrado"));

        user.setFullName(userUpdate.fullName());
        user.setEmail(userUpdate.email());
        user.setPassword(userUpdate.password());
        user.setPhoneNumber(userUpdate.phoneNumber());

        User updatedUser = repository.save(user);

        return userResponseDTO(updatedUser);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }


    private UserResponseDTO userResponseDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getDateOfBirth(),
                user.getCpf(),
                user.getPhoneNumber()
        );
    }

}
