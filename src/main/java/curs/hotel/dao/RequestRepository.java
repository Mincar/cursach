package curs.hotel.dao;

import curs.hotel.model.pojo.Request;
import curs.hotel.model.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {
    Page<Request> findAllByUser(User user, Pageable pageable);
}
