package br.edu.imepac.services;


import br.edu.imepac.dtos.administrativo.health_insurance.HealthInsuranceCreateRequest;
import br.edu.imepac.dtos.administrativo.health_insurance.HealthInsuranceDto;
import br.edu.imepac.exceptions.ObjectNotFoundException;
import br.edu.imepac.models.administrativo.HealthInsuranceModel;

import br.edu.imepac.repositories.administrativo.HealthInsuranceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HealthInsuranceService {

    @Autowired
    private HealthInsuranceRepository repo;

    private ModelMapper modelMapper;

    public HealthInsuranceService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<HealthInsuranceModel> findAll(){
        return repo.findAll();
    }

    public HealthInsuranceModel findById(Long id){
        Optional<HealthInsuranceModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Health Insurance not found"));
    }

    public List<HealthInsuranceDto> findByName(String name){
        List<HealthInsuranceModel> list = repo.findByNameContainingIgnoreCase(name);
        if(list.isEmpty()){
            throw new ObjectNotFoundException("Health Insurance not found");
        }
        return list.stream()
                .map(healthInsurance -> modelMapper.map(healthInsurance, HealthInsuranceDto.class))
                .collect(Collectors.toList());
    }

    public HealthInsuranceModel findByCnpj(String cnpj){
        return repo.findByCnpjIgnoreCase(cnpj).orElseThrow(() -> new ObjectNotFoundException("Health Insurance not found"));
    }

    public HealthInsuranceDto insert(HealthInsuranceCreateRequest request){
        HealthInsuranceModel healthInsurance = modelMapper.map(request, HealthInsuranceModel.class);
        HealthInsuranceModel savedObj = repo.save(healthInsurance);
        return modelMapper.map(savedObj, HealthInsuranceDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public HealthInsuranceDto update(Long id, HealthInsuranceCreateRequest details){
        HealthInsuranceModel existing = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Health Insurance not found"));
        modelMapper.map(details, existing);
        existing.setId(id);
        repo.save(existing);
        return modelMapper.map(existing, HealthInsuranceDto.class);
    }
}
