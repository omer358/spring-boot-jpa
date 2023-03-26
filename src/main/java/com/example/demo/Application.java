package com.example.demo;

import com.example.demo.models.Student;
import com.example.demo.models.StudentIdCard;
import com.example.demo.repos.StudentIdCardRepository;
import com.example.demo.repos.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static void accept(Student student) {
        System.out.println(student.toString());
    }

    private static void createNewStudentIdCard(StudentIdCardRepository studentIdCardRepository) {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@nctr.sd", firstName, lastName);
        Student student = new Student(
                firstName,
                lastName,
                email,
                faker.number().numberBetween(17, 60));

        StudentIdCard studentIdCard = new StudentIdCard(
                "123456789", student
        );
        studentIdCardRepository.save(studentIdCard);
    }

    private static void sorting(StudentRepository studentRepository) {
        Sort firstName = Sort.by(Sort.Direction.DESC, "firstName")
                .and(Sort.by("age").descending());
        studentRepository.findAll(firstName).forEach(
                student -> System.out.println(student.getFirstName() + " " + student.getAge())
        );
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            studentRepository.findById(109L).ifPresent(System.out::println);
            studentIdCardRepository.findById(1L)
                    .ifPresent(System.out::println);
        };
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
