import lombok.NonNull;
import model.*;
import repository.CourseRepositoryImpl;
import repository.ICourseRepository;
import repository.IMemberRepository;
import repository.MemberRepositoryImpl;
import service.CourseServiceImpl;
import service.ICourseService;
import service.IUserService;
import service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CurriculumManager {
    private static final ICourseRepository courseRepository = new CourseRepositoryImpl();
    private static final IMemberRepository memberRepository = new MemberRepositoryImpl();
    private final ICourseService courseService;
    private final IUserService userService;


    public CurriculumManager(@NonNull ICourseRepository courseRepository, @NonNull IMemberRepository memberRepository) {
        courseService = new CourseServiceImpl(courseRepository);
        userService = new UserServiceImpl(memberRepository);
    }

    public static void main(String[] args) {
        CurriculumManager curriculumManager = new CurriculumManager(courseRepository, memberRepository);
        Member user1 = new User(UUID.randomUUID().toString(), "Rajeev", MemberType.USER);
        Member user2 = new Admin(UUID.randomUUID().toString(), "Mohit", MemberType.ADMIN);
        curriculumManager.userService.addUser(user1);
        curriculumManager.userService.addAdmin(user2);

        Course pythonCourse = new Course(UUID.randomUUID().toString(), "Python", new ArrayList<>());
        List<Course>prerequisites = new ArrayList<>();
        prerequisites.add(pythonCourse);

        curriculumManager.courseService.addCourse(pythonCourse, user2);
        Course dsCourse = new Course(UUID.randomUUID().toString(), "DS-Algo", prerequisites);
        curriculumManager.courseService.addCourse(dsCourse, user2);

        curriculumManager.courseService.displayCourse();
        //curriculumManager.userService.addCompletedCourse(pythonCourse, user1);
        curriculumManager.userService.optCourse(dsCourse, user1);
    }
}
