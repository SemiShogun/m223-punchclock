package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Room;
import ch.zli.m223.punchclock.service.RoomService;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final UserService userService;

    public RoomController(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Room> getRoom(@PathVariable long id) {
        return roomService.findRoom(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@Valid @RequestBody Room room, Principal user) {
        try {
            ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
            if (applicationUser.getRole() != "ADMIN") {
                throw new BadRequestException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomService.createEntry(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable long id, Principal user) {
        try {
            ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
            if (applicationUser.getRole() != "ADMIN") {
                throw new BadRequestException();
            }
            roomService.deleteRoom(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Room updateRoom(@PathVariable long id, @Valid @RequestBody Room room, Principal user) {
        try {
            ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
            if (applicationUser.getRole() != "ADMIN") {
                throw new BadRequestException();
            }
            room.setId(id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return roomService.updateRoom(room);
    }

}
