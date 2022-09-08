package service;

import model.Course;
import model.Member;
import model.MemberType;

public interface ICourseService {
    void addCourse(Course course, Member member);
    void deleteCourse(Course course, Member member);
    void upDateCourse(Course course, Member member);
    void displayCourse();

}
