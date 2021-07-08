package dev.chaofang.springsecurityjwt.repository;

import dev.chaofang.springsecurityjwt.model.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

    Iterable<Grade> findAllByStudentId(Long studentId);

    Optional<Grade> findByIdAndStudentId(Long id, Long studentId);
}
