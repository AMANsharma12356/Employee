package com.example.Employee.controller

import com.example.Employee.model.Employee
import com.example.Employee.repository.EmployeeRepository
import com.example.Employee.service.EmployeeService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@WebFluxTest(EmployeeController::class)
@AutoConfigureWebTestClient
 class EmployeeControllerTest {

 @Autowired
 lateinit var client: WebTestClient

 @Autowired
 lateinit var employeeService: EmployeeService

 @Autowired
 lateinit var employeeRepository: EmployeeRepository

 @TestConfiguration
 class ControllerTestConfig {
  @Bean
  fun employeeService() = mockk<EmployeeService>()

  @Bean
  fun employeeRepository() = mockk<EmployeeRepository>()

 }

// @Test
// fun `should return list  `() {
//  val employee = Employee(
//   "121", "Mansi", "Qaulity", "9878974564"
//  )
//
//  val expectedResult = mapOf(
//   "employeeId" to "121",
//   "employeeName" to "Mansi",
//   "employeeRole" to "Qaulity",
//   "employeeContact" to "9878974564"
//  )
//  every {
//   employeeService.findAllemployees()
//  } returns Flux.just(employee)
//
//  val response = client.get()
//   .uri("/employees/list")
//   .accept(MediaType.APPLICATION_JSON)
//   .exchange() //invoking the end point
//   .expectStatus().is2xxSuccessful
//   .returnResult<Any>()
//   .responseBody
//
//  response.blockFirst() shouldBe expectedResult
//
//  verify(exactly = 1) {
//   employeeService.findAllemployees()
//
//  }

 @Test
 fun `should return one employee`() {
  val employee = Employee(
   "121", "Mansi", "Qaulity", "9878974564"
  )

  val expectedResult = mapOf(
   "employeeId" to "121",
   "employeeName" to "Mansi",
   "employeeRole" to "Qaulity",
   "employeeContact" to "9878974564"
  )
  every {
   employeeService.findById("1")
  } returns Mono.just(employee)

  val response = client.get()
   .uri("/employees/1")
   .accept(MediaType.APPLICATION_JSON)
   .exchange() //invoking the end point
   .expectStatus().is2xxSuccessful
   .returnResult<Any>()
   .responseBody

  response.blockFirst() shouldBe expectedResult

  verify(exactly = 1) {
   employeeService.findById("1")
  }

 }

 @Test
 fun `should create employee when api is being called `() {
  val employee = Employee(
   "121", "Mansi", "Qaulity", "9878974564"
  )
  val expectedResponse = mapOf(
   "employeeId" to "121",
   "employeeName" to "Mansi",
   "employeeRole" to "Qaulity",
   "employeeContact" to "9878974564"
  )
  every {
   employeeService.addEmployee(employee)
  } returns Mono.just(employee)

  val response = client.post()
   .uri("/employees/add")
   .bodyValue(employee)
   .exchange()
   .expectStatus().is2xxSuccessful
   .returnResult<Any>().responseBody

  response.blockFirst() shouldBe expectedResponse
  verify(exactly = 1){
   employeeService.addEmployee(employee)
  }
 }
 @Test
 fun `should able to delete employee`() {
  val employee = Employee(
   "121", "Mansi", "Qaulity", "9878974564"
  )
  val expectedResponse = mapOf(
   "employeeId" to "121",
   "employeeName" to "Mansi",
   "employeeRole" to "Qaulity",
   "employeeContact" to "9878974564"
  )
  every {
   employeeService.deleteById("1")
  } returns Mono.empty()
  val response = client.delete()
   .uri("/employees/1")
   .exchange()
   .expectStatus().is2xxSuccessful

  verify(exactly = 1) {
   employeeService.deleteById("1")
  }
 }

 @Test
 fun `should able to update employee`(){
  val employee = Employee(
   "121", "Mansi", "Qaulity", "9878974564"
  )
  val expectedResponse = mapOf(
   "employeeId" to "121",
   "employeeName" to "Mansi",
   "employeeRole" to "Qaulity",
   "employeeContact" to "9878974564"
  )
  every {
   employeeService.updateEmployeeById("1",employee)
  } returns Mono.just(employee)

  val response = client.put()
   .uri("/updateEmployee/1")
   .bodyValue(employee)
   .exchange()
   .expectStatus().is2xxSuccessful

  verify(exactly = 1) {
   employeeService.updateEmployeeById("1",employee)
  }


 }
}

