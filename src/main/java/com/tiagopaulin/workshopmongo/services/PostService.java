package com.tiagopaulin.workshopmongo.services;

import com.tiagopaulin.workshopmongo.domain.Post;
import com.tiagopaulin.workshopmongo.repositories.PostRepository;
import com.tiagopaulin.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {

        Post post = repository.findById(id).orElse(null);

        if (post == null) {
            throw new ObjectNotFoundException("Post nao encontrado");
        }

        return post;

    }

    public List<Post> findByTitle(String text) {

//        return repository.findByTitleContainingIgnoreCase(text);
        return repository.findByTitle(text);

    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {

        maxDate = new Date(maxDate.getTime() + (24 * 60 * 60 * 1000));

        return repository.fullSearch(text, minDate, maxDate);

    }

}
