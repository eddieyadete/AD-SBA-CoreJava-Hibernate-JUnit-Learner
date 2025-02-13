package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sba.sms.models.Student;



import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentServiceTest {
    private StudentService studentService;

    @BeforeAll
    void setup() {
        studentService = new StudentService();
    }

    @Test
    void testCreateAndFetchStudent() {
        Student student = new Student("test@example.com", "Test User", "testpass");
        studentService.createStudent(student);
        Student fetched = studentService.getStudentByEmail("test@example.com");
        assertNotNull(fetched);
        assertEquals("Test User", fetched.getName());
    }

    @Test
    void testValidateStudent() {
        assertTrue(studentService.validateStudent("john@example.com", "password123"));
        assertFalse(studentService.validateStudent("invalid@example.com", "wrongpass"));
    }
}