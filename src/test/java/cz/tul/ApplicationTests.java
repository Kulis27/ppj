package cz.tul;

import cz.tul.domain.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@ActiveProfiles(ApplicationProfile.TEST)
@Transactional
public class ApplicationTests {

    private static EmbeddedDatabase database;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TagRepository tagRepository;

    @BeforeClass
    public static void setUp() {
        database = new EmbeddedDatabaseBuilder().build();
    }

    @AfterClass
    public static void tearDown() {
        database.shutdown();
    }

    @Test
    public void findImagesByName() throws Exception {
        final String imageName = "Arsenal";
        List<Image> images = imageRepository.findByName(imageName);
        assertEquals(1, images.size());
        for (Image image : images) {
            assertEquals("Arsenal", image.getName());
        }
    }

    @Test
    public void findImagesByAuthorName() throws Exception {
        final String authorName = "Fila";
        List<Image> images = imageRepository.findByAuthorName(authorName);
        assertEquals(2, images.size());
        for (Image image : images) {
            assertEquals(authorName, image.getAuthor().getName());
        }
    }

    @Test
    public void findImagesByTagName() throws Exception {
        final String tagName = "fotbal";
        List<Image> images = imageRepository.findByTagName(tagName);
        assertEquals(2, images.size());
        for (Image image : images) {
            boolean tagFound = false;
            for (Tag tag : image.getTags()) {
                tagFound = tagFound || tagName.equals(tag.getName());
            }
            assertTrue(tagFound);
        }
    }

    @Test
    public void crudImage() throws Exception {
        long imageCount = imageRepository.count();

        //create
        final String authorName = "Jan";
        Author author = new Author(authorName, new Date());
        authorRepository.save(author);
        final URI uri = new URI("http://everton.com/logo.png");
        final String name = "Everton";
        final Date creation = new Date();
        Image image = new Image(author, new URI("http://everton.com/logo.png"), "Everton", creation);
        imageRepository.save(image);
        assertEquals(imageCount + 1, imageRepository.count());

        //read
        String imageId = image.getId();
        Image createdImage = imageRepository.findOne(imageId);
        assertEquals(authorName, createdImage.getAuthor().getName());
        assertEquals(uri, createdImage.getUrl());
        assertEquals(name, createdImage.getName());
        assertEquals(new Long(0), createdImage.getLikes());
        assertEquals(new Long(0), createdImage.getDislikes());
        assertEquals(creation, createdImage.getCreation());
        assertEquals(creation, createdImage.getModification());
        assertTrue(createdImage.getComments().isEmpty());
        assertTrue(createdImage.getTags().isEmpty());

        //update
        final String updatedAuthorName = "Dan";
        final URI updatedUri = new URI("http://everton.com/official-logo.png");
        final String updatedName = "Everton FC";
        final Long updatedLikes = new Long(10);
        final Long updatedDislikes = new Long(3);
        final Date updatedModification = new Date();
        Comment comment = new Comment(author, "Komentar...", new Date());
        commentRepository.save(comment);
        Tag tag = new Tag("anglicka liga");
        tagRepository.save(tag);
        image.getAuthor().setName(updatedAuthorName);
        image.setUrl(updatedUri);
        image.setName(updatedName);
        image.setDislikes(updatedDislikes);
        image.setLikes(updatedLikes);
        image.setModification(updatedModification);
        image.getTags().add(tag);
        image.getComments().add(comment);
        imageRepository.save(image);
        Image updatedImage = imageRepository.findOne(imageId);
        assertEquals(updatedAuthorName, updatedImage.getAuthor().getName());
        assertEquals(updatedUri, updatedImage.getUrl());
        assertEquals(updatedName, updatedImage.getName());
        assertEquals(updatedLikes, updatedImage.getLikes());
        assertEquals(updatedDislikes, updatedImage.getDislikes());
        assertEquals(updatedModification, updatedImage.getModification());
        assertEquals(1, updatedImage.getComments().size());
        assertEquals(1, updatedImage.getTags().size());

        //delete
        imageRepository.delete(image);
        assertEquals(imageCount, imageRepository.count());
        assertNull(imageRepository.findOne(imageId));
    }

