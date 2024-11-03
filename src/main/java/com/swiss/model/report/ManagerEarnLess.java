package com.swiss.model.report;

import com.swiss.constants.CoreConstants;
import com.swiss.model.Employee;

import java.util.Objects;

public class ManagerEarnLess {
    private final Employee employee;
    private final double averageOfSubordinates;
    private final double deviation;

    public ManagerEarnLess(Employee employee, double averageOfSubordinates, double deviation) {
        this.employee = employee;
        this.averageOfSubordinates = averageOfSubordinates;
        this.deviation = deviation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getAverageOfSubordinates() {
        return averageOfSubordinates;
    }

    public double getDeviation() {
        return deviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerEarnLess that = (ManagerEarnLess) o;
        return Math.abs(deviation - that.deviation) < CoreConstants.EPSILON
                && Math.abs(averageOfSubordinates - that.averageOfSubordinates) < CoreConstants.EPSILON
                && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, averageOfSubordinates, deviation);
    }

    @Override
    public String toString() {
        return "ManagerEarnLess{" +
                "employee=" + employee +
                ", averageOfSubordinates=" + averageOfSubordinates +
                ", deviation=" + deviation +
                '}';
    }
}
