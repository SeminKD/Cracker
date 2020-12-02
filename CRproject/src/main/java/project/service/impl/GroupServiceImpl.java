package project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.model.Group;
import project.repository.GroupRepository;
import project.service.GroupService;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public Group addGroup(Group group) {
        if (group == null) throw new IllegalArgumentException();
        Group addedGroup = groupRepository.saveAndFlush(group);
        return addedGroup;
    }

    @Override
    public Group getGroupById(long id) {
        Group resGroup = groupRepository.findById(id).get();
        return resGroup;
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> resList = groupRepository.findAll();
        return resList;
    }

    @Override
    public void updateGroup(Group group) {
        if (group == null) throw new IllegalArgumentException();
        groupRepository.saveAndFlush(group);
    }

    @Override
    public void deleteGroupById(long id) {
        groupRepository.deleteById(id);
    }
}
