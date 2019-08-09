import { Pessoa } from './../model/pessoa';
import { Injectable } from '@angular/core';
import {
  HttpClient, HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private baseUrl: any = 'http://localhost:8080/pessoa';
  public pessoa: Pessoa = new Pessoa();
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Authorization': 'Basic YWRtaW46cGFzc3dvcmQ=',
      'Content-Type': 'application/json'
    });
    
  }

  public getAllPessoa(): Observable<Pessoa> {
    const url = this.baseUrl + '/';
    return this.http.get<Pessoa>(url, { headers: this.headers });
  }

  public addPessoa(pessoa: Pessoa): Observable<Pessoa> {
    const url = this.baseUrl + '/';
    return this.http.post<Pessoa>(url, pessoa, { headers: this.headers });
  }

  public updatePessoa(pessoa: Pessoa): Observable<Pessoa> {
    const url = this.baseUrl + '/' + pessoa.id;
    return this.http.put<Pessoa>(url, pessoa, { headers: this.headers });
  }

  public deletePessoa(id: number): Observable<Pessoa> {
    const url = this.baseUrl + '/' + id;
    return this.http.delete<Pessoa>(url, { headers: this.headers });
  }

  getter() {
    return this.pessoa;
  }

  setter(pessoa: Pessoa) {
    this.pessoa = pessoa;
  }
}