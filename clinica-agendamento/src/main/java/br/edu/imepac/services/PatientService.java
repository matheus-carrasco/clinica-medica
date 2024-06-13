package br.edu.imepac.services;

import br.edu.imepac.dtos.health_insurance.HealthInsuranceCreateRequest;
import br.edu.imepac.dtos.health_insurance.HealthInsuranceDto;
import br.edu.imepac.models.HealthInsuranceModel;
import br.edu.imepac.models.PatientModel;
import br.edu.imepac.repositories.HealthInsurenceRepository;
import br.edu.imepac.repositories.PatientRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository repo;
    private ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper){
        this.repo = patientRepository;
        this.modelMapper = modelMapper;
    }

    public List<PatientModel> findAll(){
        return repo.findAll();
    }

    public PatientModel findById(Long id){
        Optional<PatientModel> obj = repo.findById(id);
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
