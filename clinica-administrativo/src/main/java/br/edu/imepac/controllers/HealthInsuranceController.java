package br.edu.imepac.controllers;

import br.edu.imepac.dtos.health_insurance.HealthInsuranceCreateRequest;
import br.edu.imepac.dtos.health_insurance.HealthInsuranceDto;
import br.edu.imepac.models.HealthInsuranceModel;
import br.edu.imepac.services.HealthInsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/healthinsurances")
public class HealthInsuranceController {

    private HealthInsuranceService service;

    public HealthInsuranceController(HealthInsuranceService service){
        this.service = service;
    }
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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody HealthInsuranceCreateRequest request){
        HealthInsuranceDto obj = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthInsuranceDto> update(@PathVariable Long id, @RequestBody HealthInsuranceDto newDto){
        HealthInsuranceDto updated = service.update(id, newDto);
        return ResponseEntity.noContent().build();
    }
}
