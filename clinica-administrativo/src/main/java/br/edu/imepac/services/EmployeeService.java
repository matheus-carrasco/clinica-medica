package br.edu.imepac.services;

import br.edu.imepac.dtos.employees.EmployeeCreateRequest;
import br.edu.imepac.dtos.employees.EmployeeDto;
import br.edu.imepac.models.EmployeeModel;
import br.edu.imepac.repositories.EmployeeRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository repo;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.repo = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeModel> findAll(){
        return repo.findAll();
    }

    public EmployeeModel findById(Long id){
        Optional<EmployeeModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public EmployeeDto insert(EmployeeCreateRequest request){
        EmployeeModel savedObj = modelMapper.map(request, EmployeeModel.class);
        repo.save(savedObj);
        return modelMapper.map(savedObj, EmployeeDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public EmployeeDto update(Long id, EmployeeDto details){
        findById(id);
        EmployeeModel obj = modelMapper.map(details, EmployeeModel.class);
        repo.save(obj);
        return modelMapper.map(obj, EmployeeDto.class);
    }
}
