package br.edu.imepac.controllers;

import br.edu.imepac.dtos.doctors.DoctorCreateRequest;
import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping
    public ResponseEntity<List<DoctorDto>> findAll(){
        List<DoctorModel> list = service.findAll();
        List<DoctorDto> listDto = list.stream().map(x -> new DoctorDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> findById(@PathVariable Long id){
        DoctorModel obj = service.findById(id);
        return ResponseEntity.ok().body(new DoctorDto(obj));
    }

    @PostMapping
    public ResponseEntity<DoctorDto> insert(@RequestBody DoctorCreateRequest request){
        DoctorDto obj = service.insert(request);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update(@PathVariable Long id, @RequestBody DoctorDto details){
        DoctorDto updated = service.update(id, details);
        return ResponseEntity.ok().body(updated);
    }
}
