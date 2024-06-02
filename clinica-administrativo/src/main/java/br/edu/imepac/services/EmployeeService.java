package br.edu.imepac.services;

import br.edu.imepac.dtos.employees.EmployeeCreateRequest;
import br.edu.imepac.dtos.employees.EmployeeDto;
import br.edu.imepac.models.EmployeeModel;
import br.edu.imepac.repositories.EmployeeRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public List<EmployeeModel> findAll(){
        return repo.findAll();
    }

    public EmployeeModel findById(Long id){
        Optional<EmployeeModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public EmployeeDto insert(EmployeeCreateRequest request){
        EmployeeModel savedObj = repo.save(createModelFromRequest(request));
        return createDtoFromModel(savedObj);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public EmployeeDto update(Long id, EmployeeDto details){
        EmployeeModel obj = findById(id);
        obj.setName(details.getName());
        obj.setAdress(details.getAdress());
        obj.setCity(details.getCity());
        obj.setState(details.getState());
        obj.setPhone(details.getPhone());
        obj.setDateOfBirth(details.getDateOfBirth());

        EmployeeModel updated = repo.save(obj);
        EmployeeDto updateDto = createDtoFromModel(updated);
        updateDto.setId(updated.getId());

        return updateDto;
    }

    //auxiliar: cria um Model a partir de um Request
    public EmployeeModel createModelFromRequest(EmployeeCreateRequest request){
        EmployeeModel obj = new EmployeeModel();
        obj.setName(request.getName());
        obj.setIdNumber(request.getIdNumber());
        obj.setCpfNumber(request.getCpfNumber());
        obj.setAdress(request.getAdress());
        obj.setCity(request.getCity());
        obj.setState(request.getState());
        obj.setPhone(request.getPhone());
        obj.setWorkPermitNumber(request.getWorkPermitNumber());
        obj.setPisNumber(request.getPisNumber());
        obj.setDateOfBirth(request.getDateOfBirth());
        return obj;
    }

    //auxiliar: Cria um DTO a partir de um Model
    public EmployeeDto createDtoFromModel(EmployeeModel model){
        EmployeeDto dto = new EmployeeDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setAdress(model.getAdress());
        dto.setCity(model.getCity());
        dto.setState(model.getState());
        dto.setPhone(model.getPhone());
        dto.setDateOfBirth(model.getDateOfBirth());
        return dto;
    }
}
