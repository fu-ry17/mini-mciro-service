package com.fury.employeeservice.controller

import Employees
import com.fury.employeeservice.repository.EmployeeRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/employees")
@RefreshScope
class EmployeeController(private val employeeRepository: EmployeeRepository) {

    private val log = LoggerFactory.getLogger(EmployeeController::class.java)

    @Value("\${service.name}")
    private val serviceInfo: String? = null

    @GetMapping("/message")
    fun getServiceInfo(): String {
        log.info("service name {}", serviceInfo)
        return "service name: $serviceInfo"
    }

    @GetMapping
    fun getEmployees(): ResponseEntity<List<Employees>>{
        log.info("Get all Employees")
        return ResponseEntity.ok(employeeRepository.getEmployees())
    }

    @PostMapping
    fun createEmployee(@RequestBody employees: Employees): ResponseEntity<Employees> {
        log.info("Creating new Employee: ${employees.name}")
        return  ResponseEntity.ok(employeeRepository.createEmployee(employees))
    }

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: Long): ResponseEntity<Employees> {
        log.info("Get Employee by id: $id")
        return ResponseEntity.ok(employeeRepository.getEmployeeById(id))
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<String> {
        log.info("Deleting Employee: $id")
        employeeRepository.deleteEmployeesById(id)
        return ResponseEntity.ok("Employee deleted")
    }

    @GetMapping("/department/{departmentId}")
    fun getEmployeesByDepartmentId(@PathVariable departmentId: Long): ResponseEntity<List<Employees>> {
        log.info("Get all Employees by departmentId: $departmentId")
        return ResponseEntity.ok(employeeRepository.getEmployeesByDepartmentId(departmentId))
    }
}