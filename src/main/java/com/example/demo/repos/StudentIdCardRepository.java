package com.example.demo.repos;

import com.example.demo.models.StudentIdCard;
import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepository extends CrudRepository<StudentIdCard,Long> {
}
