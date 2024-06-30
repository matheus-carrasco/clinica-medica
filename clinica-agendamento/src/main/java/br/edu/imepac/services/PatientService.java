package br.edu.imepac.services;


import br.edu.imepac.dtos.administrativo.doctors.DoctorDto;
import br.edu.imepac.dtos.agendamento.patients.PatientCreateRequest;
import br.edu.imepac.dtos.agendamento.patients.PatientDto;
import br.edu.imepac.exceptions.ObjectNotFoundException;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.repositories.agendamento.PatientRepository;
import org.modelmapper.ModelMapper;
import br.edu.imepac.repositories.administrativo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repo;

    @Autowired
    private HealthInsuranceRepository healthInsuranceRepository;



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

    public List<PatientDto> findByName(String name){
        List<PatientModel> list = repo.findByNameContainingIgnoreCase(name);
        if(list.isEmpty()){
            throw new ObjectNotFoundException("Patient not found");
        }
        return list.stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList());
    }

    public PatientModel findByRgNumber(String rgNumber){
        return repo.findByRgNumberIgnoreCase(rgNumber).orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
    }

    public PatientModel findByCpfNumber(String cpfNumber){
        return repo.findByCpfNumberIgnoreCase(cpfNumber).orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
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

    public PatientDto update(Long id, PatientCreateRequest details){
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