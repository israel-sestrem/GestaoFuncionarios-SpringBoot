package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private String op;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public void iniciar(){
        Scanner leitor = new Scanner(System.in);

        do{
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar");
            System.out.print("Informe a opção desejada: ");
            op = leitor.next();

            switch (op){
                case "1":
                    cadastrar(leitor);
                    break;
                case "2":
                    atualizar(leitor);
                    break;
                case "3":
                    deletar(leitor);
                    break;
                case "4":
                    listar();
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while(!op.equals("5"));
    }

    public void cadastrar(Scanner leitor){
        Funcionario funcionario = pegarInformacoesFuncionario(leitor);
        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void atualizar(Scanner leitor) {
        System.out.print("Informe o id do funcionário que deseja atualizar: ");
        Integer id = leitor.nextInt();

        boolean idExiste = funcionarioRepository.existsById(id);

        if(idExiste){
            System.out.println("Escreva as novas informações do usuário");
            Funcionario funcionario = pegarInformacoesFuncionario(leitor);
            funcionario.setId(id);
            funcionarioRepository.save(funcionario);
            System.out.println("Funcionário atualizado com sucesso!");
        } else {
            System.out.println("O id informado não existe!");
        }
    }

    public void deletar(Scanner leitor){
        System.out.print("Informe o id do funcionário que deseja deletar: ");
        Integer id = leitor.nextInt();

        boolean idExiste = funcionarioRepository.existsById(id);

        if(idExiste){
            funcionarioRepository.deleteById(id);
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("O id informado não existe!");
        }
    }

    public void listar(){
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        if(funcionarioRepository.count()>=1) {
            funcionarios.forEach(funcionario -> System.out.println(funcionario));
        } else {
            System.out.println("Não há registros de funcionários no banco de dados");
        }
    }

    public Funcionario pegarInformacoesFuncionario(Scanner leitor) {
        System.out.print("Informe o nome do funcionário: ");
        leitor.nextLine();
        String nome = leitor.nextLine();
        System.out.print("Informe o cpf do funcionário: ");
        String cpf = leitor.next();
        System.out.print("Informe o salário do funcionário: ");
        Double salario = leitor.nextDouble();
        System.out.print("Informe a data de sua contratação: ");
        String data_contratacao = leitor.next();
        System.out.print("Informe o id do seu cargo: ");
        Integer id_cargo = leitor.nextInt();
        System.out.print("Informe o id da sua unidade de trabalho: ");
        Integer id_unidade_trabalho = leitor.nextInt();
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setData_contratacao(data_contratacao);
        funcionario.setCargo_id(id_cargo);
        funcionario.setUnidade_trabalho_id(id_unidade_trabalho);
        return funcionario;
    }

}
