package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    String name;
    int age;

    @XmlElement
    Faculty faculty;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    String[] subjects;
    boolean exams;

    public Student() {
    }

    public Student(String name, int age, String[] subjects, boolean exams, Faculty faculty) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
        this.exams = exams;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age=" + age + ", subjects="
                + Arrays.toString(subjects) + ", exams=" + exams + '}';
    }

    public static void main(String[] args) throws JAXBException {
        String[] subjects = {"Russian language", "Astronomy", "History"};
        Student student = new Student("Stepan", 19, subjects, false,
                new Faculty("History", 5));
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Student result = (Student) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
