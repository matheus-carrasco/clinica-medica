package br.edu.imepac.services;

import br.edu.imepac.dtos.patients.PatientCreateRequest;
import br.edu.imepac.dtos.patients.PatientDto;
import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.repositories.HealthInsurenceRepository;
import br.edu.imepac.repositories.PatientRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repo;

    @Autowired
    private HealthInsurenceRepository healthInsuranceRepository;

    private ModelMapper modelMapper;

    public PatientService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<PatientModel> findAll(){
        return repo.findAll();
    }

    public PatientModel findById(Long id){
        Optional<PatientModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Patient not found"));
    }

    public PatientDto insert(PatientCreateRequest request){
        PatientModel obj = modelMapper.map(request, PatientModel.class);

        if (request.getHealthInsurance() != null && request.getHealthInsurance().getId() != null) {
            HealthInsuranceModel healthInsurance = healthInsuranceRepository.findById(request.getHealthInsurance().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Health Insurance not found"));
            obj.setHealthInsurance(healthInsurance);
        }
        PatientModel savedObj = repo.save(obj);
        return modelMapper.map(savedObj, PatientDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public PatientDto update(Long id, PatientDto details){
        findById(id);
        PatientModel obj = repo.getReferenceById(id);
        modelMapper.map(details, obj);
        if (details.getHealthInsurance() != null && details.getHealthInsurance().getId() != null) {
            HealthInsuranceModel healthInsurance = healthInsuranceRepository.findById(details.getHealthInsurance().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Health Insurance not found"));
            obj.setHealthInsurance(healthInsurance);
        } else {
            obj.setHealthInsurance(null);
        }
        obj.setId(id);
        repo.save(obj);
        return modelMapper.map(obj, PatientDto.class);
    }
}