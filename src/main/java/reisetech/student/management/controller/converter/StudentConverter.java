package reisetech.student.management.controller.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.domain.StudentDetail;


@Component

/**
 * 受講生と、受講生コース情報を用いて、idが一致する値を返すコンバータークラス
 */
public class StudentConverter {

    /**
     * 受講生のidと一致する、受講生コース情報を紐づける。 受講生に対して、受講生コース情報は複数存在するので、ループしてコース情報を全て取得する。
     *
     * @param students       　受講生一覧
     * @param studentCourses 　受講生コース情報のリスト
     * @return
     */

    public List<StudentDetail> converterStudentDetails(List<Student> students,
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