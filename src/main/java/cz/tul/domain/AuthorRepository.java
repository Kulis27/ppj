package cz.tul.domain;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface AuthorRepository extends PagingAndSortingRepository<Author, String> {
}
