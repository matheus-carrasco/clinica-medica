package br.edu.imepac.controllers;


import br.edu.imepac.dtos.agendamento.schedules.ScheduleCreateRequest;
import br.edu.imepac.dtos.agendamento.schedules.ScheduleDto;
import br.edu.imepac.models.agendamento.ScheduleModel;
import br.edu.imepac.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> findAll(){
        List<ScheduleModel> list = service.findAll();
        List<ScheduleDto> listDto = list.stream().map(x -> new ScheduleDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> findById(@PathVariable Long id){
        ScheduleModel obj = service.findById(id);
        return ResponseEntity.ok().body(new ScheduleDto(obj));
    }

    @PostMapping
    public ResponseEntity<ScheduleDto> insert(@RequestBody ScheduleCreateRequest request){
        ScheduleDto obj = service.insert(request);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> update(@PathVariable Long id, @RequestBody ScheduleCreateRequest newDto){
        ScheduleDto updated = service.update(id, newDto);
        return ResponseEntity.ok().body(updated);
    }
}
