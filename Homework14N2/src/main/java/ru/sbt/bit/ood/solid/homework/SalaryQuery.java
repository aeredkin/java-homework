package ru.sbt.bit.ood.solid.homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryQuery {
    private final Connection connection;

    public SalaryQuery(Connection connection) {
        this.connection = connection;
    }

    public ResultSet execute(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        // prepare statement with sql
        try (PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, " +
                "sum(salary) as salary from employee emp left join salary_payments sp on emp.id = sp.employee_id " +
                "where emp.department_id = ? and sp.date >= ? and sp.date <= ? group by emp.id, emp.name")) {
            // inject parameters to sql
            ps.setString(1, departmentId);
            ps.setDate(2, new java.sql.Date(dateFrom.toEpochDay()));
            ps.setDate(3, new java.sql.Date(dateTo.toEpochDay()));
            // execute query and return the results
            return ps.executeQuery();
        }
    }
}
