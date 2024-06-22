package br.edu.imepac.services;

import br.edu.imepac.dtos.schedules.ScheduleCreateRequest;
import br.edu.imepac.dtos.schedules.ScheduleDto;
import br.edu.imepac.models.agendamento.ScheduleModel;
import br.edu.imepac.repositories.ScheduleRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repo;
    private ModelMapper modelMapper;

    public ScheduleService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<ScheduleModel> findAll(){
        return repo.findAll();
    }

    public ScheduleModel findById(Long id){
        Optional<ScheduleModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Schedule not found"));
    }

    public ScheduleDto insert(ScheduleCreateRequest request){
        ScheduleModel obj = modelMapper.map(request, ScheduleModel.class);
        ScheduleModel savedObj = repo.save(obj);
        return modelMapper.map(savedObj, ScheduleDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public ScheduleDto update(Long id, ScheduleDto details){
        ScheduleModel obj = repo.getReferenceById(id);
        modelMapper.map(details, obj);
        obj.setId(id);
        repo.save(obj);
        return modelMapper.map(obj, ScheduleDto.class);
    }
}
