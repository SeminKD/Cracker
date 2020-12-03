package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
