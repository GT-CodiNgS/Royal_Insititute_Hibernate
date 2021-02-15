package business.custom.impl;

import business.custom.CourseBo;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dto.CourseDTO;
import entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBoImpl implements CourseBo {
    CourseDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.COURSE);

    @Override
    public boolean add(CourseDTO dto) throws Exception {
        return dao.add(new Course(dto.getCode()
                ,dto.getName()
                ,dto.getDuration()
                ,dto.getFee()
                ,null));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public boolean update(CourseDTO dto) throws Exception {
        return dao.update(new Course(dto.getCode()
                ,dto.getName()
                ,dto.getDuration()
                ,dto.getFee()
                ,null));
    }

    @Override
    public CourseDTO find(String id) throws Exception {
        Course course=dao.find(id);

        return new CourseDTO(course.getCode()
                ,course.getName()
                ,course.getDuration()
                ,course.getFee()
        );
    }

    @Override
    public List<CourseDTO> findAll() throws Exception {
        List<Course> list = dao.findAll();
        List<CourseDTO> courseDTOList = new ArrayList<>();

        for (Course course : list) {
            courseDTOList.add(new CourseDTO(course.getCode()
                    ,course.getName()
                    ,course.getDuration()
                    ,course.getFee()

            ));
        }

        return courseDTOList;
    }
}
