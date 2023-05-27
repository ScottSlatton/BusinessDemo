import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent {

  employee: Employee = new Employee();
  myHttpError: any;

  constructor(private employeeService: EmployeeService, private router: Router){}

  goToEmployeeList(){
    this.router.navigate(["/employees"]);
  }
  onSubmit(){
     this.saveEmployee()
  }

  saveEmployee(){
    this.employeeService.createEmployee(this.employee).subscribe(data => {console.log(data); this.goToEmployeeList();}, error =>  this.myHttpError = error.error)
  }

}
