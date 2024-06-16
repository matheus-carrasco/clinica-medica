package br.edu.imepac.services;

import br.edu.imepac.dtos.PatientCareRecordCreateRequest;
import br.edu.imepac.dtos.PatientCareRecordDto;
import br.edu.imepac.models.PatientCareRecordModel;
import br.edu.imepac.repositories.PatientCareRecordRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientCareRecordService {

    @Autowired
    private PatientCareRecordRepository repo;
    private ModelMapper modelMapper;

    public PatientCareRecordService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<PatientCareRecordModel> findAll(){
        return repo.findAll();
    }

    public PatientCareRecordModel findById(Long id){
        Optional<PatientCareRecordModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public PatientCareRecordDto insert(PatientCareRecordCreateRequest request){
        PatientCareRecordModel savedObj = modelMapper.map(request, PatientCareRecordModel.class);
        repo.save(savedObj);
        return modelMapper.map(savedObj, PatientCareRecordDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public PatientCareRecordDto update(Long id, PatientCareRecordDto details){
        findById(id);
        PatientCareRecordModel obj = modelMapper.map(details, PatientCareRecordModel.class);
        repo.save(obj);
        return modelMapper.map(obj, PatientCareRecordDto.class);
    }
}
