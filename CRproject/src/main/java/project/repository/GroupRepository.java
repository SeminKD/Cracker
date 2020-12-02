package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.Group;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
