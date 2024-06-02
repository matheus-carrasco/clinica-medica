package br.edu.imepac.config;

import br.edu.imepac.models.DoctorModel;
import br.edu.imepac.models.HealthInsuranceModel;
import br.edu.imepac.models.SpecialtyModel;
import br.edu.imepac.repositories.DoctorRepository;
import br.edu.imepac.repositories.HealthInsurenceRepository;
import br.edu.imepac.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private HealthInsurenceRepository healthInsurenceRepository;


    @Override
    public void run(String... args) throws Exception {

        SpecialtyModel cardiologista = new SpecialtyModel(null, "cardiologista");
        SpecialtyModel ginecologista = new SpecialtyModel(null, "ginecologista");
        SpecialtyModel pediatra = new SpecialtyModel(null, "pediatra");
        SpecialtyModel oftalmologista = new SpecialtyModel(null, "oftalmologista");
        SpecialtyModel psiquiatra = new SpecialtyModel(null, "psiquiatra");
        SpecialtyModel endocrinologista = new SpecialtyModel(null, "endocrinologista");
        specialtyRepository.saveAll(Arrays.asList(  cardiologista, ginecologista, pediatra,
                                                    oftalmologista, psiquiatra, endocrinologista));

        DoctorModel alex = new DoctorModel(null, "Alex dos Santos", "102345", "F7dK9sL3!");
        DoctorModel maria = new DoctorModel(null, "Maria dos Santos", "204678", "mR8tY4n2");
        DoctorModel joao = new DoctorModel(null, "Joao Costa", "308912", "W6xB1pQ7");
        DoctorModel bruno = new DoctorModel(null, "Bruno Oliveira", "567890", "aP5wR3x8");
        DoctorModel carla = new DoctorModel(null, "Carla Pereira", "678901", "bN2yU6q4");
        DoctorModel daniel = new DoctorModel(null, "Daniel Costa", "789012", "hJ8fZ5m3");
        DoctorModel elisa = new DoctorModel(null, "Elisa Martins", "890123", "kL1oW7p9");
        doctorRepository.saveAll(Arrays.asList(alex, maria, joao, bruno, carla, daniel, elisa));

        HealthInsuranceModel unimed = new HealthInsuranceModel(null, "Unimed", "02.812.468/0001-06","(34) 3239-6941");
        HealthInsuranceModel amil = new HealthInsuranceModel(null,"Amil", "29.309.127/0001-79", "(21) 3003-1333");
        HealthInsuranceModel bradescoSaude = new HealthInsuranceModel(null, "Bradesco Saude","92.693.118/0001-60", "(11) 2184-4585");
        HealthInsuranceModel sulamerica = new HealthInsuranceModel(null,"Sul America", "33.041.062/0001-09", "(21) 2506-1010");
        HealthInsuranceModel hapvida = new HealthInsuranceModel(null,"Hap vida", "05.275.339/0001-22", "(85) 4002-3633");
        healthInsurenceRepository.saveAll(Arrays.asList(unimed, amil, bradescoSaude, sulamerica, hapvida));
    }
}
