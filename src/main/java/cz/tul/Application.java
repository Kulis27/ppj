package cz.tul;

import cz.tul.domain.*;
import cz.tul.messaging.ImageMDB;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URI;
import java.util.Date;
import java.util.Set;

@EnableRabbit
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private TagRepository tagRepository;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(ImageMDB.exchange);
    }

    @Bean
    Queue createQueue() {
        return new Queue(ImageMDB.createQueue, false);
    }

    @Bean
    Binding createBinding() {
        return BindingBuilder.bind(createQueue()).to(exchange()).with(ImageMDB.createQueue);
    }

    @Bean
    Queue likeQueue() {
        return new Queue(ImageMDB.likeQueue, false);
    }

    @Bean
    Binding likeBinding() {
        return BindingBuilder.bind(likeQueue()).to(exchange()).with(ImageMDB.likeQueue);
    }

    @Bean
    Queue dislikeQueue() {
        return new Queue(ImageMDB.dislikeQueue, false);
    }

    @Bean
    Binding dislikeBinding() {
        return BindingBuilder.bind(dislikeQueue()).to(exchange()).with(ImageMDB.dislikeQueue);
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Author filip = new Author("Filip", new Date());
        authorRepository.save(filip);

        Author matej = new Author("Matěj", new Date());
        authorRepository.save(matej);

        Image chelsea = new Image(filip, new URI("https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/768px-Chelsea_FC.svg.png"), "Chelsea", new Date());
        imageRepository.save(chelsea);
        Image arsenal = new Image(filip, new URI("https://s-media-cache-ak0.pinimg.com/736x/b3/62/68/b362686170e30ca7ee9a0f9ebe4a2d1d.jpg"), "Arsenal", new Date());
        imageRepository.save(arsenal);

        Comment comment = new Comment(filip, chelsea, "Tato sezona se moc nepovedla.", new Date());
        commentRepository.save(comment);

        Comment comment2 = new Comment(matej, chelsea, "To tedy opravdu ne.", new Date());
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

        Image manchester = new Image(matej, new URI("https://storage.designcrowd.com/design_img/138412/123024/123024_1860727_138412_image.png"), "Manchester", new Date());
        rabbitTemplate.convertAndSend(ImageMDB.exchange, ImageMDB.createQueue, manchester);

        rabbitTemplate.convertAndSend(ImageMDB.exchange, ImageMDB.likeQueue, chelsea.getId());

        rabbitTemplate.convertAndSend(ImageMDB.exchange, ImageMDB.dislikeQueue, arsenal.getId());
    }

}
