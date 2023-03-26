package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static void accept(Student student) {
        System.out.println(student.toString());
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args ->{
//            generateRandomStudents(studentRepository);
//            sorting(studentRepository);

            PageRequest pageRequest = PageRequest.of(
                    0,
                    10   ,
                    Sort.by("age").ascending()
            );
            Page<Student> page = (Page<Student>) studentRepository.findAll(pageRequest);
            System.out.println(page);
        };
    }

    private static void sorting(StudentRepository studentRepository) {
        Sort firstName = Sort.by(Sort.Direction.DESC, "firstName")
                .and(Sort.by("age").descending());
        studentRepository.findAll(firstName).forEach(
                student -> System.out.println(student.getFirstName()+ " " + student.getAge())
        );
    }

    private static void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i =0;i <= 100;i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@nctr.sd",firstName,lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 60));
            studentRepository.save(student);

        }
    }

}
