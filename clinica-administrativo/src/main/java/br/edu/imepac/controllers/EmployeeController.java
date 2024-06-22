package br.edu.imepac.controllers;

import br.edu.imepac.dtos.employees.EmployeeCreateRequest;
import br.edu.imepac.dtos.employees.EmployeeDto;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

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
    public ResponseEntity<EmployeeDto> insert(@RequestBody EmployeeCreateRequest request){
        EmployeeDto obj = service.insert(request);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
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
