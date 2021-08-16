package lesson9hw;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Тестовые данные
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Alex", Arrays.asList(new Course("Maths"), new Course("English"), new Course("Physics"))));
        studentList.add(new Student("Ivan", Arrays.asList(new Course("Biology"), new Course("Maths"), new Course("Physics"))));
        studentList.add(new Student("Oleg", Arrays.asList(new Course("Management"), new Course("English"), new Course("Economics"), new Course("Maths"))));
        studentList.add(new Student("Anton", Arrays.asList(new Course("Physics"), new Course("IT"))));
        studentList.add(new Student("Boris", Arrays.asList(new Course("English"), new Course("Biology"), new Course("IT"), new Course("Physics"), new Course("Economics"))));

        Course testCourse = new Course("English");
        // Методы
        System.out.println("Уникальные курсы: " + getUniqueCourses(studentList));
        System.out.println("3 самых любознательных студента: " + getMostCuriousStudents(studentList));
        System.out.println("Студенты, посещающие " + testCourse.getName() + ": " + getStudentsByCourse(studentList, testCourse));
    }

    public static Set<String> getUniqueCourses(List<Student> studentList) {
        return studentList.stream()
                .map(student -> student.getCourseList())
                .flatMap(courseList -> courseList.stream())
                .map(course -> course.getName())
                .collect(Collectors.toSet());
    }

    public static List<String> getMostCuriousStudents(List<Student> studentList) {
        return studentList.stream()
                .sorted()
                .limit(3)
                .map(student -> student.getName())
                .collect(Collectors.toList());
    }

    public static List<String> getStudentsByCourse(List<Student> studentList, Course course) {
        return studentList.stream()
                .filter(student -> student.getCourseList().toString().contains(course.getName()))
                .map(student -> student.getName())
                .collect(Collectors.toList());
    }
}
