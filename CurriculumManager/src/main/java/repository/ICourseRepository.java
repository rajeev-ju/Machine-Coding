package repository;

import model.Course;

public interface ICourseRepository {
    void displayCourses();
    void addCourse(Course course);
    void deleteCourse(Course course);
    void updateCourse(Course course);
}
