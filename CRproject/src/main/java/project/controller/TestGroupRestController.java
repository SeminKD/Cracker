package project.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.model.Group;
import project.service.GroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TestGroupRestController {

    private final GroupService groupService;

    @GetMapping("/getGroups")
    public List<Group> getGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/getGroup({id})")
    public Group getGroup(@PathVariable String id) {
        return groupService.getGroupById(Long.parseLong(id));
    }

    @PutMapping("/addGroup")
    public Map<String, Object> addNewGroup(@RequestBody Group group) {
        Map<String, Object> res = new HashMap<>();
        Group added = groupService.addGroup(group);
        if(added != null) {
            res.put("message", "Adding new group success!");
            res.put("added_group", added);
        } else {
            res.put("message", "New group is not added");
        }
        return res;
    }

    @PatchMapping("/updateGroup")
    public void updateGroup(@RequestBody Group group) {
        groupService.updateGroup(group);
    }

    @DeleteMapping("/deleteGroup({id})")
    public void deleteGroup(@PathVariable("id") String id) {
        groupService.deleteGroupById(Long.parseLong(id));
    }
}
