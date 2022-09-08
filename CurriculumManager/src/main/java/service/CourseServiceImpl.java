package service;

import lombok.NonNull;
import model.Course;
import model.Member;
import model.MemberType;
import repository.ICourseRepository;
import repository.IMemberRepository;

public class CourseServiceImpl implements ICourseService {
    private final ICourseRepository courseRepository;
    //private final IMemberRepository memberRepository;

    public CourseServiceImpl(@NonNull ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void addCourse(Course course, Member member) {
        if(member.getMemberType().equals(MemberType.ADMIN))
            courseRepository.addCourse(course);
        else
            throw new RuntimeException("You don't have required access");
    }

    @Override
    public void deleteCourse(Course course, Member member) {
        if(member.getMemberType().equals(MemberType.ADMIN))
            courseRepository.deleteCourse(course);
        else
            throw new RuntimeException("You don't have required access");
    }

    @Override
    public void upDateCourse(Course course, Member member) {
        if(member.getMemberType().equals(MemberType.ADMIN))
            courseRepository.updateCourse(course);
        else
            throw new RuntimeException("You don't have required access");
    }

    @Override
    public void displayCourse() {
        courseRepository.displayCourses();
    }
}
