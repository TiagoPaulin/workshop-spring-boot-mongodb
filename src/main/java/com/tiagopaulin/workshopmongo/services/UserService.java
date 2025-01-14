package com.tiagopaulin.workshopmongo.services;

import com.tiagopaulin.workshopmongo.domain.User;
import com.tiagopaulin.workshopmongo.dto.UserDTO;
import com.tiagopaulin.workshopmongo.repositories.UserRepository;
import com.tiagopaulin.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {

        return repository.findAll();

    }

    public User findById(String id) {

        User user = repository.findById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("Usuário não encontrado");
        }

        return user;

    }

    public User insert(User user) {

        return repository.insert(user);

    }

    public void delete(String id) {

        findById(id);

        repository.deleteById(id);

    }

    public User update (User user) {

        User newUser = repository.findById(user.getId()).orElse(null);
        updateData(newUser, user);

        return repository.save(newUser);

    }

    public void updateData(User newUser, User user) {

        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());

    }

    public User fromDTO(UserDTO userDTO) {

        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());

    }

}
