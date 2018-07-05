In order to run the project follow the below steps:
a) Import as Maven project.
b) To build. Right click on parent project 'employee'->Run as ->Maven build->Give 'clean install'
c) To run the project. Right click on main project 'employee-service'->Run As->Spring Boot application.
d) If you want to test the Spring rest End-points,hit the below url in POSTMAN: 
a) GET Employee details:
http://localhost:8585/employee-service/employeedetail
b) POST Request:To invoke EmployeeAuthentication REST end-point.

http://localhost:8585/employee-service/EmployeeAuthentication

Give the test data in body section:
  {
"employeeNumber": "A765565","password": "Staffing"
}



c) Post Request:
To register employee:
Give the test data in body section:

  {
        "employeeName": "Rishikesh Kumar",
        "employeeNumber": "A765565",
        "password": "Staffing",
        "designation": "Consultant",
        "serviceLine": "SI",
        "role": "zyz"
    }