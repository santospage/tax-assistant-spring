package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.entities.FiscalMovement;

import java.util.List;
import java.util.Optional;

public interface FiscalMovementRepository {
    Optional<FiscalMovement> findById(String id); // se quiser buscar por ID único
    List<FiscalMovement> findAll();               // retorna todos os registros
    FiscalMovement save(FiscalMovement movement); // para salvar (criar ou atualizar)
    List<FiscalMovement> findByTable(String table); // busca por código de tabela
}