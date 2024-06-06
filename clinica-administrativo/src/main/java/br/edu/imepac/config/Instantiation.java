package br.edu.imepac.config;

import br.edu.imepac.models.DoctorModel;
import br.edu.imepac.models.EmployeeModel;
import br.edu.imepac.models.HealthInsuranceModel;
import br.edu.imepac.models.SpecialtyModel;
import br.edu.imepac.repositories.DoctorRepository;
import br.edu.imepac.repositories.EmployeeRepository;
import br.edu.imepac.repositories.HealthInsurenceRepository;
import br.edu.imepac.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private HealthInsurenceRepository healthInsurenceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        SpecialtyModel cardiologista = new SpecialtyModel(null, "cardiologista");
        SpecialtyModel ginecologista = new SpecialtyModel(null, "ginecologista");
        SpecialtyModel pediatra = new SpecialtyModel(null, "pediatra");
        SpecialtyModel oftalmologista = new SpecialtyModel(null, "oftalmologista");
        SpecialtyModel psiquiatra = new SpecialtyModel(null, "psiquiatra");
        specialtyRepository.saveAll(Arrays.asList(  cardiologista, ginecologista, pediatra, oftalmologista, psiquiatra));

        DoctorModel alex = new DoctorModel(null, "Alex dos Santos", "102345", "F7dK9sL3!", cardiologista);
        DoctorModel maria = new DoctorModel(null, "Maria dos Santos", "204678", "mR8tY4n2", ginecologista);
        DoctorModel joao = new DoctorModel(null, "Joao Costa", "308912", "W6xB1pQ7",pediatra);
        DoctorModel bruno = new DoctorModel(null, "Bruno Oliveira", "567890", "aP5wR3x8",oftalmologista);
        DoctorModel carla = new DoctorModel(null, "Carla Pereira", "678901", "bN2yU6q4",psiquiatra);
        doctorRepository.saveAll(Arrays.asList(alex, maria, joao, bruno, carla));

        cardiologista.setDoctors(Arrays.asList(alex));
        ginecologista.setDoctors(Arrays.asList(maria));
        pediatra.setDoctors(Arrays.asList(joao));
        oftalmologista.setDoctors(Arrays.asList(bruno));
        psiquiatra.setDoctors(Arrays.asList(carla));
        specialtyRepository.saveAll(Arrays.asList(  cardiologista, ginecologista, pediatra, oftalmologista, psiquiatra));

        HealthInsuranceModel unimed = new HealthInsuranceModel(null, "Unimed", "02.812.468/0001-06","(34) 3239-6941");
        HealthInsuranceModel amil = new HealthInsuranceModel(null,"Amil", "29.309.127/0001-79", "(21) 3003-1333");
        HealthInsuranceModel bradescoSaude = new HealthInsuranceModel(null, "Bradesco Saude","92.693.118/0001-60", "(11) 2184-4585");
        HealthInsuranceModel sulamerica = new HealthInsuranceModel(null,"Sul America", "33.041.062/0001-09", "(21) 2506-1010");
        HealthInsuranceModel hapvida = new HealthInsuranceModel(null,"Hap vida", "05.275.339/0001-22", "(85) 4002-3633");
        healthInsurenceRepository.saveAll(Arrays.asList(unimed, amil, bradescoSaude, sulamerica, hapvida));

        EmployeeModel lucas = new EmployeeModel(null, "Lucas de Melo", "18.664.873", "113.847.526-25", "Rua Nepthali Vieira 179", "Araguari", "MG", "34992748182", "3827164-0295", "28499950546-5", sdf.parse("30/12/1998"));
        EmployeeModel pedro = new EmployeeModel(null, "Pedro Souza", "21.345.678", "999.888.777-66", "Rua Central 789", "Belo Horizonte", "MG", "31976543210", "5063982-2718", "98765432101-3", sdf.parse("03/07/1980"));
        EmployeeModel ana = new EmployeeModel(null, "Ana Lima", "22.987.654", "777.888.999-00", "Rua da Praia 45", "Florianópolis", "SC", "48987654321", "2908475-3829", "19283746501-4", sdf.parse("15/11/1975"));
        EmployeeModel carlos = new EmployeeModel(null, "Carlos Oliveira", "23.123.987", "333.222.111-00", "Avenida das Palmeiras 789", "Salvador", "BA", "71987654321", "2938475-9283", "29384756123-5", sdf.parse("22/08/1982"));
        EmployeeModel julia = new EmployeeModel(null, "Júlia Santos", "24.345.678", "888.777.666-55", "Rua das Flores 17", "Curitiba", "PR", "41987654321", "3847562-9384", "84930284756-6", sdf.parse("18/04/1993"));
        employeeRepository.saveAll(Arrays.asList(lucas, pedro, ana, carlos, julia));
    }
}
