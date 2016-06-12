package cz.tul.domain;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface AuthorRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
}
