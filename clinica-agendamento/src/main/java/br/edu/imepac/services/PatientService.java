package br.edu.imepac.services;

import br.edu.imepac.dtos.patients.PatientCreateRequest;
import br.edu.imepac.dtos.patients.PatientDto;
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
