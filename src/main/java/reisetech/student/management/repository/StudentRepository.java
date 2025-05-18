package reisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルに対してSQLを実行するRepository。
 */


@Mapper
public interface StudentRepository {

    /**
     * 論理削除されている受講生以外の受講生の全件検索を行う。
     *
     * @return 受講生一覧（論理削除されている受講生以外）
     */
    List<Student> searchStudent();

    /**
     * 受講生のコース情報の全件検索を行う。
     *
     * @return　受講生コース情報の全件
     */
    List<StudentCourse> searchStudentCourseList();

    /**
     * idと一致する単一の受講生を検索する
     *
     * @param id 　受講生ID
     * @return　単一の受講生
     */
    Student findStudentById(String id);


    /**
     * 受講生idと一致する、受講生コース情報を検索する。
     *
     * @param studentId 　受講生ID
     * @return 受講生IDと一致する、受講生コース情報
     */

    List<StudentCourse> findStudentCourseById(String studentId);

    /**
     * 受講生の新規登録。 IDはDB側で自動採番を行っている。
     *
     * @param student 　受講生
     */
    void registerStudent(Student student);

    /**
     * 受講生コース情報の新規登録 IDはDB側で自動採番を行っている。
     *
     * @param studentCourse 　受講生コース情報
     */
    void registerStudentCourse(StudentCourse studentCourse);

    /**
     * 受講生更新
     *
     * @param student 　受講生
     */
    int updateStudent(Student student);

    /**
     * 受講生コース情報のコース名更新
     *
     * @param studentCourse 　受講生コース情報
     */
    void updateStudentCourse(StudentCourse studentCourse);
}