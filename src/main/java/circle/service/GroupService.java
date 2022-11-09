package circle.service;

import circle.exception.NotFoundException;
import circle.model.Group;
import circle.repository.GroupRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.FormParam;
import java.util.List;
import java.util.Optional;

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

    public Group groupToFindByName(@FormParam("groupName") final String groupName) {
        final Optional<Group> group = Optional.ofNullable(groupRepository.findByGroupName(groupName));
        if (group.isEmpty()){
            throw new NotFoundException("Group not found");
        }
        return group.get();
    }

    public Group groupToJoin(@FormParam("groupName") final String groupName, @FormParam("hashUser") final String hashUser) {
        final Group group = groupToFindByName(groupName);
        group.setUsers(List.of(userService.userToFindByHash(hashUser)));
        groupRepository.save(group);
        return group;
    }

}
