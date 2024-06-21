package br.edu.imepac.controllers;

import br.edu.imepac.dtos.specialty.SpecialtyCreateRequest;
import br.edu.imepac.dtos.specialty.SpecialtyDto;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import br.edu.imepac.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody SpecialtyCreateRequest request){
        SpecialtyDto obj = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDto> update(@PathVariable Long id, @RequestBody SpecialtyDto newDto){
        SpecialtyDto updated = service.update(id, newDto);
        return ResponseEntity.noContent().build();
    }
}
