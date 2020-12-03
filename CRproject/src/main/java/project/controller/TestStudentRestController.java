package project.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.model.Student;
import project.service.StudentService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class TestStudentRestController {

    private final StudentService studentService;

    @GetMapping("/getStudents")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudent({id})")
    public Student getStudent(@PathVariable("id") String id) {
        return studentService.getStudentById(Long.parseLong(id));
    }

    @PutMapping("/addStudent")
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PatchMapping("/updateStudent")
    public void updateStudent(@RequestBody Student student) {
        student.setEnroll_date(new Date());
        studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent({id})")
    public void deleteStudent(@PathVariable("id") String id) {
        studentService.deleteStudentById(Long.parseLong(id));
    }
}
