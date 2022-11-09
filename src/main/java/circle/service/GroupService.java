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
        final Group createGroup = new Group();
        createGroup.setUsers(List.of(userService.userToFindByHash(hashUser)));
        createGroup.setGroupName(groupName);
        groupRepository.save(createGroup);
        return createGroup;
    }

    public Group groupToFindByName(@FormParam("groupName") final String groupName) {
        final Optional<Group> findGroup = Optional.ofNullable(groupRepository.findByGroupName(groupName));
        if (findGroup.isEmpty()){
            throw new NotFoundException("Group not found");
        }
        return findGroup.get();
    }

    public Group groupToJoin(@FormParam("groupName") final String groupName, @FormParam("hashUser") final String hashUser) {
        final Group joinGroup = groupToFindByName(groupName);
        joinGroup.setUsers(List.of(userService.userToFindByHash(hashUser)));
        groupRepository.save(joinGroup);
        return joinGroup;
    }

}
