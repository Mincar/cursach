package curs.hotel.service.view.user;

import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import curs.hotel.service.security.HotelUserDetails;

public interface UserBillsService {
    void pay(@NonNull String billId);

    void paginate(@NonNull HotelUserDetails principal, @NonNull Model model, @NonNull Pageable pageable);

    void cancel(@NonNull String requestId);
}
