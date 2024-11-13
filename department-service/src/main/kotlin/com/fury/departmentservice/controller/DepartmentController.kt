package com.fury.departmentservice.controller

import com.fury.departmentservice.model.Department
import com.fury.departmentservice.repository.DepartmentRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/department")
@RefreshScope
class DepartmentController(private val repository: DepartmentRepository) {

    private val log = LoggerFactory.getLogger(this.javaClass)


    @Value("\${service.name}")
    private val serviceInfo: String? = null

    @GetMapping("/message")
    fun getServiceInfo(): String {
        log.info("service name {}", serviceInfo)
        return "service name: $serviceInfo"
    }

    @GetMapping
    fun getDepartments(): ResponseEntity<List<Department>> {
        log.info("Get all departments")
        return ResponseEntity.ok(repository.getDepartments())
    }

    @PostMapping
    fun createDepartment(@RequestBody department: Department): ResponseEntity<Department> {
        log.info("Creating new department")
        return ResponseEntity.ok(repository.addDepartment(department))
    }

    @GetMapping("/{id}")
    fun getDepartmentById(@PathVariable id: Long): ResponseEntity<Department> {
        return ResponseEntity.ok(repository.getDepartmentById(id))
    }

    @DeleteMapping("/{id}")
    fun deleteDepartment(@PathVariable id: Long): ResponseEntity<String> {
        log.info("Deleting existing department")
        repository.deleteDepartmentById(id)
        return ResponseEntity.ok("Department deleted!")
    }

    @GetMapping("/employees/{departmentId}")
    fun getEmployeesInDepartment(@PathVariable departmentId: Long): ResponseEntity<Department?> {
        log.info("Get employees from department")
        return ResponseEntity.ok(repository.getEmployeesInDepartment(departmentId))
    }
}