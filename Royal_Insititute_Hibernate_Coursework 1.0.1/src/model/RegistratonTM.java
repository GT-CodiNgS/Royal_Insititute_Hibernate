package model;

public class RegistratonTM {
    private String regNo;
    private String regDate;
    private String regFee;
    private String Sid;
    private String Cid;


    public RegistratonTM() {
    }

    public RegistratonTM(String regNo, String regDate, String regFee, String sid, String cid) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        Sid = sid;
        Cid = cid;
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

    @Override
    public String toString() {
        return "RegistratonTM{" +
                "regNo='" + regNo + '\'' +
                ", regDate='" + regDate + '\'' +
                ", regFee='" + regFee + '\'' +
                ", Sid='" + Sid + '\'' +
                ", Cid='" + Cid + '\'' +
                '}';
    }
}
