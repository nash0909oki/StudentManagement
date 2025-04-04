package reisetech.student.management.repository;

import java.util.List;
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
}