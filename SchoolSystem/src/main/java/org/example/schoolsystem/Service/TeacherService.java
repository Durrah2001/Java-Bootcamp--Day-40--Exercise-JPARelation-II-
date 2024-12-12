package org.example.schoolsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolsystem.ApiResponse.ApiException;
import org.example.schoolsystem.Model.Address;
import org.example.schoolsystem.Model.Teacher;
import org.example.schoolsystem.Repository.AddressRepository;
import org.example.schoolsystem.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final AddressRepository addressRepository;

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher){

        Teacher t = teacherRepository.findTeacherById(id);

        if(t == null)
            throw new ApiException("Teacher not found!");

        t.setName(teacher.getName());
        t.setEmail(teacher.getEmail());
        t.setAge(teacher.getAge());
        t.setSalary(teacher.getSalary());

        teacherRepository.save(t);
    }

    public void deleteTeacher(Integer id){

        Teacher t = teacherRepository.findTeacherById(id);

        if(t == null)
            throw new ApiException("Teacher not found!");

        Address address = addressRepository.findAddressById(id);


        t.setAddress(null);
        addressRepository.delete(address);
        teacherRepository.delete(t);

    }

    ///////////////

    public Teacher getTeacherById(Integer teacherId){

        Teacher teacher = teacherRepository.findTeacherById(teacherId);

        if(teacher == null)
            throw new ApiException("Teacher with this ID not found!");

        return teacher;
    }



}
