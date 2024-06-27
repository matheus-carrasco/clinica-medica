package br.edu.imepac.services;

import br.edu.imepac.dtos.employees.EmployeeCreateRequest;
import br.edu.imepac.dtos.employees.EmployeeDto;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.repositories.EmployeeRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;
    private ModelMapper modelMapper;

    public EmployeeService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<EmployeeModel> findAll(){
        return repo.findAll();
    }

    public EmployeeModel findById(Long id){
        Optional<EmployeeModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
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
        EmployeeModel obj = repo.getReferenceById(id);
        EmployeeModel updated = modelMapper.map(details, EmployeeModel.class);
        updated.setId(obj.getId());
        repo.save(updated);
        return modelMapper.map(updated, EmployeeDto.class);
    }
}
