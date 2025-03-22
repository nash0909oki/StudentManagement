package reisetech.student.management.controller.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.domain.StudentDetail;

@Component
public class StudentConverter {

    public List<StudentDetail> getStudentDetails(List<Student> students,
            List<StudentCourses> studentCourses) {
        List<StudentDetail> studentDetails = new ArrayList<>();
        for (Student student : students) {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);
            List<StudentCourses> convertStudentCourses = new ArrayList<>();

            for (StudentCourses studentCourse : studentCourses) {
                if (student.getId().equals(studentCourse.getStudentId())) {
                    convertStudentCourses.add(studentCourse);
                }
            }
            studentDetail.setStudentCourses(convertStudentCourses);
            studentDetails.add(studentDetail);
        }
        return studentDetails;
    }
}