package dev.chaofang.springsecurityjwt.service;

import dev.chaofang.springsecurityjwt.exception.ResourceNotFoundException;
import dev.chaofang.springsecurityjwt.model.Grade;
import dev.chaofang.springsecurityjwt.model.Student;
import dev.chaofang.springsecurityjwt.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;

    public Iterable<Grade> getAllByStudentId(Long studentId) {
        return this.gradeRepository.findAllByStudentId(studentId);
    }

    public Grade addGrade(Long studentId, Grade grade) {
        grade.setStudent(Student.builder().id(studentId).build());
        return this.gradeRepository.save(grade);
    }

    public Grade updateGrade(Long studentId, Long gradeId, Grade grade) {
        grade.setId(gradeId);
        grade.setStudent(Student.builder().id(studentId).build());
        return this.gradeRepository.save(grade);
    }

    public Grade deleteGrade(Long studentId, Long gradeId) {
        var toBeDeleted = this.gradeRepository.findByIdAndStudentId(gradeId, studentId);
        if (toBeDeleted.isPresent()) {
            this.gradeRepository.deleteById(gradeId);
            return toBeDeleted.get();
        }
        throw new ResourceNotFoundException();
    }

    public Grade getByIdAndStudentId(Long gradeId, Long studentId) {
        return this.gradeRepository.findByIdAndStudentId(gradeId, studentId).orElseThrow(RuntimeException::new);
    }
}
