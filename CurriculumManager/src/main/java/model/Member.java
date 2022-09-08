package model;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Member {
    private final String id;
    private final String name;
    private final MemberType memberType;

    public Member(@NonNull String id, @NonNull String name, @NonNull MemberType memberType) {
        this.id = id;
        this.name = name;
        this.memberType = memberType;
    }
}
