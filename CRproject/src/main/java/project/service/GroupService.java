package project.service;

import project.model.Group;

import java.util.List;

public interface GroupService {
    Group addGroup(Group group);
    Group getGroupById(long id);
    List<Group> getAllGroups();
    void updateGroup(Group group);
    void deleteGroupById(long id);
}
