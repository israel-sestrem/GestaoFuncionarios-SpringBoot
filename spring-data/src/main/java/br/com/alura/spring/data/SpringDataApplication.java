package br.com.alura.spring.data;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.alura.spring.data.service.RelatoriosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService crudCargoService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final RelatoriosService relatoriosService;

	private String op;

	public SpringDataApplication(CrudCargoService crudCargoService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, CrudFuncionarioService crudFuncionarioService, RelatoriosService relatoriosService){
		this.crudCargoService = crudCargoService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Seja bem vindo ao meu sistema!\n");

		do{
			System.out.println("1 - Funcionários");
			System.out.println("2 - Cargos");
			System.out.println("3 - Unidades de Trabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Sair");
			System.out.print("Escolha a aba que deseja acessar: ");
			op = leitor.next();

			switch (op){
				case "1":
					crudFuncionarioService.iniciar();
					break;
				case "2":
					crudCargoService.iniciar();
					break;
				case "3":
					crudUnidadeTrabalhoService.iniciar();
					break;
				case "4":
					relatoriosService.iniciar();
					break;
				case "5":
					break;
				default:
					System.out.println("Opção inválida");
					break;
			}
		} while(!op.equals("5"));

		System.out.println("\nObrigado por utilizar meu sistema!");
	}
}
