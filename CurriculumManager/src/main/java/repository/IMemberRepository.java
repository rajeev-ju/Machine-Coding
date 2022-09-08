package repository;

import lombok.NonNull;
import model.*;

import java.util.List;
import java.util.Map;

public interface IMemberRepository {

    void addUser(Member member);
    void addAdmin(Member member);
    void addCompletedCourse(Course course, Member member);

    MemberType checkMemberType(Member member);
}
