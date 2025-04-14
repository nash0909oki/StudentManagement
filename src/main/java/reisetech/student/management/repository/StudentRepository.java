package reisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students WHERE is_deleted = false")
    List<Student> searchStudent();

    @Select("SELECT * FROM students_courses")
    List<StudentCourses> searchCoursesList();

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findStudentById(String id);

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