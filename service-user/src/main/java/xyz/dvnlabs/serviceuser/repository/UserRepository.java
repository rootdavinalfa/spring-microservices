package xyz.dvnlabs.serviceuser.repository;

import org.springframework.stereotype.Repository;
import xyz.dvnlabs.corelibrary.base.BaseRepository;
import xyz.dvnlabs.serviceuser.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
}
