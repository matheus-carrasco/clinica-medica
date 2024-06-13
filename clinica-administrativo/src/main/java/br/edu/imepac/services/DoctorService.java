package br.edu.imepac.services;

import br.edu.imepac.dtos.doctors.DoctorCreateRequest;
import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.models.DoctorModel;
import br.edu.imepac.models.SpecialtyModel;
import br.edu.imepac.repositories.DoctorRepository;
import br.edu.imepac.repositories.SpecialtyRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepository repo;

    private SpecialtyRepository specialtyRepo;

    private ModelMapper modelMapper;

    public DoctorService(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepo, ModelMapper modelMapper) {
        this.repo = doctorRepository;
        this.specialtyRepo = specialtyRepo;
        this.modelMapper = modelMapper;
    }

    public List<DoctorModel> findAll(){
        return repo.findAll();
    }

    public DoctorModel findById(Long id){
        Optional<DoctorModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Doctor not found"));
    }

    public DoctorDto insert(DoctorCreateRequest request){
        Optional<SpecialtyModel> specialty = specialtyRepo.findById(request.getSpecialty().getId());
        DoctorModel savedObj = modelMapper.map(request, DoctorModel.class);
        savedObj.setSpecialty(specialty.get());
        repo.save(savedObj);
        return modelMapper.map(savedObj, DoctorDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public DoctorDto update(Long id, DoctorDto details){
        findById(id);
        DoctorModel obj = modelMapper.map(details, DoctorModel.class);
        repo.save(obj);
        return modelMapper.map(obj, DoctorDto.class);
    }
}
