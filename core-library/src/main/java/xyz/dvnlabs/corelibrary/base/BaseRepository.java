package xyz.dvnlabs.corelibrary.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<E, ID> extends JpaRepository<E, ID>, PagingAndSortingRepository<E, ID> {

}
