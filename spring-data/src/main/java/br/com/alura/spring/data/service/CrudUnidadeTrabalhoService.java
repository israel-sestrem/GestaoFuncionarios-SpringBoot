package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private String op;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository){
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void iniciar(){
        Scanner leitor = new Scanner(System.in);

        do{
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar");
            System.out.print("Escolha a opção desejada: ");
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
                    System.out.println("Opção inválida");
                    break;
            }
        } while(!op.equals("5"));
    }

    public void cadastrar(Scanner leitor){
        System.out.print("Informe a descrição da unidade de trabalho: ");
        leitor.nextLine();
        String descricao = leitor.nextLine();
        System.out.print("Informe o endereço da unidade de trabalho: ");
        String endereco = leitor.nextLine();
        leitor.nextLine();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de trabalho cadastrada com sucesso!");
    }

    public void atualizar(Scanner leitor){
        System.out.print("Informe o id da unidade de trabalho que deseja atualizar: ");
        Integer id = leitor.nextInt();

        boolean idExiste = unidadeTrabalhoRepository.existsById(id);

        if(idExiste){
            System.out.print("Informe a nova descrição da unidade de trabalho: ");
            leitor.nextLine();
            String novaDescricao = leitor.nextLine();
            System.out.print("Informe o novo endereço da unidade de trabalho: ");
            String novoEndereco = leitor.nextLine();
            UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
            unidadeTrabalho.setId(id);
            unidadeTrabalho.setDescricao(novaDescricao);
            unidadeTrabalho.setEndereco(novoEndereco);
            unidadeTrabalhoRepository.save(unidadeTrabalho);
            System.out.println("Unidade de trabalho atualizada com sucesso!");
        } else {
            System.out.println("O id informado não existe");
        }
    }

    public void deletar(Scanner leitor){
        System.out.print("Informe o id da unidade de trabalho que deseja deletar: ");
        Integer id = leitor.nextInt();

        boolean idExiste = unidadeTrabalhoRepository.existsById(id);

        if(idExiste){
            unidadeTrabalhoRepository.deleteById(id);
            System.out.println("Unidade de trabalho deletada com sucesso!");
        } else {
            System.out.println("O id informado não existe");
        }
    }

    public void listar(){
        Iterable<UnidadeTrabalho> unidades_trabalho = unidadeTrabalhoRepository.findAll();
        if(unidadeTrabalhoRepository.count()>=1) {
            unidades_trabalho.forEach(unidade_trabalho -> System.out.println(unidade_trabalho));
        } else {
            System.out.println("Não há registros de unidades de trabalho no banco de dados");
        }
    }

}
