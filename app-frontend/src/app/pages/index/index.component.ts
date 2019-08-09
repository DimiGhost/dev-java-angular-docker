import { Pessoa } from './../../model/pessoa';
import { PessoaService } from './../../services/pessoa.service';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit, AfterViewInit {

  public pessoa: Pessoa;
  constructor(
    private route: Router,
    private pessoaService: PessoaService
  ) { }

  ngOnInit() {
    this.pessoaService.getter();
  }

  ngAfterViewInit() {
    this.getAllPessoas();
  }

  private getAllPessoas(): any {
    this.pessoaService.getAllPessoa().subscribe((data) => {
      this.pessoa = data;
    });
  }

  public deletePessoa(id: number): void {
    this.pessoaService.deletePessoa(id).subscribe((data) => {
      alert('Pessoa deletada com sucesso');
      this.getAllPessoas();
    },
      (dataErro) => {
        alert(dataErro.error.erros.join('\n'));
      });
  }

  public goToAddPessoa(): void {
    const pessoa: Pessoa = new Pessoa();
    this.pessoaService.setter(pessoa);
    this.route.navigate(['/pessoa']);
  }

  public goToUpdatePessoa(pessoa: Pessoa): void {
    this.pessoaService.setter(pessoa);
    this.route.navigate(['/pessoa']);
  }

}