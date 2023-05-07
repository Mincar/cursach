package curs.hotel.dao;

import curs.hotel.model.pojo.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
    Bill findByRequestId(Long request_id);
    Page<Bill> findAllByRequest_UserId(Long id, Pageable pageable);
}
