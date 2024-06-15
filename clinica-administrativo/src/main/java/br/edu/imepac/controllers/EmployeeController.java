package br.edu.imepac.controllers;

import br.edu.imepac.dtos.employees.EmployeeCreateRequest;
import br.edu.imepac.dtos.employees.EmployeeDto;
import br.edu.imepac.models.EmployeeModel;
import br.edu.imepac.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll(){
        List<EmployeeModel> list = service.findAll();
        List<EmployeeDto> listDto = list.stream().map(x -> new EmployeeDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id){
        EmployeeModel obj = service.findById(id);
        return ResponseEntity.ok().body(new EmployeeDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody EmployeeCreateRequest request){
        EmployeeDto obj = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody EmployeeDto newDto){
        EmployeeDto updated = service.update(id, newDto);
        return ResponseEntity.noContent().build();
    }
}
