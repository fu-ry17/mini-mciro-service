package com.fury.departmentservice.repository


import com.fury.departmentservice.client.EmployeeClient
import com.fury.departmentservice.model.Department
import org.springframework.stereotype.Repository

@Repository
class DepartmentRepository(private val employeeClient: EmployeeClient) {
    private val departments: MutableList<Department> = mutableListOf()

    fun addDepartment(department: Department): Department {
        departments.add(department)
        return department
    }

    fun getDepartments(): List<Department> {
        return departments
    }

    fun getDepartmentById(id: Long): Department? {
        return departments.find { it.id == id }
    }

    fun deleteDepartmentById(id: Long) {
        departments.removeIf { it.id == id }
    }

    fun getEmployeesInDepartment(id: Long): Department? {
        return departments.find { it.id == id }?.apply {
            employees = employeeClient.getEmployeesByDepartmentId(id)
        }
    }


}