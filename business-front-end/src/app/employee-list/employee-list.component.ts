import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';
import { identifierName } from '@angular/compiler';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit{

  employees: Employee[] = [];

  myHttpError: any;

  constructor(private router: Router, private employeeService: EmployeeService){}

  ngOnInit(): void{

    this.getEmployees();
  }

  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe( data => {
      this.employees = data
    }, error => this.myHttpError = error.error);
  }

  public employeeDetails(id: number){
    this.router.navigate(["employee-details", id])
  }

}

