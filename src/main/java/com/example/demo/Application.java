package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            Student Maria = new Student("Maria", "john","maria@gmail.com",20);
            Student huda = new Student("Huda", "Omer","huda@gmail.com",23);
            Student omer = new Student("Omer", "Khalid", "omer@gmail.com",21);
            Student omer2 = new Student("Omer", "Mazin", "omer2@gmail.com",23);
            Student omer3 = new Student("Omer", "Mustafa", "omer3@gmail.com",22);
            Student khansa = new Student("Khansa", "Jaffar","khansa@gmail.com",30);
            Student sara = new Student("Sara", "Mustafa","Sara@gmail.com",28);
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

            Optional<Student> student = studentRepository.findStudentByEmail("omer@gmail.com");
            student.ifPresentOrElse(System.out::println,
                    ()-> System.out.println("There is no student with this email"));

            System.out.println("Find all students by first name");
            studentRepository.findAllStudentsByFirstName(
                    "Omer"
            ).forEach(
                    System.out::println
            );

            System.out.println("Find all Student By age greater than 23");
            studentRepository.findAllStudentsByAgeGreaterThanEqual(23).forEach(
                    System.out::println
            );
            System.out.println("find students by first name");
            studentRepository.findByFirstName("Omer").forEach(System.out::println);

            System.out.println("Find Student with age greater than or equal to 23");
            studentRepository.findStudentWithAgeGreaterThan(23).forEach(
                    System.out::println
            );
        };
    }

}
