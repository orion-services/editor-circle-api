package circle.service;

import circle.model.Activity;
import circle.model.Group;
import circle.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.FormParam;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final GroupService groupService;
    private final UserService userService;

    public ActivityService(ActivityRepository activityRepository, GroupService groupService, UserService userService) {
        this.activityRepository = activityRepository;
        this.groupService = groupService;
        this.userService = userService;
    }

    public Activity activityToCreate(@FormParam("groupName") final String groupName) {
        final Activity createActivity = new Activity();
        createActivity.setGroup(groupService.groupToFindByName(groupName));
        activityRepository.save(createActivity);
        return createActivity;
    }

    public Activity activityToFindByName(@FormParam("groupName") final String groupName) {
        final Activity findActivity = new Activity();
        findActivity.setGroup(groupService.groupToFindByName(groupName));
        return findActivity;
    }

    public Activity activityToAddUser(@FormParam("groupName") final String groupName, @FormParam("hashUser") final String hashUser) {
        final Activity addActivity = activityToFindByName(groupName);
        addActivity.setGroup(groupService.groupToFindByName(groupName));
        addActivity.setUser(userService.userToFindByHash(hashUser));
        activityRepository.save(addActivity);
        return addActivity;
    }

    public Activity activityToParticipate(@FormParam("hashUser") final String hashUser, @FormParam("groupName") final String groupName) {
        return activityToAddUser(groupName, hashUser);
    }
}
