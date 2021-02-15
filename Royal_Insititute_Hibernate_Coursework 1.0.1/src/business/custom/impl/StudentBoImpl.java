package business.custom.impl;


import business.custom.StudentBo;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentBoImpl implements StudentBo {
    StudentDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STUDENT);

    @Override
    public boolean add(StudentDTO dto) throws Exception {
        return dao.add(new Student(dto.getId(),dto.getName(),dto.getAddress(),dto.getContact(),dto.getDobDate(),dto.getGender(),null));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return  dao.delete(id);
    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {

        return dao.update(new Student(dto.getId()
                ,dto.getName(),dto.getAddress()
                ,dto.getContact(),dto.getDobDate()
                ,dto.getGender(),null));
    }

    @Override
    public StudentDTO find(String id) throws Exception {
        Student student = dao.find(id);
        return new StudentDTO(student.getId()
                ,student.getName()
                ,student.getAddress()
                ,student.getContact()
                ,student.getDobDate()
                ,student.getGender()
        );
    }
    @Override
    public List<StudentDTO> findAll() throws Exception {
        List<Student> list = dao.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student student : list) {
            studentDTOList.add(new StudentDTO(student.getId()
                    ,student.getName()
                    ,student.getAddress()
                    ,student.getContact()
                    ,student.getDobDate()
                    ,student.getGender()
            ));
        }

        return studentDTOList;
    }



}
