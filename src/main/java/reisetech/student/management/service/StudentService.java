package reisetech.student.management.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reisetech.student.management.controller.converter.StudentConverter;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.domain.StudentDetail;
import reisetech.student.management.repository.StudentRepository;

/**
 * 受講生情報を取り扱うサービス 受講生の検索や登録、更新処理を行う。
 */
@Service
public class StudentService {

    private StudentRepository repository;
    private StudentConverter converter;

    @Autowired
    public StudentService(StudentRepository repository, StudentConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    /**
     * 受講生と受講生コース情報一覧検索。全件検索のため、条件指定は行わない。
     *
     * @return　受講生一覧（全件）
     */

    public List<StudentDetail> searchStudentList() {
        List<Student> studentList = repository.searchStudent();
        List<StudentCourses> studentCoursesList = repository.searchCoursesList();
        return converter.converterStudentDetails(studentList, studentCoursesList);
    }

    /**
     * 受講生と受講生コース情報登録。受講生コース情報は、受講生のid、受講開始日(登録した日）、終了日(登録から一年後）も登録。
     *
     * @param studentDetail 　受講生詳細
     * @return　受講生詳細
     */

    @Transactional
    public StudentDetail insertStudent(StudentDetail studentDetail) {
        Student student = studentDetail.getStudent();
        //受講生DB登録
        repository.registerStudent(student);
        //受講生コース情報の登録
        for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
            //初期データ設定
            studentCourses.setStudentId(student.getId());
            studentCourses.setStartDate(LocalDateTime.now());
            studentCourses.setEndDate(LocalDateTime.now().plusYears(1));
            //受講生コース情報DB登録
            repository.registerStudentsCourses(studentCourses);
        }
        return studentDetail;
    }

    /**
     * 一人の受講生検索。idに紐づく任意の受講生情報を取得する。
     * その後、student_coursesテーブルから、その受講生のidと一致する受講生コース情報（コース名、受講開始日など）を取得する
     *
     * @param id 　受講生ID
     * @return　受講生単体の情報
     */
    public StudentDetail findStudent(String id) {
        Student student = repository.findStudentById(id);
        List<StudentCourses> studentCourses = repository.findStudentCoursesById(
                student.getId());
        return new StudentDetail(student, studentCourses);
    }

    /**
     * 受講生と受講生コース情報更新。受講生は一件、受講生コース情報は、リクエストのコース名の件数分更新
     *
     * @param studentDetail 　受講生詳細
     */

    @Transactional
    public void updateStudentDetail(StudentDetail studentDetail) {
        repository.updateStudent(studentDetail.getStudent());
        for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
            repository.updateStudentCourses(studentCourses);
        }
    }
}



