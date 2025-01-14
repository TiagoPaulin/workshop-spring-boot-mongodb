package com.tiagopaulin.workshopmongo.config;

import com.tiagopaulin.workshopmongo.domain.Post;
import com.tiagopaulin.workshopmongo.domain.User;
import com.tiagopaulin.workshopmongo.dto.AuthorDTO;
import com.tiagopaulin.workshopmongo.dto.CommentDTO;
import com.tiagopaulin.workshopmongo.repositories.PostRepository;
import com.tiagopaulin.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User tiago = new User(null, "Tiago Paulin", "tiago@gmail.com");
        User gabriel = new User(null, "Gabriel Felipe", "gabriel@gmail.com");
        User tatsuo = new User(null, "Brunno Tatsuo", "brunno@gmail.com");
        User sid = new User(null, "Sidnei Henrique", "sid@gmail.com");

        userRepository.saveAll(Arrays.asList(tiago, gabriel, tatsuo, sid));

        Post post1 = new Post(null, sdf.parse("13/01/2025"), "Viagem com os amigos", "Vamos viajar com os amigos semana que vem", new AuthorDTO(tiago));
        Post post2 = new Post(null, sdf.parse("12/01/2025"), "Meu aniversário está chegando", "Faltam apenas 5 dias para meu aniversário", new AuthorDTO(tiago));

        CommentDTO comment1 = new CommentDTO("Boa viagem!", sdf.parse("13/01/2025"), new AuthorDTO(gabriel));
        CommentDTO comment2 = new CommentDTO("Poste as fotos da viagem", sdf.parse("14/01/2025"), new AuthorDTO(tatsuo));
        CommentDTO comment3 = new CommentDTO("Feliz Aniversário", sdf.parse("17/01/2025"), new AuthorDTO(sid));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1,post2));

        tiago.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(tiago);

    }
}
