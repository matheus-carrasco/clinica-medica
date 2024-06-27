package br.edu.imepac.services;

import br.edu.imepac.dtos.doctors.DoctorCreateRequest;
import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import br.edu.imepac.repositories.DoctorRepository;
import br.edu.imepac.repositories.SpecialtyRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    @Autowired
    private SpecialtyRepository specialtyRepo;

    private ModelMapper modelMapper;

    public DoctorService(ModelMapper modelMapper) {
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
        List<SpecialtyModel> specialties = new ArrayList<>();
        for(SpecialtyModel specialty : request.getSpecialties()){
            SpecialtyModel existingSpecialty = specialtyRepo.findById(specialty.getId()).orElseThrow(() -> new ObjectNotFoundException("Specialty not found"));
            specialties.add(existingSpecialty);
        }
        DoctorModel model = modelMapper.map(request, DoctorModel.class);
        model.setSpecialties(specialties);
        repo.save(model);
        return modelMapper.map(model, DoctorDto.class);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public DoctorDto update(Long id, DoctorDto details){
        findById(id);
        List<SpecialtyModel> specialties = new ArrayList<>();
        for(SpecialtyModel specialty : details.getSpecialties()){
            SpecialtyModel existingSpecialty = specialtyRepo.findById(specialty.getId()).orElseThrow(() -> new ObjectNotFoundException("Specialty not found"));
            specialties.add(existingSpecialty);
        }
        DoctorModel model = modelMapper.map(details, DoctorModel.class);
        model.setSpecialties(specialties);
        model.setId(id);
        repo.save(model);
        return modelMapper.map(model, DoctorDto.class);
    }
}