    @Test
    public void crudComment() {
        long commentCount = commentRepository.count();

        //create
        final String authorName = "Jan";
        Author author = new Author(authorName, new Date());
        authorRepository.save(author);
        final String text = "Komentar...";
        final Date creation = new Date();
        Comment comment = new Comment(author, text, creation);
        commentRepository.save(comment);
        assertEquals(commentCount + 1, commentRepository.count());

        //read
        String commentId = comment.getId();
        Comment createdComment = commentRepository.findOne(commentId);
        assertEquals(authorName, comment.getAuthor().getName());
        assertEquals(text, comment.getText());
        assertEquals(new Long(0), comment.getLikes());
        assertEquals(new Long(0), comment.getDislikes());
        assertEquals(creation, comment.getCreation());
        assertEquals(creation, comment.getModification());

        //update
        final String updatedAuthorName = "Dan";
        final String updatedText = "Jiny komentar...";
        final Long updatedLikes = new Long(10);
        final Long updatedDislikes = new Long(3);
        final Date updatedModification = new Date();
        comment.getAuthor().setName(updatedAuthorName);
        comment.setText(updatedText);
        comment.setLikes(updatedLikes);
        comment.setDislikes(updatedDislikes);
        comment.setModification(updatedModification);
        commentRepository.save(comment);
        Comment updatedComment = commentRepository.findOne(commentId);
        assertEquals(updatedAuthorName, updatedComment.getAuthor().getName());
        assertEquals(updatedText, updatedComment.getText());
        assertEquals(updatedLikes, updatedComment.getLikes());
        assertEquals(updatedDislikes, updatedComment.getDislikes());
        assertEquals(updatedModification, updatedComment.getModification());

        //delete
        commentRepository.delete(comment);
        assertEquals(commentCount, commentRepository.count());
        assertNull(commentRepository.findOne(commentId));
    }

    @Test
    public void crudAuthor() {
        long authorCount = authorRepository.count();

        //create
        final String name = "Jan";
        final Date registration = new Date();
        Author author = new Author(name, registration);
        authorRepository.save(author);
        assertEquals(authorCount + 1, authorRepository.count());

        //read
        String authorId = author.getId();
        Author createdAuthor = authorRepository.findOne(authorId);
        assertEquals(name, createdAuthor.getName());
        assertEquals(registration, createdAuthor.getRegistration());

        //update
        final String updatedName = "Dan";
        final Date updatedRegistration = new Date();
        author.setName(updatedName);
        author.setRegistration(updatedRegistration);
        authorRepository.save(author);
        Author updatedAuthor = authorRepository.findOne(authorId);
        assertEquals(updatedName, updatedAuthor.getName());
        assertEquals(updatedRegistration, updatedAuthor.getRegistration());

        //delete
        authorRepository.delete(author);
        assertEquals(authorCount, authorRepository.count());
        assertNull(authorRepository.findOne(authorId));
    }

    @Test
    public void crudTag() {
        long tagCount = tagRepository.count();

        //create
        final String name = "anglicka liga";
        Tag tag = new Tag(name);
        tagRepository.save(tag);
        assertEquals(tagCount + 1, tagRepository.count());

        //read
        String tagId = tag.getId();
        Tag createdTag = tagRepository.findOne(tagId);
        assertEquals(name, createdTag.getName());

        //update
        final String updatedName = "premier league";
        tag.setName(updatedName);
        tagRepository.save(tag);
        Tag updatedTag = tagRepository.findOne(tagId);
        assertEquals(updatedName, updatedTag.getName());

        //delete
        tagRepository.delete(tag);
        assertEquals(tagCount, tagRepository.count());
        assertNull(tagRepository.findOne(tagId));
    }

}
