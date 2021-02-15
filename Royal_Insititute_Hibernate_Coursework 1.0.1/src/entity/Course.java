package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity {
    @Id
    private String code;
    private String name;
    private String duration;
    private String fee;
    @OneToMany(mappedBy = "course")
    private List<Registration> registrations;


    public Course() {
    }

    public Course(String code, String name, String duration, String fee, List<Registration> registrations) {
        this.code = code;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
        this.registrations = registrations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee='" + fee + '\'' +
                ", registrations=" + registrations +
                '}';
    }
}
