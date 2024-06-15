package br.edu.imepac.services;

import br.edu.imepac.dtos.PatientCreateRequest;
import br.edu.imepac.dtos.PatientDto;
import br.edu.imepac.models.PatientModel;
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

    public PatientDto insert(PatientCreateRequest request){
        PatientModel savedObj = modelMapper.map(request, PatientModel.class);
        repo.save(savedObj);
        return modelMapper.map(savedObj, PatientDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public PatientDto update(Long id, PatientDto details){
        findById(id);
        PatientModel obj = modelMapper.map(details, PatientModel.class);
        repo.save(obj);
        return modelMapper.map(obj, PatientDto.class);
    }
}
