package br.edu.imepac.services;

import br.edu.imepac.dtos.patients.PatientCreateRequest;
import br.edu.imepac.dtos.patients.PatientDto;
import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import br.edu.imepac.models.agendamento.PatientModel;
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

        if(request.getHealthInsuranceId() != null){
            HealthInsuranceModel healthInsurance = new HealthInsuranceModel();
            healthInsurance.setId(request.getHealthInsuranceId());
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
        PatientModel obj = repo.getReferenceById(id);
        modelMapper.map(details, obj);
        if(details.getHealthInsuranceId() != null){
            HealthInsuranceModel healthInsurance = new HealthInsuranceModel();
            healthInsurance.setId(details.getHealthInsuranceId());
            obj.setHealthInsurance(healthInsurance);
        }
        obj.setId(id);
        repo.save(obj);
        return modelMapper.map(obj, PatientDto.class);
    }
}
