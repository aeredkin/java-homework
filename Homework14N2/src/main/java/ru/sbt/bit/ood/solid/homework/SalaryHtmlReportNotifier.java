package ru.sbt.bit.ood.solid.homework;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {
    private final Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            SalaryQuery query = new SalaryQuery(connection);
            // execute query and get the results
            ResultSet results = query.execute(departmentId, dateFrom, dateTo);
            HtmlSalaryReportGenerator reportGenerator = new HtmlSalaryReportGenerator();
            String report = reportGenerator.generateHtmlSalaryReport(results);
            // now when the report is built we need to send it to the recipients list
            MailSender mailSender = new MailSender();
            // we're going to use google mail to send this message
            mailSender.send("mail.google.com", recipients, report);
        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
