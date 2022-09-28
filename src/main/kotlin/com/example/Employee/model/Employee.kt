package com.example.Employee.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
data class Employee(
    @Id
    var employeeId: String?,
    var employeeName: String,
    var employeeRole: String,
    var employeeContact: String,

    //aman
)