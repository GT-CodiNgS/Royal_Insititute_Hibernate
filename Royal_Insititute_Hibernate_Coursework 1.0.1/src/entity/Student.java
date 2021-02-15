package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String address;
    private String contact;
    private String dobDate;
    private String gender;
    @OneToMany(mappedBy = "student")
    private List<Registration> registrations;

    public Student() {
    }

    public Student(String id, String name, String address, String contact, String dobDate, String gender, List<Registration> registrations) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.dobDate = dobDate;
        this.gender = gender;
        this.registrations = registrations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDobDate() {
        return dobDate;
    }

    public void setDobDate(String dobDate) {
        this.dobDate = dobDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
