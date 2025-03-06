package org.main;

import org.entities.Employee;
import org.utils.EmployeeUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        EmployeeUtils.addEmployee(employees, "Maria", "18/10/2000", 2009.44, "Operador");
        EmployeeUtils.addEmployee(employees, "João", "12/05/1990", 2284.38, "Operador");
        EmployeeUtils.addEmployee(employees, "Caio", "02/05/1961", 9836.14, "Coordenador");
        EmployeeUtils.addEmployee(employees, "Miguel", "14/10/1988", 19119.88, "Diretor");
        EmployeeUtils.addEmployee(employees, "Alice", "05/01/1995", 2234.68, "Recepcionista");
        EmployeeUtils.addEmployee(employees, "Heitor", "19/11/1999", 1582.72, "Operador");
        EmployeeUtils.addEmployee(employees, "Arthur", "31/03/1993", 4071.84, "Contador");
        EmployeeUtils.addEmployee(employees, "Laura", "08/07/1994", 3017.45, "Gerente");
        EmployeeUtils.addEmployee(employees, "Heloísa", "24/05/2003", 1606.85, "Eletricista");
        EmployeeUtils.addEmployee(employees, "Helena", "02/09/1996", 2799.93, "Gerente");

        //
        employees.removeIf(e -> e.getName().equalsIgnoreCase("João"));

        //
        EmployeeUtils.printEmployees(employees);

        //
        employees.forEach(e -> e.setSalary(e.getSalary().multiply(new BigDecimal("1.10"))));

        System.out.println("\nApós aumento de salário:");
        EmployeeUtils.printEmployees(employees);

        //
        Map<String, List<Employee>> groupedByRole = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole));

        EmployeeUtils.printEmployeesGroupedByRole(groupedByRole);

        //
        EmployeeUtils.printEmployeesWithBirthdayInOctoberOrDecember(employees);

        //
        EmployeeUtils.printOldestEmployee(employees);

        //
        EmployeeUtils.printEmployeesInAlphabeticalOrder(employees);

        //
        EmployeeUtils.printTotalSalaries(employees);

        //
        EmployeeUtils.printSalaryInMinimumWage(employees);


    }
}
