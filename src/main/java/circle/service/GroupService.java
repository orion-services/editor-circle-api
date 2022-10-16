package circle.service;

import circle.model.Group;
import circle.repository.GroupRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.FormParam;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group groupToCreate(@FormParam("groupName") final String groupName, @FormParam("hashUser") final String hashUser) {
        final Group group = new Group();
            group.setHashUser(hashUser);
            group.setGroupName(groupName);
            groupRepository.save(group);
        return group;
    }

}
