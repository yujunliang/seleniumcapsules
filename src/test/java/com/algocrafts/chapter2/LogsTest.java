package com.algocrafts.chapter2;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import static org.openqa.selenium.WebDriver.Options;

/**
 * Created by yujunliang on 8/31/14.
 */
public class LogsTest {

    @Test
    public void getAvailableLogTypes() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://ticketfly.com");

        Options manage = webDriver.manage();
        Logs logs = manage.logs();
        Set<String> availableLogTypes = logs.getAvailableLogTypes();
        System.out.println(availableLogTypes);
        availableLogTypes.stream().forEach(logType -> {
            System.out.println("logType=" + logType);
            LogEntries logEntries = logs.get(logType);
            List<LogEntry> all = logEntries.getAll();
            all.forEach(entry -> {
                System.out.println(entry.getLevel());
                System.out.println(entry.getMessage());
                System.out.println(entry.getTimestamp());
                System.out.println(entry.toMap());
                System.out.println(entry);
            });

            List<LogEntry> filter = logEntries.filter(Level.SEVERE);
            System.out.println("After filtering");
            filter.forEach(System.out::println);
            System.out.println("Done");
        });

    }
}
