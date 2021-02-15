package business.custom.impl;

import business.custom.RegistratonBo;
import dao.DAOFactory;
import dao.custom.RegistrationDAO;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Registration;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistratonBoImpl implements RegistratonBo {
    RegistrationDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REGISTRATION);


    @Override
    public boolean add(RegistrationDTO dto) throws Exception {

        return  dao.add(new Registration(dto.getRegNo()
                ,dto.getRegDate()
                ,dto.getRegFee()
                ,dto.getSid()
                ,dto.getCid()
                ,dto.getStudent()
                ,dto.getCourse()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public boolean update(RegistrationDTO dto) throws Exception {
        return dao.update(new Registration(dto.getRegNo()
                ,dto.getRegDate()
                ,dto.getRegFee()
                ,dto.getSid()
                ,dto.getCid()
                ,dto.getStudent()
                ,dto.getCourse()
        ));
    }

    @Override
    public RegistrationDTO find(String id) throws Exception {
        Registration registration = dao.find(id);


        return new RegistrationDTO(registration.getRegNo()
                ,registration.getRegDate()
                ,registration.getRegFee()
                ,registration.getSid()
                ,registration.getCid()
                ,registration.getStudent()
                ,registration.getCourse()
        );
    }

    @Override
    public List<RegistrationDTO> findAll() throws Exception {
        List<Registration> list = dao.findAll();
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();

        for (Registration registration : list) {
            registrationDTOS.add(new RegistrationDTO(registration.getRegNo()
                    ,registration.getRegDate()
                    ,registration.getRegFee()
                    ,registration.getSid()
                    ,registration.getCid()
                    ,registration.getStudent()
                    ,registration.getCourse()

            ));
        }

        return registrationDTOS;
    }
}
