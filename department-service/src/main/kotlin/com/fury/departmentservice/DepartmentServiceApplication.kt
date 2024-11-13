package com.fury.departmentservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class DepartmentServiceApplication

fun main(args: Array<String>) {
	runApplication<DepartmentServiceApplication>(*args)
}
