import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = "http://localhost:8080/api/v1/employees";
  constructor(private httpClient: HttpClient) { }

  getEmployeesList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseUrl}`);

  }

  getEmployeeById(id: number): Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseUrl}/${id}`);

  }

  createEmployee(employee: Employee ): Observable<Object>{
    return this.httpClient.post<Employee>(`${this.baseUrl}`, employee);
  }

  updateEmployee(employee: Employee, id: number ): Observable<Object>{

    return this.httpClient.patch<Employee>(`${this.baseUrl}/${id}`, employee)
  }

  deleteEmployee(id: number): Observable<Object>{
    console.log("inside delete service" + id)
    return this.httpClient.delete<Employee>(`${this.baseUrl}/${id}`)
  }
}
