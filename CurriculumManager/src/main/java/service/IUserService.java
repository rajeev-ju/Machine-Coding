package service;

import lombok.NonNull;
import model.*;

import javax.jws.soap.SOAPBinding;

public interface IUserService {
    void optCourse(Course course, Member member);
    void addUser(Member member);
    void addAdmin(Member member);
    void addCompletedCourse(Course course, Member member);
}
