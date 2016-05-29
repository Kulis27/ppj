package cz.tul;

import cz.tul.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.util.Date;

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

        Image chelseaLogo = new Image(filip, new URI("https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/768px-Chelsea_FC.svg.png"), "Chelsea Logo", new Date());
        imageRepository.save(chelseaLogo);

        Image arsenalLogo = new Image(filip, new URI("https://s-media-cache-ak0.pinimg.com/736x/b3/62/68/b362686170e30ca7ee9a0f9ebe4a2d1d.jpg"), "Arsenal Logo", new Date());
        imageRepository.save(arsenalLogo);

        Comment chelseaLogoComment = new Comment(filip, chelseaLogo, "Tato sezona se moc nepovedla.", new Date());
        commentRepository.save(chelseaLogoComment);

        Tag fotbal = new Tag("fotbal");
        tagRepository.save(fotbal);

        Tag premierLeague = new Tag("Premier League");
        tagRepository.save(premierLeague);
    }

}
