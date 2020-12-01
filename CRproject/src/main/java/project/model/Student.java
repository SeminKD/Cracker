package project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "stud_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stud_id;

    @Column(name = "enroll_date")
    private Date enroll_date;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group_id;

    public Student() {
    }

    public Long getStud_id() {
        return stud_id;
    }

    public void setStud_id(Long stud_id) {
        this.stud_id = stud_id;
    }

    public Date getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(Date enroll_date) {
        this.enroll_date = enroll_date;
    }

    public Group getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Group group_id) {
        this.group_id = group_id;
    }
}
