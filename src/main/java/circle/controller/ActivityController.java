package circle.controller;

import circle.model.Activity;
import circle.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<Activity> activityToCreate(@RequestParam @Valid String groupName) {
        return new ResponseEntity<>(this.activityService.activityToCreate(groupName), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Activity> activityToFindByName(@RequestParam @Valid String groupName) {
        return new ResponseEntity<>(this.activityService.activityToFindByName(groupName), HttpStatus.OK);
    }
}
