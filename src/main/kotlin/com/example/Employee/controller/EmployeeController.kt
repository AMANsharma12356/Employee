package com.example.Employee.controller

import com.example.Employee.model.Employee
import com.example.Employee.repository.EmployeeRepository
import com.example.Employee.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@CrossOrigin("*")

@RestController
class EmployeeController (
        @Autowired
        val employeeRepository: EmployeeRepository,
        val employeeService: EmployeeService

        ) {

        @GetMapping("/employees/list")
        fun getAllEmployees(): Flux<Employee> {
                return employeeService.findAllemployees()
        }

        @GetMapping("/employees/{id}")
        fun getEmployeeById(@PathVariable id: String): Mono<Employee> {
                return employeeService.findById(id)
        }
        @PostMapping("/employees/add")
        fun save(@RequestBody employee: Employee): Mono<Employee> {
                return employeeService.addEmployee(employee)
        }
        @DeleteMapping
        fun delete(): Mono<Void> {
                return employeeRepository.deleteAll()
        }

        @DeleteMapping("/employees/{id}")
        fun deleteUser(@PathVariable id: String): Mono<Void> {
                return employeeService.deleteById(id)
        }

        @PutMapping("/updateEmployee/{id}")
        fun update(@PathVariable("id") id: String,@RequestBody employee: Employee): Mono<Employee> {
                return employeeService.updateEmployeeById(id,employee)
        }

}
