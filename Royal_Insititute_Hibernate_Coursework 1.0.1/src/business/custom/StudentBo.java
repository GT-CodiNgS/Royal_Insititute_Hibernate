package business.custom;

import business.SuperBO;
import dto.StudentDTO;
import entity.Student;

import java.util.List;

public interface StudentBo extends SuperBO {
    public boolean add(StudentDTO dto)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(StudentDTO dto)throws Exception;

    public StudentDTO  find(String id)throws Exception;

    public List<StudentDTO> findAll()throws Exception;



}
