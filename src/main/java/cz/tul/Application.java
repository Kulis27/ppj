package cz.tul;

import cz.tul.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TagRepository tagRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Author filip = new Author("Filip", new Date());
        authorRepository.save(filip);

        Image chelsea = new Image(filip, new URI("https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/768px-Chelsea_FC.svg.png"), "Chelsea", new Date());
        imageRepository.save(chelsea);
        Image arsenal = new Image(filip, new URI("https://s-media-cache-ak0.pinimg.com/736x/b3/62/68/b362686170e30ca7ee9a0f9ebe4a2d1d.jpg"), "Arsenal", new Date());
        imageRepository.save(arsenal);

        Comment comment = new Comment(filip, chelsea, "Tato sezona se moc nepovedla.", new Date());
        commentRepository.save(comment);

        Comment comment2 = new Comment(filip, chelsea, "To tedy opravdu ne.", new Date());
        commentRepository.save(comment2);

        Comment comment3 = new Comment(filip, arsenal, "Bla bla bla...", new Date());
        commentRepository.save(comment3);

        Tag fotbal = new Tag("fotbal");
        tagRepository.save(fotbal);

        Tag sport = new Tag("sport");
        tagRepository.save(sport);

        Set<Tag> tags = chelsea.getTags();
        tags.add(fotbal);
        tags.add(sport);
        imageRepository.save(chelsea);
    }

}
