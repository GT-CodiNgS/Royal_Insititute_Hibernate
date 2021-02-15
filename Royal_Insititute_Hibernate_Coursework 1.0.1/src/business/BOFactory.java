package business;

import business.custom.CourseBo;
import business.custom.impl.CourseBoImpl;
import business.custom.impl.RegistratonBoImpl;
import business.custom.impl.StudentBoImpl;
import dao.custom.impl.CourseDAOImpl;
import dao.custom.impl.RegistrationDAOImpl;


public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){ }

    public enum BOType {
        STUDENT,COURSE,REGISTRATION;
    }

    public static BOFactory getInstance(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case STUDENT:
                return (T) new StudentBoImpl();
            case COURSE:
                return (T) new CourseBoImpl();
            case REGISTRATION:
                return (T) new RegistratonBoImpl();
            default:
                return null;
        }
    }
}
