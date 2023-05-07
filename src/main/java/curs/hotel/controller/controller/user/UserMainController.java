package curs.hotel.controller.controller.user;

import curs.hotel.dao.RoomRepository;
import curs.hotel.model.pojo.Room;
import curs.hotel.service.security.HotelUserDetails;
import curs.hotel.service.util.directions.Pages;
import curs.hotel.service.util.directions.Pathes;
import curs.hotel.service.view.admin.RoomsTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Controller
public class UserMainController {
    private RoomsTableService service;
    private final RoomRepository roomRepository;
    @Autowired
    public UserMainController(RoomsTableService service, RoomRepository roomRepository) {
        this.service = service;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/user/")
    public String getMain(Model model, Authentication authentication) {
        log.info("Accessing GET main controller");
        if (authentication != null) {
            HotelUserDetails userDetails = (HotelUserDetails)authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
        }
        return Pathes.USER_MAIN.getCropPagePath();
    }

    @GetMapping("/user/rooms")
    public String getRooms(Model model,
                           @PageableDefault( sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {

        service.paginate(model, pageable);
        return Pages.USER_ROOMS_PAGE.getCropPath();
    }

    @GetMapping("/user/rooms/room/{id}")
    public String phoneEdit(Model model, @PathVariable(value = "id") long id) {
        if(!roomRepository.existsById(id))
        {
            return "redirect:/user/rooms";
        }
        Optional<Room> room=Optional.ofNullable(roomRepository.findById(id).orElseThrow());
        ArrayList<Room> res=new ArrayList<>();
        room.ifPresent(res::add);
        model.addAttribute("room", res);

        return Pages.USER_ROOM.getCropPath();
    }

}
