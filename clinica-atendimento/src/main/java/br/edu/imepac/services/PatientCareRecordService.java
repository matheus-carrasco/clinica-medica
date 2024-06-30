package br.edu.imepac.services;


import br.edu.imepac.dtos.administrativo.doctors.DoctorWithoutSpecialtiesDto;
import br.edu.imepac.dtos.agendamento.patients.PatientWithoutCareRecordsDto;
import br.edu.imepac.dtos.atendimento.records.PatientCareRecordCreateRequest;
import br.edu.imepac.dtos.atendimento.records.PatientCareRecordDto;
import br.edu.imepac.exceptions.ObjectNotFoundException;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import br.edu.imepac.repositories.administrativo.DoctorRepository;
import br.edu.imepac.repositories.agendamento.PatientRepository;
import br.edu.imepac.repositories.atendimento.PatientCareRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public PatientCareRecordDto update(Long id, PatientCareRecordCreateRequest details) {
        PatientCareRecordModel obj = findById(id);

        PatientModel patientModel = patientRepository.findById(details.getPatient().getId()).orElseThrow(
                () -> new ObjectNotFoundException("Patient not found"));
        PatientWithoutCareRecordsDto patientWithoutCareRecordsDto = modelMapper.map(patientModel, PatientWithoutCareRecordsDto.class);

        DoctorModel doctorModel = doctorRepository.findById(details.getDoctor().getId()).orElseThrow(
                () -> new ObjectNotFoundException("Doctor not found"));
        DoctorWithoutSpecialtiesDto doctorWithoutSpecialtiesDto = modelMapper.map(doctorModel, DoctorWithoutSpecialtiesDto.class);

        obj.setPatient(modelMapper.map(patientWithoutCareRecordsDto, PatientModel.class));
        obj.setDoctor(modelMapper.map(doctorWithoutSpecialtiesDto, DoctorModel.class));
        obj.setHistoryDescription(details.getHistoryDescription());
        obj.setPrescription(details.getPrescription());
        obj.setExamRequest(details.getExamRequest());
        obj.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant()));
        obj.setId(id);
        repo.save(obj);
        return modelMapper.map(obj, PatientCareRecordDto.class);
    }
}
