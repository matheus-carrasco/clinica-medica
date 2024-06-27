package br.edu.imepac.services;

import br.edu.imepac.dtos.records.PatientCareRecordCreateRequest;
import br.edu.imepac.dtos.records.PatientCareRecordDto;
import br.edu.imepac.dtos.schedules.ScheduleDto;
import br.edu.imepac.dtos.specialty.SpecialtyDto;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.models.agendamento.ScheduleModel;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import br.edu.imepac.repositories.DoctorRepository;
import br.edu.imepac.repositories.PatientCareRecordRepository;
import br.edu.imepac.repositories.PatientRepository;
import br.edu.imepac.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientCareRecordService {

    @Autowired
    private PatientCareRecordRepository repo;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    public PatientCareRecordService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<PatientCareRecordDto> findAll() {
        List<PatientCareRecordModel> list = repo.findAll();
        return list.stream().map(PatientCareRecordDto::new).collect(Collectors.toList());
    }

    public PatientCareRecordModel findById(Long id) {
        Optional<PatientCareRecordModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Record not found"));
    }

    public PatientCareRecordDto insert(PatientCareRecordCreateRequest request) {
        PatientCareRecordModel savedObj = modelMapper.map(request, PatientCareRecordModel.class);
        PatientModel patientModel = patientRepository.findById(request.getPatient().getId()).orElseThrow(
                () -> new ObjectNotFoundException("Patient not found"));
        DoctorModel doctorModel = doctorRepository.findById(request.getDoctor().getId()).orElseThrow(
                () -> new ObjectNotFoundException("Doctor not found"));
        savedObj.setPatient(patientModel);
        savedObj.setDoctor(doctorModel);
        savedObj.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant()));
        repo.save(savedObj);
        return modelMapper.map(savedObj, PatientCareRecordDto.class);
    }

    public void delete(Long id) {
        findById(id);
        repo.deleteById(id);
    }

    public PatientCareRecordDto update(Long id, PatientCareRecordDto details) {
        findById(id);
        PatientCareRecordModel obj = repo.getReferenceById(id);
        PatientCareRecordModel updated = modelMapper.map(details, PatientCareRecordModel.class);
        updated.setId(obj.getId());
        repo.save(updated);
        return modelMapper.map(updated, PatientCareRecordDto.class);
    }
}
