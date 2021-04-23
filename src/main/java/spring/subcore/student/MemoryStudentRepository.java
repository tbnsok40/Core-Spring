package spring.subcore.student;

import java.util.HashMap;

public class MemoryStudentRepository implements StudentRepository {

    private static HashMap<Long, Student> store = new HashMap<>();

    @Override
    public void save(Student student) {
        store.put(student.getId(), student);
    }

    @Override
    public Student findById(Long studentId) {
        return store.get(studentId);
    }
}
