package com.tiagopaulin.workshopmongo.services;

import com.tiagopaulin.workshopmongo.domain.User;
import com.tiagopaulin.workshopmongo.repositories.UserRepository;
import com.tiagopaulin.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}
