package br.edu.imepac.controllers;

import br.edu.imepac.dtos.administrativo.health_insurance.HealthInsuranceCreateRequest;
import br.edu.imepac.dtos.administrativo.health_insurance.HealthInsuranceDto;
import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import br.edu.imepac.services.HealthInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/health_insurances")
public class HealthInsuranceController {

    @Autowired
    private HealthInsuranceService service;

    @GetMapping
    public ResponseEntity<List<HealthInsuranceDto>> findAll(){
        List<HealthInsuranceModel> list = service.findAll();
        List<HealthInsuranceDto> listDto = list.stream().map(x -> new HealthInsuranceDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthInsuranceDto> findById(@PathVariable Long id){
        HealthInsuranceModel obj = service.findById(id);
        return ResponseEntity.ok().body(new HealthInsuranceDto(obj));
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<HealthInsuranceDto>> findByName(@RequestParam("name") String name){
        List<HealthInsuranceDto> listDto = service.findByName(name);
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/findByCnpjNumber")
    public ResponseEntity<HealthInsuranceDto> findByCnpjNumber(@RequestParam("cnpj") String cnpj){
        HealthInsuranceModel obj = service.findByCnpj(cnpj);
        return ResponseEntity.ok().body(new HealthInsuranceDto(obj));
    }

    @PostMapping
    public ResponseEntity<HealthInsuranceDto> insert(@RequestBody HealthInsuranceCreateRequest request){
        HealthInsuranceDto obj = service.insert(request);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthInsuranceDto> update(@PathVariable Long id, @RequestBody HealthInsuranceCreateRequest newDto){
        HealthInsuranceDto updated = service.update(id, newDto);
        return ResponseEntity.ok().body(updated);
    }
}
