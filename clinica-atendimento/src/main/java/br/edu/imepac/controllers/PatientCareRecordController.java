package br.edu.imepac.controllers;

import br.edu.imepac.dtos.records.PatientCareRecordCreateRequest;
import br.edu.imepac.dtos.records.PatientCareRecordDto;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import br.edu.imepac.services.PatientCareRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/records")
public class PatientCareRecordController {

    @Autowired
    private PatientCareRecordService service;

    @GetMapping
    public ResponseEntity<List<PatientCareRecordDto>> findAll(){
        List<PatientCareRecordModel> list = service.findAll();
        List<PatientCareRecordDto> listDto = list.stream().map(x -> new PatientCareRecordDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientCareRecordDto> findById(@PathVariable Long id){
        PatientCareRecordModel obj = service.findById(id);
        return ResponseEntity.ok().body(new PatientCareRecordDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody PatientCareRecordCreateRequest request){
        PatientCareRecordDto obj = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientCareRecordDto> update(@PathVariable Long id, @RequestBody PatientCareRecordDto newDto){
        PatientCareRecordDto updated = service.update(id, newDto);
        return ResponseEntity.noContent().build();
    }
}
