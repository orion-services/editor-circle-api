package circle.controller;

import circle.model.Group;
import circle.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Group> groupToCreate(@RequestParam @Valid String groupName, @RequestParam @Valid String hashUser){
        return new ResponseEntity<>(this.groupService.groupToCreate(groupName, hashUser), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Group> groupToFindByName(@RequestParam @Valid String groupName){
        return new ResponseEntity<>(this.groupService.groupToFindByName(groupName), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Group> groupToJoin(@RequestParam @Valid String groupName, @RequestParam @Valid String hashUser){
        return new ResponseEntity<>(this.groupService.groupToJoin(groupName, hashUser), HttpStatus.OK);
    }
}
