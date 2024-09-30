package br.com.luis.jest_airlines.service;

import br.com.luis.jest_airlines.dto.user.UserLoginDTO;
import br.com.luis.jest_airlines.dto.user.UserRegisterDTO;
import br.com.luis.jest_airlines.dto.user.UserResponseDTO;
import br.com.luis.jest_airlines.dto.user.UserUpdateDTO;
import br.com.luis.jest_airlines.infra.exception.EmailAlreadyRegisteredException;
import br.com.luis.jest_airlines.infra.exception.IdNotFoundException;
import br.com.luis.jest_airlines.infra.exception.InvalidPasswordException;
import br.com.luis.jest_airlines.infra.security.TokenService;
import br.com.luis.jest_airlines.model.User;
import br.com.luis.jest_airlines.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRegisterDTO userRegister) {

        if (this.repository.findByEmail(userRegister.email()) != null) {
            throw new EmailAlreadyRegisteredException("Este email já está cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegister.password());

        User newUser = new User(userRegister);

        newUser.setPassword(encryptedPassword);

        User savedUser = repository.save(newUser);

        return userResponseDTO(savedUser);
    }

    public String login(UserLoginDTO userLogin) {

        User user = (User) repository.findByEmail(userLogin.email());

        if (!this.passwordEncoder.matches(userLogin.password(), user.getPassword())) {
            throw new InvalidPasswordException("Senha inválida");
        }

        var token = new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password());
        var auth = manager.authenticate(token);

        return tokenService.generateToken((User) auth.getPrincipal());
    }

    public UserResponseDTO findById(UUID id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));

        return userResponseDTO(user);
    }

    public UserResponseDTO perfil() {

        User user = repository.findUserByEmail(getAuthenticatedUser());

        return userResponseDTO(user);
    }

    public UserResponseDTO update(UUID id, UserUpdateDTO userUpdate) {

        User user = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));

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
                user.getDateOfBirth(),
                user.getCpf(),
                user.getPhoneNumber()
        );
    }

    private String getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

}
