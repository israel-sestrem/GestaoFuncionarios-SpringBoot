package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;

    private String op;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
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
        System.out.print("Informe a descrição do cargo: ");
        leitor.nextLine();
        String descricao = leitor.nextLine();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo salvo com sucesso!");
    }

    public void atualizar(Scanner leitor){
        System.out.print("Informe o id do registro que deseja atualizar: ");
        Integer id = leitor.nextInt();

        boolean idExiste = cargoRepository.existsById(id);

        if(idExiste){
            System.out.print("Informe o novo cargo: ");
            leitor.nextLine();
            String novaDescricao = leitor.nextLine();
            Cargo cargo = new Cargo();
            cargo.setId(id);
            cargo.setDescricao(novaDescricao);
            cargoRepository.save(cargo);
            System.out.println("Cargo editado com sucesso!");
        } else {
            System.out.println("O id informado não existe na tabela");
        }
    }

    public void deletar(Scanner leitor){
        System.out.print("Informe o id do produto que deseja remover: ");
        Integer id = leitor.nextInt();

        boolean idExiste = cargoRepository.existsById(id);

        if(idExiste){
            cargoRepository.deleteById(id);
            System.out.println("Cargo deletado com sucesso!");
        } else {
            System.out.println("O id informado não existe na tabela");
        }
    }

    public void listar(){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        if(cargoRepository.count()>=1) {
            cargos.forEach(cargo -> System.out.println(cargo));
        } else {
            System.out.println("Não há registros de cargos no banco de dados");
        }
    }

}
