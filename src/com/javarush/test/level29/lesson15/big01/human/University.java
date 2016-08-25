package com.javarush.test.level29.lesson15.big01.human;

import java.util.List;
public class University {
    private List<Student> students;
    private String name;
    private int age;
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public University(String name, int age) {
        this.name=name;
        this.age=age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student:students){
            if (Double.compare(student.getAverageGrade(),averageGrade)==0){
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student studentTemp=students.get(0);
        for (Student student:students){
            if (Double.compare(student.getAverageGrade(),studentTemp.getAverageGrade())>0){
                studentTemp=student;
            }
        }
        return studentTemp;
    }

    public Student getStudentWithMinAverageGrade() {
        Student studentTemp=students.get(0);
        for (Student student:students){
            if (Double.compare(student.getAverageGrade(),studentTemp.getAverageGrade())<0){
                studentTemp=student;
            }
        }
        return studentTemp;
    }
    public void expel(Student student){
        students.remove(student);
    }
}
