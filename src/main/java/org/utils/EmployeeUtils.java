package org.utils;

import org.entities.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EmployeeUtils {

    public static void addEmployee(List<Employee> employees, String name, String birthDate, double salary, String role) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = LocalDate.parse(birthDate, formatter);
        employees.add(new Employee(name, dateOfBirth, BigDecimal.valueOf(salary), role));
    }

    public static void printEmployees(List<Employee> employees) {
        System.out.println("\nLista de funcionários:");
        employees.forEach(e -> System.out.println(e.getName() + " | " + formatDate(e.getBirthDate()) + " | " + e.getRole() + " | " + formatCurrency(e.getSalary())));
    }

    public static void printEmployeesGroupedByRole(Map<String, List<Employee>> groupedByRole) {
        System.out.println("\nFuncionários agrupados por função:");
        groupedByRole.forEach((role, list) -> {
            System.out.println("Função: " + role);
            list.forEach(e -> System.out.println("  - " + e.getName()));
        });
    }

    public static void printEmployeesWithBirthdayInOctoberOrDecember(List<Employee> employees) {
        System.out.println("\nFuncionários que fazem aniversário em outubro ou dezembro:");
        employees.stream().filter(e -> e.getBirthDate().getMonthValue() == 10 || e.getBirthDate().getMonthValue() == 12).forEach(e -> System.out.println(e.getName() + " - " + formatDate(e.getBirthDate())));
    }

    public static void printOldestEmployee(List<Employee> employees) {
        Employee oldestEmployee = employees.stream().min(Comparator.comparing(Employee::getBirthDate)).orElse(null);

        if (oldestEmployee != null) {
            System.out.println("\nFuncionário com maior idade: " + oldestEmployee.getName() + " - Idade: " + getAge(oldestEmployee.getBirthDate()));
        }
    }

    public static void printEmployeesInAlphabeticalOrder(List<Employee> employees) {
        System.out.println("\nFuncionários em ordem alfabética:");
        employees.stream().sorted(Comparator.comparing(Employee::getName)).forEach(e -> System.out.println(e.getName()));
    }

    public static void printTotalSalaries(List<Employee> employees) {
        BigDecimal totalSalaries = employees.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + formatCurrency(totalSalaries));
    }

    public static void printSalaryInMinimumWage(List<Employee> employees) {
        final BigDecimal MINIMUM_WAGE = BigDecimal.valueOf(1212.00);
        System.out.println("\nSalários em relação ao salário mínimo:");
        employees.forEach(e -> {
            BigDecimal salaryInMinWage = e.getSalary().divide(MINIMUM_WAGE, 2, RoundingMode.HALF_UP);
            System.out.println(e.getName() + " ganha " + salaryInMinWage + " salários mínimos.");
        });
    }


    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public static String formatCurrency(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return format.format(value);
    }

    public static int getAge(LocalDate birthDate) {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

}
