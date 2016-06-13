package cz.tul;

import cz.tul.domain.Image;
import cz.tul.domain.ImageRepository;
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

import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
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
    public void findImagesByName() throws Exception {
        List<Image> images = imageRepository.findByName("Arsenal");
        assertEquals(1, images.size());
    }

    @Test
    public void findImagesByAuthorName() throws Exception {
        List<Image> images = imageRepository.findByAuthorName("Fila");
        assertEquals(2, images.size());
    }

    @Test
    public void findImagesByTagName() throws Exception {
        List<Image> images = imageRepository.findByTagName("fotbal");
        assertEquals(2, images.size());
    }

}
