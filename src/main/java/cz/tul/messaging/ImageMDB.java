package cz.tul.messaging;

import cz.tul.domain.Image;
import cz.tul.service.ImageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageMDB {

    public final static String exchange = "image-exchange";
    public final static String createQueue = "image-create-queue";
    public final static String likeQueue = "image-like-queue";
    public final static String dislikeQueue = "image-dislike-queue";

    @Autowired
    private ImageService service;

    @RabbitListener(queues = createQueue)
    public void create(Image image) {
        service.create(image);
    }

    @RabbitListener(queues = likeQueue)
    public void like(String id) {
        service.like(id);
    }

    @RabbitListener(queues = dislikeQueue)
    public void dislike(String id) {
        service.dislike(id);
    }

}
