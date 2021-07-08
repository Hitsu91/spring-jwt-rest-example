package dev.chaofang.springsecurityjwt.controller;

import dev.chaofang.springsecurityjwt.model.Grade;
import dev.chaofang.springsecurityjwt.model.Student;
import dev.chaofang.springsecurityjwt.service.GradeService;
import dev.chaofang.springsecurityjwt.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final GradeService gradeService;

    @GetMapping
    public Iterable<Student> getAll() {
        return this.studentService.getAll();
    }

    @GetMapping("{id}")
    public Student getById(@PathVariable Long id) {
        return this.studentService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.studentService.addStudent(student));
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentService.updateStudent(id, student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.deleteStudent(id));
    }

    @GetMapping("{studentId}/grade")
    public Iterable<Grade> gradesByStudentId(@PathVariable Long studentId) {
        return this.gradeService.getAllByStudentId(studentId);
    }

    @GetMapping("{studentId}/grade/{gradeId}")
    public Grade gradeByStudentId(@PathVariable Long studentId, @PathVariable Long gradeId) {
        return this.gradeService.getByIdAndStudentId(gradeId, studentId);
    }

    @PostMapping("{studentId}/grade")
    public Grade addGrade(@PathVariable Long studentId, @RequestBody Grade grade) {
        return this.gradeService.addGrade(studentId, grade);
    }

    @PutMapping("{studentId}/grade/{gradeId}")
    public Grade updateGrade(@PathVariable Long studentId, @PathVariable Long gradeId, @RequestBody Grade grade) {
        return this.gradeService.updateGrade(studentId, gradeId, grade);
    }

    @DeleteMapping("{studentId}/grade/{gradeId}")
    public Grade deleteGrade(@PathVariable Long studentId, @PathVariable Long gradeId) {
        return this.gradeService.deleteGrade(studentId, gradeId);
    }

}
