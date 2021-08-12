package lesson9hw;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Student implements Comparable<Student> {
    private String name;
    private List<Course> courseList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Student(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", courseList=" + courseList.toArray().toString() +
                '}';
    }

    @Override
    public int compareTo(@NotNull Student o) {
        return o.getCourseList().size() - this.getCourseList().size();
    }
}
