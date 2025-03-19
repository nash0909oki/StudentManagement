package reisetech.student.management.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService (StudentRepository repository){
        this.repository=repository;
    }

    public List<Student> searchStudentList(){
        List<Student> studentAll = repository.searchStudent();
        List<Student> studentOver30 = new ArrayList<>();
        for (int i = 0; i<studentAll.size();i++){
            if (studentAll.get(i).getAge()>=30){
                studentOver30.add(studentAll.get(i));
            }
        }
        return studentOver30;
    }

    public List<StudentCourses> searchStudentCoursesList(){
        List<StudentCourses> studentCoursesAll = repository.searchCourse();
        List<StudentCourses> studentCoursesJava = new ArrayList<>();
        for (int i = 0; i < studentCoursesAll.size();i++){
            if (studentCoursesAll.get(i).getCourseName().equals("Java")){
                studentCoursesJava.add(studentCoursesAll.get(i));
            }
        }
        return studentCoursesJava;
    }
}



