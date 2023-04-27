package ma.emsi.patientmvc;

import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args ->{
            patientRepository.save(new Patient(null,"hassan",new Date(),true,23));
            patientRepository.save(new Patient(null,"amine",new Date(),false,333));
            patientRepository.save(new Patient(null,"moha",new Date(),false,253));

        };
    }
    @Bean
    PasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
