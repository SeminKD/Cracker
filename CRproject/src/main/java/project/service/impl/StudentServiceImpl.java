package project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.model.Student;
import project.repository.StudentRepository;
import project.service.StudentService;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if (student == null) throw new IllegalArgumentException();
        student.setEnroll_date(new Date());
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void updateStudent(Student student) {
        if (student == null) throw new IllegalArgumentException();
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }
}
