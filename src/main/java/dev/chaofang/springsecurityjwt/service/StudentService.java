package dev.chaofang.springsecurityjwt.service;

import dev.chaofang.springsecurityjwt.exception.ResourceNotFoundException;
import dev.chaofang.springsecurityjwt.model.Student;
import dev.chaofang.springsecurityjwt.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Iterable<Student> getAll() {
        return this.studentRepository.findAll();
    }

    public Student getById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Student deleteStudent(Long id) {
        var toBeDeleted = this.studentRepository.findById(id);
        if (toBeDeleted.isPresent()) {
            this.studentRepository.deleteById(id);
            return toBeDeleted.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Student updateStudent(Long id, Student student) {
        var existing = this.studentRepository.findById(id);
        if (existing.isPresent()) {
            var toBeUpdated = existing.get();
            toBeUpdated.setName(student.getName());
            toBeUpdated.setSurname(student.getSurname());
            toBeUpdated.setClassRoom(student.getClassRoom());
            toBeUpdated.setBirthDate(student.getBirthDate());
            return studentRepository.save(toBeUpdated);
        } else {
            throw new ResourceNotFoundException();
        }

    }
}
