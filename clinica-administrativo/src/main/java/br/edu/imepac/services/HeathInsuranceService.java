package br.edu.imepac.services;

import br.edu.imepac.dtos.health_insurance.HealthInsuranceCreateRequest;
import br.edu.imepac.dtos.health_insurance.HealthInsuranceDto;
import br.edu.imepac.models.HealthInsuranceModel;
import br.edu.imepac.repositories.HealthInsurenceRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeathInsuranceService {

    @Autowired
    private HealthInsurenceRepository repo;

    public List<HealthInsuranceModel> findAll(){
        return repo.findAll();
    }

    public HealthInsuranceModel findById(Long id){
        Optional<HealthInsuranceModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public HealthInsuranceDto insert(HealthInsuranceCreateRequest request){
        HealthInsuranceModel savedObj = repo.save(createModelFromRequest(request));
        return createDtoFromModel(savedObj);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public HealthInsuranceDto update(Long id, HealthInsuranceDto details){
        HealthInsuranceModel obj = findById(id);
        obj.setName(details.getName());
        obj.setCnpj(details.getCnpj());
        obj.setPhone(details.getPhone());

        HealthInsuranceModel updated = repo.save(obj);
        HealthInsuranceDto updateDto = createDtoFromModel(updated);
        updateDto.setId(updated.getId());

        return updateDto;
    }

    //auxiliar: cria um Model a partir de um Request
    public HealthInsuranceModel createModelFromRequest(HealthInsuranceCreateRequest request){
        HealthInsuranceModel obj = new HealthInsuranceModel();
        obj.setName(request.getName());
        obj.setPhone(request.getPhone());
        obj.setCnpj(request.getPhone());
        return obj;
    }

    //auxiliar: Cria um DTO a partir de um Model
    public HealthInsuranceDto createDtoFromModel(HealthInsuranceModel model){
        HealthInsuranceDto dto = new HealthInsuranceDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCnpj(model.getCnpj());
        dto.setPhone(model.getPhone());
        return dto;
    }
}
