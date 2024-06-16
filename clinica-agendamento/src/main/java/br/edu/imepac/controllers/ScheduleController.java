package br.edu.imepac.controllers;

import br.edu.imepac.dtos.schedules.ScheduleCreateRequest;
import br.edu.imepac.dtos.schedules.ScheduleDto;
import br.edu.imepac.models.ScheduleModel;
import br.edu.imepac.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Void> insert(@RequestBody ScheduleCreateRequest request){
        ScheduleDto obj = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> update(@PathVariable Long id, @RequestBody ScheduleDto newDto){
        ScheduleDto updated = service.update(id, newDto);
        return ResponseEntity.noContent().build();
    }
}
