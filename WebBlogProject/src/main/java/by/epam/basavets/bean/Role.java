package by.epam.basavets.bean;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {

    private Integer id;
    private RoleTypes roleTypes;
    private static final long serialVersionUID = 1133L;

    public Role() {
    }

    public Role(int id, RoleTypes roleTypes) {
        this.id = id;
        this.roleTypes = roleTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return id == null ? 0 : Objects.hash(id, roleTypes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Role role = (Role) obj;
        return Objects.equals(id, role.id) && Objects.equals(roleTypes, role.roleTypes);
    }

    @Override
    public String toString() {
        return "Тип пользователя  - " + roleTypes;
    }
}
