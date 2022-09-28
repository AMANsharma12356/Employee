package com.example.Employee.service

import com.example.Employee.model.Employee
import com.example.Employee.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EmployeeService(
    @Autowired
    val employeeRepository: EmployeeRepository

) {
    fun findAllemployees(): Flux<Employee> {
        return employeeRepository.findAll()
    }

    fun addEmployee(employee: Employee): Mono<Employee> {
        return employeeRepository.save(employee)
    }

    fun updateEmployeeById(id:String, employee: Employee): Mono<Employee> {
        return employeeRepository.save(employee)
    }

    fun deleteById(id: String): Mono<Void> {
        return employeeRepository.deleteById(id)
    }

    fun findById(id: String): Mono<Employee> {
        return employeeRepository.findById(id)
    }
}