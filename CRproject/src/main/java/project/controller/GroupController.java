package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.model.DBControl;
import project.model.Group;
import project.model.NewStudent;
import project.model.Student;
import project.repository.GroupRepository;
import project.repository.StudentRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {

    private DBControl db = new DBControl();
    private Statement statement;
    {
        try {
            statement = db.getConnection().createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    StudentRepository studentRepository;


    @GetMapping("/groups")
    public String showGroups(Model model){
        List<Group> list = groupRepository.findAll();
        if(!list.isEmpty()){
            model.addAttribute("groups",list);
        }
        return "groups";
    }

    @GetMapping("/group-delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id){
        String query1 = "select stud_id from students where group_id="+id;
        ArrayList<Long> list = new ArrayList<>();
        try{
            ResultSet resultSet = statement.executeQuery(query1);
            while(resultSet.next()){
                list.add(resultSet.getLong(1));
            }
            for (Long l:list){
                studentRepository.deleteById(l);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            groupRepository.deleteById(id);
        }
        return "redirect:/groups";
    }

    @GetMapping("/group-edit/{id}")
    public String editGroup(@PathVariable("id") Long id, Model model){
        model.addAttribute("ID",id);
        Group gr = groupRepository.getOne(id);
        model.addAttribute("group", gr);
        return "group-edit";
    }

    @PostMapping("/group-edit")
    public String updateStudent(@RequestParam(value = "faculty") String faculty,
                                @RequestParam(value = "id") Long id){
        Group group = groupRepository.getOne(id);
        group.setFaculty(faculty);
        groupRepository.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/add-group")
    public String adding(){
        return "add-group";
    }

    @PostMapping("/add-group")
    public String addGroup(@RequestParam(value="faculty") String faculty){
        Group group = new Group();
        group.setFaculty(faculty);
        groupRepository.save(group);
        return "redirect:/groups";
    }
}
