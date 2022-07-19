package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private final FuncionarioRepository funcionarioRepository;

    private String op;

    public RelatoriosService(CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, FuncionarioRepository funcionarioRepository){
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public void iniciar(){
        Scanner leitor = new Scanner(System.in);

        do{
            System.out.println("1 - Buscar funcionário por nome");
            System.out.println("2 - Buscar cargo por nome");
            System.out.println("3 - Buscar unidade de trabalho por nome");
            System.out.println("4 - Voltar");
            System.out.print("Escolha a opção desejada: ");
            op = leitor.next();

            switch (op){
                case "1":
                    buscarFuncionarioNome(leitor);
                    break;
                case "2":
                    buscarCargoNome(leitor);
                    break;
                case "3":
                    buscarUnidadeTrabalhoNome(leitor);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while(!op.equals("4"));
    }

    public void buscarFuncionarioNome(Scanner leitor){
        System.out.print("Informe o nome do funcionário: ");
        leitor.nextLine();
        String nome = leitor.nextLine();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        if(list.size() >= 1) {
            list.forEach(System.out::println);
        } else {
            System.out.println("Não há funcionários cadastrados no banco de dados.");
        }
    }

    public void buscarCargoNome(Scanner leitor){
        System.out.print("Informe o nome do cargo: ");
        String cargo = leitor.next();
        List<Cargo> list = cargoRepository.findByDescricao(cargo);
        if(list.size() >= 1) {
            list.forEach(System.out::println);
        } else {
            System.out.println("Não há cargos cadastrados no banco de dados.");
        }
    }

    public void buscarUnidadeTrabalhoNome(Scanner leitor){
        System.out.print("Informe a descrição da unidade de trabalho: ");
        leitor.nextLine();
        String desc = leitor.nextLine();
        List<UnidadeTrabalho> list = unidadeTrabalhoRepository.findByDescricao(desc);
        if(list.size() >= 1) {
            list.forEach(System.out::println);
        } else {
            System.out.println("Não há unidades de trabalho cadastradas no banco de dados.");
        }
    }

}
