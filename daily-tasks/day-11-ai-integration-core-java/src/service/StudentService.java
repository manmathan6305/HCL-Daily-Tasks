package service;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.List;
import java.util.Optional;

/**
 * Service contract for student administration and academic enrollment (FR7 & FR8).
 */
public interface StudentService {
    void registerStudent(Student student);
    Optional<Student> findStudentById(String studentId);
    List<Student> getAllStudents();
    void addCourse(Course course);
    Enrollment enrollStudentInCourse(String studentId, String courseCode); // FR7
    String generateAcademicTranscript(String studentId); // FR8
    List<Student> findTopPerformers(int limit);
}
