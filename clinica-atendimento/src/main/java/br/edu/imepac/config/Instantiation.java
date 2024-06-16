package br.edu.imepac.config;

import br.edu.imepac.models.PatientCareRecordModel;
import br.edu.imepac.repositories.PatientCareRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PatientCareRecordRepository patientCareRecordRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));


        PatientCareRecordModel p1 = new PatientCareRecordModel(null, "Patient with several pain around his neck", "1 pill of muscle relaxin", "X-ray around his head and thorax");
        PatientCareRecordModel p2 = new PatientCareRecordModel(null, "Patient with high fever and headache", "2 pills of paracetamol", "Blood test and MRI of the head");
        PatientCareRecordModel p3 = new PatientCareRecordModel(null, "Patient with persistent cough", "Cough syrup and antibiotics", "Chest X-ray");
        PatientCareRecordModel p4 = new PatientCareRecordModel(null, "Patient with severe abdominal pain", "Painkillers and antacids", "Abdominal ultrasound");
        PatientCareRecordModel p5 = new PatientCareRecordModel(null, "Patient with a broken arm", "Painkillers and cast", "X-ray of the arm");
        patientCareRecordRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

    }
}
