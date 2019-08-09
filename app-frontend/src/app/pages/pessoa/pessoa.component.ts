import { PessoaService } from './../../services/pessoa.service';
import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/app/model/pessoa';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.css']
})
export class PessoaComponent implements OnInit {

  public pessoa: Pessoa;
  constructor(
    private route: Router,
    private pessoaService: PessoaService
  ) { }

  ngOnInit() {
    this.pessoa = this.pessoaService.getter();
  }

  public goToList(): void {
    this.route.navigate(['/']);
  }

  public salvarPessoa(): void {
    if (this.pessoa.dataNascimento == null) {
      alert('Data de nascimento deve ser preenchida.');
      return;
    }

    if (!this.pessoa.dataNascimento.match("[0-3]{1}[0-9]{1}/[0-9]{2}/[0-9]{4}")) {
      alert('Data de nascimento inválida');
      return;
    }

    if (this.pessoa.id === undefined) {
      this.pessoaService.addPessoa(this.pessoa).subscribe((data) => {
        this.pessoa = data;
        alert('Pessoa criada com sucesso');
        this.route.navigate(['/']);
      },
        (dataErro) => {
          if (dataErro.error == null)
            alert("Ocorreu um erro ao processar os dados")
          else
            alert(dataErro.error.erros.join('\n'));
        });
    } else {
      this.pessoaService.updatePessoa(this.pessoa).subscribe((data) => {
        this.pessoa = data;
        alert('Pessoa atualizada com sucesso');
        this.route.navigate(['/']);
      },
        (dataErro) => {
          if (dataErro.error == null)
            alert("Ocorreu um erro ao processar os dados")
          else
            alert(dataErro.error.erros.join('\n'));
        });
    }
  }

}