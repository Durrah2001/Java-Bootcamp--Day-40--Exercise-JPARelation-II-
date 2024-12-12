package org.example.schoolsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolsystem.ApiResponse.ApiException;
import org.example.schoolsystem.DTO.AddressDTO;
import org.example.schoolsystem.Model.Address;
import org.example.schoolsystem.Model.Teacher;
import org.example.schoolsystem.Repository.AddressRepository;
import org.example.schoolsystem.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;


    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }


    public void addAddress(AddressDTO addressDTO){

        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());

        if(teacher == null)
            throw new ApiException("Teacher not found!");

        Address address = new Address(null, addressDTO.getStreet(), addressDTO.getBuildingNumber(), teacher );

        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO){

        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());

        if(address == null)
            throw new ApiException("Address not found!");

        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());

        addressRepository.save(address);
    }




}
