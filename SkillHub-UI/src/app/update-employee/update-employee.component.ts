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
  myHttpError: any;

  employee: Employee = new Employee();

  constructor(private employeeService: EmployeeService,
              private router: Router,
              private route: ActivatedRoute){}

  ngOnInit(): void{

    this.id = this.route.snapshot.params["id"];

    this.employeeService.getEmployeeById(this.id).subscribe(data => this.employee = data, error => this.myHttpError = error.error)
  }

  goToEmployeeList(){
    this.router.navigate(["/employees"]);
  }

  goBack(event: Event): void {
      event.preventDefault();
      this.router.navigate([`/employee-details/${this.id}`]);
    }

  onSubmit(){
     this.updateEmployee()
  }

  updateEmployee(){
    this.employeeService.updateEmployee(this.employee, this.id)
    .subscribe(data => {console.log(data); this.router.navigate([`/employee-details/${this.id}`]);},
     error => this.myHttpError = error.error)
  }

}
