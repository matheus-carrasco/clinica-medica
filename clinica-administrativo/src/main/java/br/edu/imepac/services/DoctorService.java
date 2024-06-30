package br.edu.imepac.services;

import br.edu.imepac.dtos.administrativo.doctors.DoctorCreateRequest;
import br.edu.imepac.dtos.administrativo.doctors.DoctorDto;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import br.edu.imepac.repositories.administrativo.DoctorRepository;
import br.edu.imepac.repositories.administrativo.SpecialtyRepository;
import br.edu.imepac.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<DoctorDto> findByName(String name){
        List<DoctorModel> list = repo.findByNameContainingIgnoreCase(name);
        if(list.isEmpty()){
            throw new ObjectNotFoundException("Doctor not found");
        }
        return list.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                .collect(Collectors.toList());
    }

    public DoctorModel findByCrm(String crm){
        return repo.findByCrmIgnoreCase(crm).orElseThrow(() -> new ObjectNotFoundException("Doctor not found"));
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

    public DoctorDto update(Long id, DoctorCreateRequest details){
        DoctorModel existing = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Doctor not found"));
        modelMapper.map(details, existing);

        List<SpecialtyModel> specialties = new ArrayList<>();
        for(SpecialtyModel specialty : details.getSpecialties()){
            SpecialtyModel existingSpecialty = specialtyRepo.findById(specialty.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Specialty not found"));
            specialties.add(existingSpecialty);
        }
        existing.setSpecialties(specialties);
        existing.setId(id);

        repo.save(existing);
        return modelMapper.map(existing, DoctorDto.class);
    }
}
