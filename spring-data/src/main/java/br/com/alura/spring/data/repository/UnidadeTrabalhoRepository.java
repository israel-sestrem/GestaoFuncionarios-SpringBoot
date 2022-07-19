package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Integer> {
    List<UnidadeTrabalho> findByDescricao(String desc);
}
