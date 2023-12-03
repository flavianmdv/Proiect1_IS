package model;

import java.util.List;
import java.util.stream.Collectors;

public class Role {
    private Long id;
    private String role;
    private List<Right> rights;

    public Role(Long id, String role, List<Right> rights) {
        this.id = id;
        this.role = role;
        this.rights = rights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    public List<Right> collect() {
        return rights.stream().collect(Collectors.toList());
    }
}
