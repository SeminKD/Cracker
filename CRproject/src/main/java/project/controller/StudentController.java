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
public class StudentController {


    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository groupRepository;

/*    @GetMapping("/students")
    public String showStudents(Model model){
        List<Student> list = studentRepository.findAll();
        ArrayList<NewStudent> students = new ArrayList<>();
        for (Student student:list) {
            NewStudent f = new NewStudent(student.getStud_id(),
                    student.getGroupNumber(),
                    student.getEnroll_date());
            students.add(f);
        }
        if(!students.isEmpty()){
            model.addAttribute("students", students);
        }
        return "students";
    }*/


    @GetMapping("/add-student")
    public String adding(Model model){
        List<Group> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam(value="group_id") Group group_id,
                             @RequestParam(value="stud_name") String stud_name,
                             @RequestParam(value="enroll_date") String enroll_date
                             ){
        Student student = new Student();
        student.setStud_name(stud_name);
        student.setEnroll_date(enroll_date);
        student.setGroup_id(group_id);
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/student-delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String showStudents(Model model){
        List<NewStudent> students = getNewStudents();
        if(!students.isEmpty()){
            model.addAttribute("students", students);
        }
        return "students";
    }

    private List<NewStudent> getNewStudents(){
        DBControl db = new DBControl();
        String query="select s.stud_id, s.stud_name,s.enroll_date, sg.group_id from students s inner join stud_groups sg on s.group_id = sg.group_id";
        List<NewStudent> list = new ArrayList<>();
        try{
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                list.add(new NewStudent(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getLong(4)
                ));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @GetMapping("/student-edit/{id}")
    public String editStudentForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("ID",id);
        Student student = studentRepository.getOne(id);
        model.addAttribute("student", student);
        List<Group> gr = groupRepository.findAll();
        model.addAttribute("groups",gr);
        return "student-edit";
    }

    @PostMapping("/student-edit")
    public String updateStudent(@RequestParam(value = "group_id") Group group_id,
                                @RequestParam(value = "stud_name") String stud_name,
                                @RequestParam(value = "enroll_date") String enroll_date,
                                @RequestParam(value = "id") Long id){
        Student student = studentRepository.getOne(id);
        student.setStud_name(stud_name);
        student.setGroup_id(group_id);
        student.setEnroll_date(enroll_date);
        studentRepository.save(student);
        return "redirect:/students";
    }
}
