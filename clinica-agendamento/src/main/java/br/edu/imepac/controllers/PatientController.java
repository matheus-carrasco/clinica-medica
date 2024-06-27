package br.edu.imepac.controllers;

import br.edu.imepac.dtos.patients.PatientCreateRequest;
import br.edu.imepac.dtos.patients.PatientDto;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll(){
        List<PatientModel> list = service.findAll();
        List<PatientDto> listDto = list.stream().map(x -> new PatientDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable Long id){
        PatientModel obj = service.findById(id);
        return ResponseEntity.ok().body(new PatientDto(obj));
    }

    @PostMapping
    public ResponseEntity<PatientDto> insert(@RequestBody PatientCreateRequest request){
        PatientDto obj = service.insert(request);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable Long id, @RequestBody PatientDto newDto){
        PatientDto updated = service.update(id, newDto);
        return ResponseEntity.ok().body(updated);
    }
}