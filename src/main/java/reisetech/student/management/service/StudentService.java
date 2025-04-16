package reisetech.student.management.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.domain.StudentDetail;
import reisetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> searchStudentList() {
        return repository.searchStudent();
    }

    public List<StudentCourses> searchStudentCoursesList() {
        return repository.searchCoursesList();
    }

    @Transactional
    public void insertStudent(StudentDetail studentDetail) {
        repository.registerStudent(studentDetail.getStudent());
        for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
            studentCourses.setStudentId(studentDetail.getStudent().getId());
            studentCourses.setStartDate(LocalDateTime.now());
            studentCourses.setEndDate(LocalDateTime.now().plusYears(1));
            repository.registerStudentsCourses(studentCourses);
        }
    }

    public StudentDetail test(String id) {
        StudentDetail studentDetail = new StudentDetail();
        Student student = repository.findStudentById(id);
        studentDetail.setStudent(student);
        return studentDetail;
    }

    public StudentDetail findStudent(String id) {
        Student student = repository.findStudentById(id);
        List<StudentCourses> studentCourses = repository.findStudentCoursesById(
                student.getId());
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        studentDetail.setStudentCourses(studentCourses);
        return studentDetail;
    }

    @Transactional
    public void updateStudentDetail(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
            repository.updateStudentCourses(studentCourses);
        }
    }
}



