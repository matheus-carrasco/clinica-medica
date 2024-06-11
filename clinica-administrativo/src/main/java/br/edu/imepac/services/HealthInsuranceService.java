package br.edu.imepac.services;

import br.edu.imepac.dtos.health_insurance.HealthInsuranceCreateRequest;
import br.edu.imepac.dtos.health_insurance.HealthInsuranceDto;
import br.edu.imepac.models.HealthInsuranceModel;
import br.edu.imepac.repositories.HealthInsurenceRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthInsuranceService {

    private HealthInsurenceRepository repo;
    private ModelMapper modelMapper;

    public HealthInsuranceService(HealthInsurenceRepository healthInsurenceRepository, ModelMapper modelMapper){
        this.repo = healthInsurenceRepository;
        this.modelMapper = modelMapper;
    }

    public List<HealthInsuranceModel> findAll(){
        return repo.findAll();
    }

    public HealthInsuranceModel findById(Long id){
        Optional<HealthInsuranceModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public HealthInsuranceDto insert(HealthInsuranceCreateRequest request){
        HealthInsuranceModel savedObj = modelMapper.map(request, HealthInsuranceModel.class);
        repo.save(savedObj);
        return modelMapper.map(savedObj, HealthInsuranceDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public HealthInsuranceDto update(Long id, HealthInsuranceDto details){
        findById(id);
        HealthInsuranceModel obj = modelMapper.map(details, HealthInsuranceModel.class);
        repo.save(obj);
        return modelMapper.map(obj, HealthInsuranceDto.class);
    }
}
