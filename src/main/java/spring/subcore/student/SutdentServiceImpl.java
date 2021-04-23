package spring.subcore.student;

public class SutdentServiceImpl implements StudentService {

    private final StudentRepository studentRepository = new MemoryStudentRepository();

    @Override
    public void join(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findStudent(Long StudentId) {
        return studentRepository.findById(StudentId);
    }
}
