package br.edu.imepac.services;

import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.dtos.doctors.DoctorCreateRequest;
import br.edu.imepac.models.DoctorModel;
import br.edu.imepac.repositories.DoctorRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    public List<DoctorModel> findAll(){
        return repo.findAll();
    }

    public DoctorModel findById(Long id){
        Optional<DoctorModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public DoctorDto insert(DoctorCreateRequest request){
        DoctorModel savedObj = repo.save(createModelFromRequest(request));
        return createDtoFromModel(savedObj);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public DoctorDto update(Long id, DoctorDto details){
        DoctorModel obj = findById(id);
        obj.setName(details.getName());
        obj.setCrm(details.getCrm());

        DoctorModel updated = repo.save(obj);
        DoctorDto updateDto = createDtoFromModel(updated);
        updateDto.setId(updated.getId());

        return updateDto;
    }

    //auxiliar: cria um Model a partir de um Request
    //Ver com Everton se poderia criar apenas um construtor na classe DoctorModel, ou na .util
    public DoctorModel createModelFromRequest(DoctorCreateRequest request){
        DoctorModel obj = new DoctorModel();
        obj.setName(request.getName());
        obj.setCrm(request.getCrm());
        obj.setPassword(request.getPassword());
        return obj;
    }

    //auxiliar: Cria um DTO a partir de um Model
    //Ver com Everton se poderia criar apenas um construtor na classe DoctorDto, ou na .util
    public DoctorDto createDtoFromModel(DoctorModel model){
        DoctorDto dto = new DoctorDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCrm(model.getCrm());
        return dto;
    }

/*
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<DoctorDto> findAll() {
        List<DoctorModel> doctor = repo.findAll();
        return doctor.stream().map(medico -> {
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setId(medico.getId());
            doctorDto.setName(medico.getName());
            doctorDto.setCrm(medico.getCrm());
            return doctorDto;
        }).collect(Collectors.toList());
    }


    public DoctorDto update(Long id, DoctorDto doctorDetails) {
        Optional<DoctorModel> optionalDoctor = repo.findById(id);

        if (optionalDoctor.isPresent()) {
            DoctorModel doctorModel = optionalDoctor.get();
            doctorModel.setName(doctorDetails.getName());
            doctorModel.setCrm(doctorDetails.getCrm());

            DoctorModel updatedDoctor = repo.save(doctorModel);

            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setId(updatedDoctor.getId());
            doctorDto.setName(updatedDoctor.getName());
            doctorDto.setCrm(updatedDoctor.getCrm());

            return doctorDto;
        } else {
            return null;
        }
    }

    public DoctorDto save(DoctorCreateRequest doctorRequest) {
        DoctorModel doctorModel = new DoctorModel();
        doctorModel.setName(doctorRequest.getNome());
        doctorModel.setCrm(doctorRequest.getCrm());
        doctorModel.setPassword(doctorRequest.getPassword());

        DoctorModel savedDoctor = repo.save(doctorModel);

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(savedDoctor.getId());
        doctorDto.setName(savedDoctor.getName());
        doctorDto.setCrm(savedDoctor.getCrm());

        return doctorDto;
    }

    public DoctorDto findById(Long id) {
        Optional<DoctorModel> optionalDocter = repo.findById(id);
        if (optionalDocter.isPresent()) {
            DoctorModel medicoModel = optionalDocter.get();
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.setId(medicoModel.getId());
            doctorDto.setName(medicoModel.getName());
            doctorDto.setCrm(medicoModel.getCrm());
            return doctorDto;
        } else {
            return null;
        }
    }
*/
}
