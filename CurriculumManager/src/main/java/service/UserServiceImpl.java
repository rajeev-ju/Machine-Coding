package service;

import lombok.NonNull;
import model.*;
import repository.IMemberRepository;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private final IMemberRepository memberRepository;

    public UserServiceImpl(@NonNull IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void optCourse(Course course, Member user) {
        if(user.getMemberType().equals(MemberType.ADMIN))
            throw new RuntimeException("Admin has no access to opt the course");
        //user = (User)(user);
        for(Course course1 : ((User) user).getCompletedCourses())

            if(course1.getName().equals(course.getName()) && course1.getId().equals(course.getName())){
                //System.out.println(course.getName());
                throw new RuntimeException("User has already completed this course");
            }

        for(Course course1 : ((User) user).getInProgressCourses())
            if(course1.getName().equals(course.getName()) && course1.getId().equals(course.getName())){
                throw new RuntimeException("User has currently in progress  with this course");
            }

        boolean status = checkPrerequisite(user, course.getPreRequisites());
        if(status){
            ((User) user).getInProgressCourses().add(course);
            System.out.println("Course opted successfully");
        }
        else
            throw new RuntimeException("Before opting this course please complete these courses first : " + course.getPreRequisites().toString());
    }

    @Override
    public void addUser(Member member) {
        memberRepository.addUser(member);
    }

    @Override
    public void addAdmin(Member member) {
        memberRepository.addAdmin(member);
    }

    @Override
    public void addCompletedCourse(Course course, Member member) {
        memberRepository.addCompletedCourse(course, member);
    }

    boolean checkPrerequisite(Member user, List<Course>preRequisites){
        int count = 0;
        for(Course course : preRequisites){
            for(Course course1 : ((User) user).getCompletedCourses()){
                System.out.println(course1.getName());
                if(course1.getName().equals(course.getName()) && course1.getId().equals(course.getId())){
                    count++;
                    break;
                }
            }
        }
        return  count == preRequisites.size();
    }
}
