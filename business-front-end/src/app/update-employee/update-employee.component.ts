import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  id!: number;

  employee: Employee = new Employee();

  constructor(private employeeService: EmployeeService, private router: Router, private route: ActivatedRoute){}

  ngOnInit(): void{

    this.id = this.route.snapshot.params["id"];

    this.employeeService.getEmployeeById(this.id).subscribe(data => {this.employee = data}, error => {console.log(error)})
  }

  goToEmployeeList(){
    this.router.navigate(["/employees"]);
  }
  onSubmit(){
     this.updateEmployee()
  }

  updateEmployee(){
    this.employeeService.updateEmployee(this.employee, this.id).subscribe(data => {console.log(data); this.goToEmployeeList();}, error => console.log(error))
  }

}
