package by.epam.basavets.bean;

import by.epam.basavets.bean.Enum.RoleTypes;

public class Role {

    private int id;
    private RoleTypes roleTypes;

    public Role(){}

    public Role(int id, RoleTypes roleTypes) {
        this.id = id;
        this.roleTypes = roleTypes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleTypes getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(RoleTypes roleTypes) {
        this.roleTypes = roleTypes;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
