package curs.hotel.controller.controller.admin;

import curs.hotel.dao.RoomRepository;
import curs.hotel.model.pojo.Room;
import curs.hotel.service.util.directions.Pages;
import curs.hotel.service.util.directions.Pathes;
import curs.hotel.service.view.admin.RoomsTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import curs.hotel.model.enums.RoomClass;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Controller
public class AdminRoomsTableController {
    private RoomsTableService service;
    private final RoomRepository roomRepository;
    @Autowired
    public AdminRoomsTableController(RoomsTableService service, RoomRepository roomRepository) {
        this.service = service;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/admin/rooms")
    public String getRooms(Model model,
                           @RequestParam(name = "method", required = false) String method,
                           @RequestParam(name = "id", required = false) String id,
                           @PageableDefault( sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        if (method != null && id != null && method.equals("delete")) {
            service.delete(id);
        }
        service.paginate(model, pageable);
        return Pages.ADMIN_ROOMS_PAGE.getCropPath();
    }

    @PostMapping("/admin/update-rooms")
    public String tablesAddRoom( @RequestParam("picture") String pictureURL,
                                @RequestParam("places") Integer places,
                                @RequestParam("roomClass") RoomClass roomClass,
                                @RequestParam("price") Double price) {
        service.save(pictureURL, places, roomClass, price);
        return "redirect:" + Pathes.ADMIN_ROOMS.getUrl();
    }


    @GetMapping("/admin/room/{id}/edit")
    public String phoneEdit(Model model, @PathVariable(value = "id") long id) {
        if(!roomRepository.existsById(id))
        {
            return "redirect:/admin/rooms";
        }
        Optional<Room> room=Optional.ofNullable(roomRepository.findById(id).orElseThrow());
        ArrayList<Room> res=new ArrayList<>();
        room.ifPresent(res::add);
        model.addAttribute("room", res);

        return Pages.ADMIN_ROOM_EDIT.getCropPath();
    }

    @PostMapping ("/admin/room/{id}/edit")
    public String phoneUpdate( @PathVariable(value = "id") long id,
                               @RequestParam("picture") String pictureURL,
                               @RequestParam("places") Integer places,
                               @RequestParam("roomClass") RoomClass roomClass,
                               @RequestParam("price") Double price
    ) {

        Room room = roomRepository.findById(id).orElseThrow();
        room.setRoomClass(roomClass);
        room.setPrice(price);
        room.setPlaces(places);
        room.setPicURL(pictureURL);

        roomRepository.save(room);
        return "redirect:/admin/rooms";
    }


}
