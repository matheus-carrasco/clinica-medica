package br.edu.imepac.services;


import br.edu.imepac.dtos.administrativo.employee.EmployeeCreateRequest;
import br.edu.imepac.dtos.administrativo.employee.EmployeeDto;
import br.edu.imepac.exceptions.ObjectNotFoundException;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.repositories.administrativo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<EmployeeDto> findByName(String name){
        List<EmployeeModel> list = repo.findByNameContainingIgnoreCase(name);
        if(list.isEmpty()){
            throw new ObjectNotFoundException("Employee not found");
        }
        return list.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeModel findByRgNumber(String rgNumber){
        return repo.findByRgNumberIgnoreCase(rgNumber).orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
    }

    public EmployeeModel findByCpfNumber(String cpfNumber){
        return repo.findByCpfNumberIgnoreCase(cpfNumber).orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
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

    public EmployeeDto update(Long id, EmployeeCreateRequest details){
        EmployeeModel existing = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
        modelMapper.map(details, existing);
        existing.setId(id);
        repo.save(existing);
        return modelMapper.map(existing, EmployeeDto.class);
    }
}
