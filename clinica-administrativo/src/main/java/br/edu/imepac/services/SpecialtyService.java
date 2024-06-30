package br.edu.imepac.services;

import br.edu.imepac.dtos.administrativo.specialty.SpecialtyCreateRequest;
import br.edu.imepac.dtos.administrativo.specialty.SpecialtyDto;
import br.edu.imepac.dtos.administrativo.specialty.SpecialtyWithDoctorsDto;
import br.edu.imepac.exceptions.ObjectNotFoundException;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import br.edu.imepac.repositories.administrativo.SpecialtyRepository;
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

    public SpecialtyWithDoctorsDto findSpecialtyWithDoctors(Long id){
        SpecialtyModel obj = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Specialty not found"));
        return new SpecialtyWithDoctorsDto(obj);
    }

    public SpecialtyModel findByDescription(String description){
        return repo.findByDescriptionIgnoreCase(description).orElseThrow(() -> new ObjectNotFoundException("Specialty not found"));
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

    public SpecialtyDto update(Long id, SpecialtyCreateRequest details){
        SpecialtyModel existing = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Specialty not found"));
        modelMapper.map(details, existing);
        existing.setId(id);
        repo.save(existing);
        return modelMapper.map(existing, SpecialtyDto.class);
    }
}
