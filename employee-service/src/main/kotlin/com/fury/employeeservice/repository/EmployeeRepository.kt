package com.fury.employeeservice.repository

import Employees
import org.springframework.stereotype.Repository

@Repository
class EmployeeRepository {
    private val employees: MutableList<Employees> = mutableListOf()

    fun createEmployee(employee: Employees): Employees {
        employees.add(employee)
        return employee
    }

    fun getEmployees(): List<Employees> {
        return employees
    }

    fun getEmployeeById(id: Long): Employees? {
        return employees.find { it.id == id }
    }

    fun deleteEmployeesById(id: Long) {
        employees.removeIf { it.id == id }
    }

    fun getEmployeesByDepartmentId(departmentId: Long): List<Employees> {
        return employees.filter { it.departmentId == departmentId }
    }
}