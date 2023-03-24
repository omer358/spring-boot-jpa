package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
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
            Student Maria = new Student("Maria", "john","maria@gmail.com",23);
            Student huda = new Student("Huda", "Omer","huda@gmail.com",23);
            String email = "omer@gmail.com";
            Student omer = new Student("Omer", "Maki", email,23);
            Student khansa = new Student("Khansa", "Jaffar","khansa@gmail.com",23);
            Student sara = new Student("Sara", "Mustafa","Sara@gmail.com",23);
            System.out.println("Saving multiple students to the database");
            studentRepository.saveAll(List.of(Maria,huda,omer,khansa,sara));

            System.out.println("find student by Id 1");
            studentRepository
                    .findById(1L)
                    .ifPresentOrElse(
                            Application::accept,
                            ()->{
                                System.out.println("Student not exists");
            });

            Optional<Student> student = studentRepository.findStudentByEmail(email);
            student.ifPresentOrElse(System.out::println,
                    ()-> System.out.println("There is no student with this email"));
        };
    }

}
