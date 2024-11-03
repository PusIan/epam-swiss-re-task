package com.swiss.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrganizationTree {
    private final Employee ceo;
    private final Map<Employee, List<Employee>> tree;

    public OrganizationTree(Employee ceo, Map<Employee, List<Employee>> tree) {
        this.ceo = ceo;
        this.tree = tree;
    }

    public Employee getCeo() {
        return ceo;
    }

    @Override
    public String toString() {
        return "OrganizationTree{" +
                "ceo=" + ceo +
                ", tree=" + tree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationTree that = (OrganizationTree) o;
        return Objects.equals(getCeo(), that.getCeo()) && Objects.equals(getTree(), that.getTree());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCeo(), getTree());
    }

    public Map<Employee, List<Employee>> getTree() {
        return Collections.unmodifiableMap(tree);
    }
}
