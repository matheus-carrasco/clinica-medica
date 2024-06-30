package br.edu.imepac.controllers;


import br.edu.imepac.dtos.administrativo.employee.EmployeeCreateRequest;
import br.edu.imepac.dtos.administrativo.employee.EmployeeDto;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findByName")
    public ResponseEntity<List<EmployeeDto>> findByName(@RequestParam("name") String name){
        List<EmployeeDto> listDto = service.findByName(name);
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/findByRgNumber")
    public ResponseEntity<EmployeeDto> findByRgNumber(@RequestParam("rgNumber") String rgNumber){
        EmployeeModel obj = service.findByRgNumber(rgNumber);
        return ResponseEntity.ok().body(new EmployeeDto(obj));
    }

    @GetMapping("/findByCpfNumber")
    public ResponseEntity<EmployeeDto> findByCpfNumber(@RequestParam("cpfNumber") String cpfNumber){
        EmployeeModel obj = service.findByCpfNumber(cpfNumber);
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
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody EmployeeCreateRequest newDto){
        EmployeeDto updated = service.update(id, newDto);
        return ResponseEntity.ok().body(updated);
    }
}
