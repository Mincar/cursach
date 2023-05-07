package curs.hotel.dao;

import curs.hotel.model.pojo.Room;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
}
