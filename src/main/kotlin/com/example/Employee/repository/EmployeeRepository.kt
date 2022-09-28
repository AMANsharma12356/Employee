package com.example.Employee.repository

import com.example.Employee.model.Employee
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : ReactiveMongoRepository<Employee, String> {

}
