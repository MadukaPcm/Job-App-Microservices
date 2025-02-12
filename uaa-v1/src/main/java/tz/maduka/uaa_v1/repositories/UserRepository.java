package tz.maduka.uaa_v1.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tz.maduka.uaa_v1.models.User;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
