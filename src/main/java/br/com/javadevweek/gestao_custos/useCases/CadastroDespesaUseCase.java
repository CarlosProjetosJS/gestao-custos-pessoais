package br.com.javadevweek.gestao_custos.useCases;

import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroDespesaUseCase {
    // SOLID
    // Single Responsability principle

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa execute(Despesa despesa) {

        if(despesa.getCategoria() == null || despesa.getData() == null || despesa.getDescricao() == null || despesa.getEmail() == null) {

            String s = "Preencher todos os campos";
            throw new IllegalArgumentException("Preencher todos os campos");
        }

        despesaRepository.save(despesa);
        return despesa;

    }
}