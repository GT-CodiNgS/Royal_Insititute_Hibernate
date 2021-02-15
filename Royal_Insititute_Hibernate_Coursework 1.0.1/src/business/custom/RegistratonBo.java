package business.custom;

import business.SuperBO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Student;

import java.util.List;

public interface RegistratonBo extends SuperBO{
    public boolean add(RegistrationDTO dto)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(RegistrationDTO dto)throws Exception;

    public RegistrationDTO  find(String id)throws Exception;

    public List<RegistrationDTO> findAll()throws Exception;
}
