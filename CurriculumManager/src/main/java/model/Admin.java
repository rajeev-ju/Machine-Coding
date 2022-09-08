package model;

import lombok.NonNull;

public class Admin extends Member{
    private final MemberType memberType;
    public Admin(@NonNull String id, @NonNull String name, @NonNull MemberType memberType) {
        super(id, name, memberType);
        this.memberType = memberType;
    }
}
