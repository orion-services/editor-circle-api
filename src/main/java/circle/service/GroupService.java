package circle.service;

import circle.model.Group;
import circle.repository.GroupRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.FormParam;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public Group groupToCreate(@FormParam("groupName") final String groupName, @FormParam("hashUser") final String hashUser) {
        final Group group = new Group();
        group.setUsers(List.of(userService.userToFindByHash(hashUser)));
        group.setGroupName(groupName);
        groupRepository.save(group);
        return group;
    }

}
