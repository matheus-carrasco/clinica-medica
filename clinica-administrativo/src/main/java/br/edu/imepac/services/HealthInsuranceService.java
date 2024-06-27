package br.edu.imepac.services;

import br.edu.imepac.dtos.health_insurance.HealthInsuranceCreateRequest;
import br.edu.imepac.dtos.health_insurance.HealthInsuranceDto;
import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import br.edu.imepac.repositories.HealthInsurenceRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthInsuranceService {

    @Autowired
    private HealthInsurenceRepository repo;

    private ModelMapper modelMapper;

    public HealthInsuranceService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<HealthInsuranceModel> findAll(){
        return repo.findAll();
    }

    public HealthInsuranceModel findById(Long id){
        Optional<HealthInsuranceModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Health Insurance not found"));
    }

    public HealthInsuranceDto insert(HealthInsuranceCreateRequest request){
        HealthInsuranceModel healthInsurance = modelMapper.map(request, HealthInsuranceModel.class);
        HealthInsuranceModel savedObj = repo.save(healthInsurance);
        return modelMapper.map(savedObj, HealthInsuranceDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public HealthInsuranceDto update(Long id, HealthInsuranceDto details){
        findById(id);
        HealthInsuranceModel obj = repo.getReferenceById(id);
        HealthInsuranceModel updated = modelMapper.map(details, HealthInsuranceModel.class);
        updated.setId(obj.getId());
        repo.save(updated);
        return modelMapper.map(updated, HealthInsuranceDto.class);
    }
}
