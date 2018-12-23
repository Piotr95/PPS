package szczepaniak.ppss.AuthorizationService.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.ppss.AuthorizationService.model.User;

import java.util.UUID;

@Repository
public interface UserRepository  extends CassandraRepository<User, UUID> {

    boolean existsByUsername(String username);
    User findByUsername(String username);
}
