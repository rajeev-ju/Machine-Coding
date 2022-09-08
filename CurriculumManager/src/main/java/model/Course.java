package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Course {
    private final String id;
    private final String name;
    private List<Course>preRequisites;

    public Course(String id, String name, List<Course>preRequisites) {
        this.id = id;
        this.name = name;
        this.preRequisites = preRequisites;
    }
}
