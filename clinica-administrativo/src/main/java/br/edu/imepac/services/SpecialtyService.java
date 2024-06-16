package br.edu.imepac.services;

import br.edu.imepac.dtos.specialty.SpecialtyCreateRequest;
import br.edu.imepac.dtos.specialty.SpecialtyDto;
import br.edu.imepac.models.SpecialtyModel;
import br.edu.imepac.repositories.SpecialtyRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository repo;
    private ModelMapper modelMapper;

    public SpecialtyService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<SpecialtyModel> findAll(){
        return repo.findAll();
    }

    public SpecialtyModel findById(Long id){
        Optional<SpecialtyModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Specialty not found"));
    }

    public SpecialtyDto insert(SpecialtyCreateRequest request){
        SpecialtyModel savedObj = modelMapper.map(request, SpecialtyModel.class);
        repo.save(savedObj);
        return modelMapper.map(savedObj, SpecialtyDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public SpecialtyDto update(Long id, SpecialtyDto details){
        findById(id);
        SpecialtyModel obj = modelMapper.map(details, SpecialtyModel.class);
        repo.save(obj);
        return modelMapper.map(obj, SpecialtyDto.class);
    }
}
