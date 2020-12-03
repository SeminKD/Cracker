package project.service;

import project.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudentById(long id);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudentById(long id);
}
