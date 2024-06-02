package br.edu.imepac.services;

import br.edu.imepac.dtos.specialty.SpecialtyCreateRequest;
import br.edu.imepac.dtos.specialty.SpecialtyDto;
import br.edu.imepac.models.SpecialtyModel;
import br.edu.imepac.repositories.SpecialtyRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository repo;

    public List<SpecialtyModel> findAll(){
        return repo.findAll();
    }

    public SpecialtyModel findById(Long id){
        Optional<SpecialtyModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public SpecialtyDto insert(SpecialtyCreateRequest request){
        SpecialtyModel savedObj = repo.save(createModelFromRequest(request));
        return createDtoFromModel(savedObj);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public SpecialtyDto update(Long id, SpecialtyDto details){
        SpecialtyModel obj = findById(id);
        obj.setDescription(details.getDescription());

        SpecialtyModel updated = repo.save(obj);
        SpecialtyDto updateDto = createDtoFromModel(updated);
        updateDto.setId(updated.getId());

        return updateDto;
    }

    //auxiliar: cria um Model a partir de um Request
    public SpecialtyModel createModelFromRequest(SpecialtyCreateRequest request){
        SpecialtyModel obj = new SpecialtyModel();
        obj.setDescription(request.getDescription());
        return obj;
    }

    //auxiliar: Cria um DTO a partir de um Model
    public SpecialtyDto createDtoFromModel(SpecialtyModel model){
        SpecialtyDto dto = new SpecialtyDto();
        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        return dto;
    }
}
