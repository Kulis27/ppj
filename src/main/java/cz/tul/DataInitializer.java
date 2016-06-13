package cz.tul;

import cz.tul.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TagRepository tagRepository;

    public void initData() throws Exception {
        Author filip = new Author("Fila", new Date());
        authorRepository.save(filip);
        Author matej = new Author("Mates", new Date());
        authorRepository.save(matej);

        Comment comment1 = new Comment(filip, "Tato sezona se moc nepovedla.", new Date());
        commentRepository.save(comment1);
        Comment comment2 = new Comment(matej, "To tedy opravdu ne.", new Date());
        commentRepository.save(comment2);
        Comment comment3 = new Comment(filip, "Bla bla bla...", new Date());
        commentRepository.save(comment3);

        Tag fotbal = new Tag("fotbal");
        tagRepository.save(fotbal);
        Tag sport = new Tag("sport");
        tagRepository.save(sport);

        Image chelsea = new Image(filip, new URI("https://upload.wikimedia.org/wikipedia/en/thumb/c/cc/Chelsea_FC.svg/768px-Chelsea_FC.svg.png"), "Chelsea", new Date());
        List<Comment> chelseaComments = chelsea.getComments();
        chelseaComments.add(comment1);
        chelseaComments.add(comment2);
        List<Tag> chelseaTags = chelsea.getTags();
        chelseaTags.add(fotbal);
        chelseaTags.add(sport);
        imageRepository.save(chelsea);

        Image arsenal = new Image(filip, new URI("https://s-media-cache-ak0.pinimg.com/736x/b3/62/68/b362686170e30ca7ee9a0f9ebe4a2d1d.jpg"), "Arsenal", new Date());
        List<Comment> arsenalComments = arsenal.getComments();
        arsenalComments.add(comment3);
        List<Tag> arsenalTags = arsenal.getTags();
        arsenalTags.add(fotbal);
        arsenalTags.add(sport);
        imageRepository.save(arsenal);

        Image manchester = new Image(matej, new URI("https://storage.designcrowd.com/design_img/138412/123024/123024_1860727_138412_image.png"), "Manchester", new Date());
        imageRepository.save(manchester);
    }

}
