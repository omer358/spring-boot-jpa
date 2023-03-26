package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByEmail(String email);
    List<Student> findAllStudentsByFirstName(
            String firstName);

    List<Student> findAllStudentsByAgeGreaterThanEqual(
            Integer age);

    // performing native queries using postgres language.
    @Query(value = "SELECT * from student WHERE first_name LIKE ?1",nativeQuery = true)
    List<Student> findByFirstName(String firstName);
}
