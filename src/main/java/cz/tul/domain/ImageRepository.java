package cz.tul.domain;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {

    List<Image> findByName(String name);

    List<Image> findByAuthorName(String name);

    List <Image> findByTagName(String name);

}
