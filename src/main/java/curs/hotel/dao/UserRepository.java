package curs.hotel.dao;

import curs.hotel.model.pojo.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByName(String name);
}
