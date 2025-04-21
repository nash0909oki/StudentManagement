package reisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;

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
    @Select("SELECT * FROM students WHERE is_deleted = false")
    List<Student> searchStudent();

    /**
     * idと一致する単一の受講生を検索する。
     *
     * @param id 　受講生ID
     * @return　単一の受講生
     */
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findStudentById(String id);

    /**
     * 受講生のコース情報の全件検索を行う。
     *
     * @return　受講生コース情報の全件
     */
    @Select("SELECT * FROM students_courses")
    List<StudentCourses> searchCoursesList();

    /**
     * 受講生idと一致する、受講生コース情報を検索する。
     *
     * @param studentId 　受講生ID
     * @return 受講生IDと一致する、受講生コース情報
     */
    @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
    List<StudentCourses> findStudentCoursesById(String studentId);

    @Insert("INSERT INTO students(full_name,kana_name,nickname,email_address,address,age,sex,remark,is_deleted) "
            + "VALUES(#{fullName},#{kanaName},#{nickname},#{emailAddress},#{address},#{age},#{sex},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudent(Student student);

    @Insert("INSERT INTO students_courses(student_id,course_name,start_date,end_date) "
            + "VALUES(#{studentId},#{courseName},#{startDate},#{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void registerStudentsCourses(StudentCourses studentCourses);

    @Update("UPDATE students SET full_name = #{fullName},kana_name = #{kanaName},nickname = #{nickname},email_address = #{emailAddress},"
            + "address = #{address},age = #{age},sex = #{sex},remark = #{remark},is_deleted= #{isDeleted}  WHERE id = #{id}")
    void updateStudent(Student student);

    @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
    void updateStudentCourses(StudentCourses studentCourses);
}