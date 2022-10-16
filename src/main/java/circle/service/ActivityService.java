package circle.service;

import circle.model.Activity;
import circle.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.FormParam;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final GroupService groupService;

    public ActivityService(ActivityRepository activityRepository, GroupService groupService) {
        this.activityRepository = activityRepository;
        this.groupService = groupService;
    }

    public Activity activityToCreate(@FormParam("groupName") final String groupName) {
        final Activity activity = new Activity();
        activity.setGroup(groupService.groupToFindByName(groupName));
        activityRepository.save(activity);
        return activity;
    }

    public Activity activityToFindByName(@FormParam("groupName") final String groupName) {
        final Activity activity = new Activity();
        activity.setGroup(groupService.groupToFindByName(groupName));
        return activity;
    }
}
