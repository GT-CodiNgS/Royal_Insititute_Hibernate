package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Registration implements SuperEntity {
    @Id
    private String regNo;
    private String regDate;
    private String regFee;
    private String Sid;
    private String Cid;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Student student;
    @ManyToOne
    @JoinColumn(referencedColumnName = "code")
    private Course course;

    public Registration() {
    }

    public Registration(String regNo, String regDate, String regFee, String sid, String cid, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        Sid = sid;
        Cid = cid;
        this.student = student;
        this.course = course;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegFee() {
        return regFee;
    }

    public void setRegFee(String regFee) {
        this.regFee = regFee;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
