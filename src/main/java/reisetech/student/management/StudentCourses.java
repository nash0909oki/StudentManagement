package reisetech.student.management;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentCourses {

    public String id;
    public String studentId;
    public String courseName;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
}
