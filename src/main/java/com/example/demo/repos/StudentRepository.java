package com.example.demo.repos;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByEmail(String email);
    List<Student> findAllStudentsByFirstName(
            String firstName);

    List<Student> findAllStudentsByAgeGreaterThanEqual(
            Integer age);

    // performing native queries using postgres language.
    @Query(value = "SELECT * from student WHERE first_name LIKE :firstName",nativeQuery = true)
    List<Student> findByFirstName(@Param("firstName") String firstName);

    @Query(value = "SELECT * from student WHERE age >= :age", nativeQuery = true)
    List<Student> findStudentWithAgeGreaterThan(@Param("age") Integer age);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student u WHERE u.id =?1")
    int deleteStudentById(Long id);
}
