package repository;

import lombok.NonNull;
import model.*;

import java.util.HashMap;
import java.util.Map;

public class MemberRepositoryImpl implements IMemberRepository {

    private Map<String, Member> memberMap;

    public MemberRepositoryImpl() {
        this.memberMap = new HashMap<>();
    }

    @Override
    public void addUser(Member user) {
        if(memberMap.containsKey(user.getId()))
            throw new RuntimeException("This member already exists");
        memberMap.put(user.getId(), user);
    }

    @Override
    public void addAdmin(Member admin) {
        if(memberMap.containsKey(admin.getId()))
            throw new RuntimeException("This member already exists");
        memberMap.put(admin.getId(), admin);
    }

    @Override
    public void addCompletedCourse(Course course, Member member) {
        for(Course course1 : ((User) member).getCompletedCourses())
            if(course.getName().equals(course1.getName()) && course.getId().equals(course1.getId()))
                throw new RuntimeException("Already added in the completed list");

        ((User) member).getCompletedCourses().add(course);
        System.out.println("This course is completed by the user");
    }

    @Override
    public MemberType checkMemberType(Member member) {
        return member.getMemberType();
    }
}
