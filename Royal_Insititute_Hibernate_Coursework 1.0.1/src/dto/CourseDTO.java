package dto;

public class CourseDTO implements SuperDTO{
    private String code;
    private String name;
    private String duration;
    private String fee;
    public CourseDTO() {
    }

    public CourseDTO(String code, String name, String duration, String fee) {
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

    @Override
    public String toString() {
        return "CourseDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee='" + fee + '\'' +
                '}';
    }
}
