package circle.controller;

import circle.model.Group;
import circle.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
