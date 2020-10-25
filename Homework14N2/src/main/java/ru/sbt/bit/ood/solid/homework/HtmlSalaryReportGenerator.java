package ru.sbt.bit.ood.solid.homework;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HtmlSalaryReportGenerator {
    public String generateHtmlSalaryReport(ResultSet results) throws SQLException {
        // create a StringBuilder holding a resulting html
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        while (results.next()) {
            // process each row of query results
            // add row start tag
            resultingHtml.append("<tr>");
            // appending employee name
            resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>");
            // appending employee salary for period
            resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>");
            // add row end tag
            resultingHtml.append("</tr>");
            // add salary to totals
            totals += results.getDouble("salary");
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml.toString();
    }
}
