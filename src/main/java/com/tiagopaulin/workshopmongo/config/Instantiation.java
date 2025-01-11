package com.tiagopaulin.workshopmongo.config;

import com.tiagopaulin.workshopmongo.domain.User;
import com.tiagopaulin.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        User tiago = new User(null, "Tiago Paulin", "tiago@gmail.com");
        User gabriel = new User(null, "Gabriel Felipe", "gabriel@gmail.com");
        User tatsuo = new User(null, "Brunno Tatsuo", "brunno@gmail.com");
        User sid = new User(null, "Sidnei Henrique", "sid@gmail.com");

        repository.saveAll(Arrays.asList(tiago, gabriel, tatsuo, sid));

    }
}
