package com.tiagopaulin.workshopmongo.resources;

import com.tiagopaulin.workshopmongo.domain.Post;
import com.tiagopaulin.workshopmongo.domain.User;
import com.tiagopaulin.workshopmongo.dto.UserDTO;
import com.tiagopaulin.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {

        List<User> list = service.findAll();
        List<UserDTO> users = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(users);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {

        User user = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(user));

    }

    @PostMapping
    public ResponseEntity<Void> insert (@RequestBody UserDTO userDTO) {

        User user = service.fromDTO(userDTO);
        user = service.insert(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable String id) {

        User user = service.fromDTO(userDTO);
        user.setId(id);
        user = service.update(user);

        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {

        User user = service.findById(id);

        return ResponseEntity.ok().body(user.getPosts());

    }

}
