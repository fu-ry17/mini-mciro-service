package com.fury.departmentservice.client

import com.fury.departmentservice.model.Employees
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "employee-service")
interface EmployeeClient {
    @GetMapping("/api/v1/employees/department/{departmentId}")
    fun getEmployeesByDepartmentId(@PathVariable departmentId: Long): List<Employees>
}
