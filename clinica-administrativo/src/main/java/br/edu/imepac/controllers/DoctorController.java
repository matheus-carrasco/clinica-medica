package br.edu.imepac.controllers;

import br.edu.imepac.dtos.doctors.DoctorCreateRequest;
import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.models.DoctorModel;
import br.edu.imepac.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorService service;

    public DoctorController(DoctorService service){
        this.service = service;
    }

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
    public ResponseEntity<Void> insert(@RequestBody DoctorCreateRequest request){
        DoctorDto obj = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update(@PathVariable Long id, @RequestBody DoctorDto newDto){
        DoctorDto updated = service.update(id, newDto);
        return ResponseEntity.noContent().build();
    }
}
