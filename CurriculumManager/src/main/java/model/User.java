package model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User extends Member{
    private List<Course>completedCourses;
    private List<Course>inProgressCourses;
    public User(@NonNull String id, @NonNull String name, @NonNull MemberType memberType) {
        super(id, name, memberType);
        this.completedCourses = new ArrayList<>();
        this.inProgressCourses = new ArrayList<>();
    }

    public void addCompletedCourse(Course course){
        for(Course course1 : completedCourses)
            if(course1.getName().equals(course.getName()) && course1.getId().equals(course.getId()))
                throw new RuntimeException("This course is already completed by the user");

        this.completedCourses.add(course);
    }

    public void addCourseInProgress(Course course){
        for(Course course1 : inProgressCourses)
            if(course1.getName().equals(course.getName()) && course1.getId().equals(course.getId()))
                throw new RuntimeException("This course is already completed by the user");

        this.inProgressCourses.add(course);
    }


}
