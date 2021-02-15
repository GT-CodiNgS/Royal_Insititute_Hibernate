package model;

import com.jfoenix.controls.JFXButton;

public class CourseTM {
    private String code;
    private String name;
    private String duration;
    private String fee;


    public CourseTM() {
    }

    public CourseTM(String code
            , String name
            , String duration
            , String fee) {
        this.code = code;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "CourseTM{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee='" + fee + '\'' +
                '}';
    }
}
