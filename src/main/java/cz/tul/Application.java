package cz.tul;

import cz.tul.messaging.ImageMDB;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "cz.tul.domain.jpa")
@EnableMongoRepositories(basePackages = "cz.tul.domain.mongo")
@EnableRabbit
public class Application implements CommandLineRunner {

    @Autowired
    private DataInitializer dataInitializer;

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
        dataInitializer.initData();
    }

}
