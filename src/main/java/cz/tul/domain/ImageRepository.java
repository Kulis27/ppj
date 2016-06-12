package cz.tul.domain;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ImageRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    List<Image> findByName(String name);

    List<Image> findByAuthorName(String name);

    List <Image> findByTagName(String name);

}
