package br.edu.imepac.services;

import br.edu.imepac.dtos.doctors.DoctorCreateRequest;
import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.repositories.DoctorRepository;
import br.edu.imepac.repositories.SpecialtyRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        DoctorModel model = modelMapper.map(request, DoctorModel.class);
        DoctorModel savedObj = repo.save(model);
        return modelMapper.map(savedObj, DoctorDto.class);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public DoctorDto update(Long id, DoctorDto details){
        Optional<DoctorModel> opt = repo.findById(id);
        if(opt.isPresent()){
            DoctorModel model = opt.get();
            DoctorModel savedObj = repo.save(model);
            return modelMapper.map(savedObj, DoctorDto.class);
        }
        else {
            return null;
        }
    }
}
