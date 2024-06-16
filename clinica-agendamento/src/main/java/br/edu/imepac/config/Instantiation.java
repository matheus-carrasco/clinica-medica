package br.edu.imepac.config;

import br.edu.imepac.models.PatientModel;
import br.edu.imepac.models.ScheduleModel;
import br.edu.imepac.repositories.PatientRepository;
import br.edu.imepac.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT"));


        PatientModel ana = new PatientModel(null, "20.123-456", "123.456.789-01", "Rua Exemplo, nº 100", "Belo Horizonte", "Minas Gerais","(31) 98765-4321", sdf.parse("15/05/1985"), "Feminino", true, null);

        PatientModel joao = new PatientModel(null, "30.654-321", "234.567.890-12", "Avenida Central, nº 200", "Uberlândia", "Minas Gerais","(34) 99876-5432", sdf.parse("22/08/1972"), "Masculino", false, null);

        PatientModel maria = new PatientModel(null, "40.987-654", "345.678.901-23", "Rua dos Alfeneiros, nº 4", "Araguari", "Minas Gerais","(34) 91234-5678", sdf.parse("10/01/1990"), "Feminino", true, null);

        PatientModel carlos = new PatientModel(null, "50.321-987", "456.789.012-34", "Praça das Flores, nº 50", "Belo Horizonte", "Minas Gerais","(31) 92345-6789", sdf.parse("05/11/1980"), "Masculino", false, null);

        PatientModel julia = new PatientModel(null, "60.654-123", "567.890.123-45", "Rua dos Girassóis, nº 70", "Uberlândia", "Minas Gerais","(34) 93456-7890", sdf.parse("18/03/1995"), "Feminino", true, null);

        PatientModel pedro = new PatientModel(null, "70.789-456", "678.901.234-56", "Avenida dos Coqueiros, nº 90", "Araguari", "Minas Gerais","(34) 94567-8901", sdf.parse("09/09/1988"), "Masculino", false, null);
        patientRepository.saveAll(Arrays.asList(ana, joao, maria, carlos, julia, pedro));

        ScheduleModel schedule1 = new ScheduleModel(null, 1L, 2L, sdf2.parse("16/06/2024 08:00"));
        ScheduleModel schedule2 = new ScheduleModel(null, 3L, 4L, sdf2.parse("17/06/2024 09:00"));
        ScheduleModel schedule3 = new ScheduleModel(null, 5L, 6L, sdf2.parse("18/06/2024 10:00"));
        ScheduleModel schedule4 = new ScheduleModel(null, 7L, 8L, sdf2.parse("19/06/2024 11:00"));
        ScheduleModel schedule5 = new ScheduleModel(null, 9L, 10L, sdf2.parse("20/06/2024 12:00"));

        scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2, schedule3, schedule4, schedule5));
    }
}
