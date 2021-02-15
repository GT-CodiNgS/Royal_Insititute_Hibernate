package business.custom;

import business.SuperBO;
import dto.CourseDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;

import java.util.List;

public interface CourseBo extends SuperBO{
    public boolean add(CourseDTO dto)throws Exception;

    public boolean delete(String id)throws Exception;

    public boolean update(CourseDTO dto)throws Exception;

    public CourseDTO find(String id)throws Exception;

    public List<CourseDTO> findAll()throws Exception;
}
