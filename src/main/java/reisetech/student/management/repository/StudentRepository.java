package reisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    List<Student> searchStudent();

    @Select("SELECT * FROM students_courses")
    List<StudentCourses> searchCourse();


    @Insert("INSERT INTO students(full_name,kana_name,nickname,email_address,address,age,sex,remark) "
            + "VALUES(#{fullName},#{kanaName},#{nickname},#{emailAddress},#{address},#{age},#{sex},#{remark})")
    int registerStudent(Student student);
}