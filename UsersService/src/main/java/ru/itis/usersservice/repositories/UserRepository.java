package ru.itis.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.usersservice.models.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Bulat Giniyatullin
 * 14 October 2018
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
