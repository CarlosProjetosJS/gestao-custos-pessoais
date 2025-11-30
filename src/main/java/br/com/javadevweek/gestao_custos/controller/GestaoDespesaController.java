package br.com.javadevweek.gestao_custos.controller;

import br.com.javadevweek.gestao_custos.custom_messages.ErrorMessage;
import br.com.javadevweek.gestao_custos.entity.Despesa;
import br.com.javadevweek.gestao_custos.useCases.BuscarDespesaUseCase;
import br.com.javadevweek.gestao_custos.useCases.CadastroDespesaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/gestao")
@RestController
public class GestaoDespesaController {

    /*
    - Cadastro de despesa
    - Criar tablea no banco de dados
    - Criar entidade
    */

    @Autowired
    CadastroDespesaUseCase cadastroDespesaUseCase;

    @Autowired
    BuscarDespesaUseCase buscarDespesaUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Despesa despesa){

         try {
             var result = cadastroDespesaUseCase.execute(despesa);
             return ResponseEntity.ok(result);
         }catch (IllegalArgumentException e) {

             var errorMessage = new ErrorMessage(e.getMessage(), "INVALID_PARAMS");
              return ResponseEntity.status(400).body(errorMessage);
         }
    }

    // /gestao/find/danieleleaoe@gmail.com?data=2025-06-08
    @GetMapping("/{email}")
    public List<Despesa> findByEmailAndDate(
            @PathVariable String email, @RequestParam(required = false)LocalDate data) {
            return buscarDespesaUseCase.execute(email, data);
        }
    }



