package repository;

import model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements ICourseRepository {
    private List<Course>courses;

    public CourseRepositoryImpl() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void displayCourses() {
        System.out.println(courses.size());
        for(Course course : this.courses) {
            System.out.println("Course Name : " + course.getName());
        }
    }

    @Override
    public void addCourse(Course course) {
        for(Course course1 : this.courses)
            if(course1.getId().equals(course.getId()) && course1.getName().equals(course.getName()))
                throw new RuntimeException("This course is alredy present in the course list");

        this.courses.add(course);
        System.out.println(course.getName() + " added successfully");
    }

    @Override
    public void deleteCourse(Course course) {
        for(Course course1 : this.courses)
            if(course1.getId().equals(course.getId()) && course1.getName().equals(course.getName())){
                this.courses.remove(course);
                return;
            }

        throw new RuntimeException(course.getName() + " doesn't exist in the course list. Hence it can't be deleted");
    }

    @Override
    public void updateCourse(Course course) {
        for(Course course1 : this.courses)
            if(course1.getId().equals(course.getId()) && course1.getName().equals(course.getName())){
                System.out.println("course updated");
                return;
            }

        throw new RuntimeException(course.getName() + " doesn't exist in the course list. Hence it can't be updated");
    }
}
