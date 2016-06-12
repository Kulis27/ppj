package cz.tul;

import cz.tul.domain.Image;
import cz.tul.domain.ImageRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.springframework.hateoas.client.Hop.rel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
@ActiveProfiles(ApplicationProfile.TEST)
public class ApplicationTests {

    private static EmbeddedDatabase database;

    @Autowired
    private ImageRepository imageRepository;

    @BeforeClass
    public static void setUp() {
        database = new EmbeddedDatabaseBuilder().build();
    }

    @AfterClass
    public static void tearDown() {
        database.shutdown();
    }

    @Test
    public void findImagesByNameUsingRepository() throws Exception {
        List<Image> images = imageRepository.findByName("Arsenal");
        assertEquals(1, images.size());
    }

    @Test
    public void findImagesByAuthorNameUsingRepository() throws Exception {
        List<Image> images = imageRepository.findByAuthorName("Filip");
        assertEquals(2, images.size());
    }

    @Test
    public void findImagesByTagNameUsingRepository() throws Exception {
        List<Image> images = imageRepository.findByTagName("fotbal");
        assertEquals(1, images.size());
    }

    @Test
    public void findImagesByNameUsingREST() throws Exception {
        String url = "http://localhost:8080/images/search/";
        Traverson traverson = new Traverson(new URI(url), MediaTypes.HAL_JSON);
        Resources<Image> images = traverson
                .follow(rel("findByName").withParameter("name", "Arsenal"))
                .toObject(new ParameterizedTypeReference<Resources<Image>>() {});
        assertEquals(1, images.getContent().size());
    }

    @Test
    public void findImagesByAuthorNameUsingREST() throws Exception {
        String url = "http://localhost:8080/images/search/";
        Traverson traverson = new Traverson(new URI(url), MediaTypes.HAL_JSON);
        Resources<Image> images = traverson
                .follow(rel("findByAuthorName").withParameter("name", "Filip"))
                .toObject(new ParameterizedTypeReference<Resources<Image>>() {});
        assertEquals(2, images.getContent().size());
    }

    @Test
    public void findImagesByTagNameUsingREST() throws Exception {
        String url = "http://localhost:8080/images/search/";
        Traverson traverson = new Traverson(new URI(url), MediaTypes.HAL_JSON);
        Resources<Image> images = traverson
                .follow(rel("findByTagName").withParameter("name", "fotbal"))
                .toObject(new ParameterizedTypeReference<Resources<Image>>() {});
        assertEquals(1, images.getContent().size());
    }

}
