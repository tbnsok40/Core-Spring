package spring.subcore.student;

public interface StudentService {
    void join(Student student);
    Student findStudent(Long StudentId);
}
