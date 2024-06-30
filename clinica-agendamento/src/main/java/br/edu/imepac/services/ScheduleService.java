package br.edu.imepac.services;


import br.edu.imepac.dtos.agendamento.schedules.ScheduleCreateRequest;
import br.edu.imepac.dtos.agendamento.schedules.ScheduleDto;
import br.edu.imepac.exceptions.ObjectNotFoundException;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.models.agendamento.ScheduleModel;
import br.edu.imepac.repositories.administrativo.DoctorRepository;
import br.edu.imepac.repositories.administrativo.EmployeeRepository;
import br.edu.imepac.repositories.agendamento.PatientRepository;
import br.edu.imepac.repositories.agendamento.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repo;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    public ScheduleService(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<ScheduleModel> findAll(){
        return repo.findAll();
    }

    public ScheduleModel findById(Long id){
        Optional<ScheduleModel> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Schedule not found"));
    }

    public ScheduleDto insert(ScheduleCreateRequest request){
        ScheduleModel obj = verifyRequest(request, new ScheduleModel());
        ScheduleModel savedObj = repo.save(obj);
        return modelMapper.map(savedObj, ScheduleDto.class);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

    public ScheduleDto update(Long id, ScheduleCreateRequest details){
        ScheduleModel existingSchedule = findById(id);
        ScheduleModel updatedSchedule = verifyRequest(details, existingSchedule);
        repo.save(updatedSchedule);
        return modelMapper.map(updatedSchedule, ScheduleDto.class);
    }

    private ScheduleModel verifyRequest(ScheduleCreateRequest request, ScheduleModel existingSchedule){
        if (existingSchedule == null) {
            existingSchedule = modelMapper.map(request, ScheduleModel.class);
        }

        if (request.getPatient() != null && request.getPatient().getId() != null) {
            PatientModel patientModel = patientRepository.findById(request.getPatient().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Patient not found"));
            existingSchedule.setPatient(patientModel);
        }

        if (request.getDoctor() != null && request.getDoctor().getId() != null) {
            DoctorModel doctorModel = doctorRepository.findById(request.getDoctor().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Doctor not found"));
            existingSchedule.setDoctor(doctorModel);
        }

        if (request.getEmployee() != null && request.getEmployee().getId() != null) {
            EmployeeModel employeeModel = employeeRepository.findById(request.getEmployee().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
            existingSchedule.setEmployee(employeeModel);
        }

        if (request.getDate() != null) {
            existingSchedule.setDate(request.getDate());
        }
        return existingSchedule;
    }

    private ScheduleModel verifyRequest(ScheduleDto details, ScheduleModel existingSchedule){
        if (existingSchedule == null) {
            existingSchedule = modelMapper.map(details, ScheduleModel.class);
        }

        if (details.getPatient() != null && details.getPatient().getId() != null) {
            PatientModel patientModel = patientRepository.findById(details.getPatient().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Patient not found"));
            existingSchedule.setPatient(patientModel);
        }

        if (details.getDoctor() != null && details.getDoctor().getId() != null) {
            DoctorModel doctorModel = doctorRepository.findById(details.getDoctor().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Doctor not found"));
            existingSchedule.setDoctor(doctorModel);
        }

        if (details.getEmployee() != null && details.getEmployee().getId() != null) {
            EmployeeModel employeeModel = employeeRepository.findById(details.getEmployee().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Employee not found"));
            existingSchedule.setEmployee(employeeModel);
        }

        if (details.getDate() != null) {
            existingSchedule.setDate(details.getDate());
        }
        return existingSchedule;
    }
}
