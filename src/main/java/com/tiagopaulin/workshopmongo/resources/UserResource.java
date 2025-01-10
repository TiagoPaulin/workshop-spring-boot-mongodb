package com.tiagopaulin.workshopmongo.resources;

import com.tiagopaulin.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        User maria = new User("1", "Maria Fernanda", "maria@gmail.com");
        User tiago = new User("2", "Tiago Paulin", "tiago@gmail.com");

        List<User> users = new ArrayList<>();
        users.addAll(Arrays.asList(maria, tiago));

        return ResponseEntity.ok().body(users);

    }

}
