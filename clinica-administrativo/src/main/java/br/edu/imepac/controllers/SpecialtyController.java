package br.edu.imepac.controllers;


import br.edu.imepac.dtos.administrativo.specialty.SpecialtyCreateRequest;
import br.edu.imepac.dtos.administrativo.specialty.SpecialtyDto;
import br.edu.imepac.dtos.administrativo.specialty.SpecialtyWithDoctorsDto;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import br.edu.imepac.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService service;

    @GetMapping
    public ResponseEntity<List<SpecialtyDto>> findAll(){
        List<SpecialtyModel> list = service.findAll();
        List<SpecialtyDto> listDto = list.stream().map(x -> new SpecialtyDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDto> findById(@PathVariable Long id){
        SpecialtyModel obj = service.findById(id);
        return ResponseEntity.ok().body(new SpecialtyDto(obj));
    }

    @GetMapping("/withDoctors/{id}")
    public ResponseEntity<SpecialtyWithDoctorsDto> findSpecialtyWithDoctors(@PathVariable Long id){
        SpecialtyWithDoctorsDto obj = service.findSpecialtyWithDoctors(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/findByDescription")
    public ResponseEntity<SpecialtyDto> findByDescription(@RequestParam("description") String description){
        SpecialtyModel obj = service.findByDescription(description);
        return ResponseEntity.ok().body(new SpecialtyDto(obj));
    }

    @PostMapping
    public ResponseEntity<SpecialtyDto> insert(@RequestBody SpecialtyCreateRequest request){
        SpecialtyDto obj = service.insert(request);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDto> update(@PathVariable Long id, @RequestBody SpecialtyCreateRequest newDto){
        SpecialtyDto updated = service.update(id, newDto);
        return ResponseEntity.ok().body(updated);
    }
}
