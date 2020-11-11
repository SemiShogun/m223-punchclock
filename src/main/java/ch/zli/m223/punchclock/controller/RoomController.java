package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Room;
import ch.zli.m223.punchclock.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.createEntry(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable long id) {
        try {
            roomService.deleteRoom(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Room updateRoom(@PathVariable long id, @Valid @RequestBody Room room) {
        try {
            room.setId(id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return roomService.updateRoom(room);
    }

}
