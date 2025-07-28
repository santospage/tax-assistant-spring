package br.com.santospage.taxassistant.interfaces.controllers;

import br.com.santospage.taxassistant.application.usecases.GetFiscalMovementsUseCase;
import br.com.santospage.taxassistant.domain.entities.FiscalMovement;
import br.com.santospage.taxassistant.interfaces.dtos.FiscalMovementDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/fiscal-movements")
public class FiscalMovementController {

    private final GetFiscalMovementsUseCase useCase;

    public FiscalMovementController(GetFiscalMovementsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<FiscalMovementDTO> getFiscalMovements(
            @RequestParam("tableCode") String tableCode
    ) {
        return useCase.execute(tableCode).stream().map(entity -> {
            FiscalMovementDTO dto = new FiscalMovementDTO();
            dto.fd2Id = entity.getMovementId();
            //dto.companyCode = entity.getCompanyCode();
            //dto.emissionDate = entity.getEmissionDate();
            //dto.productCode = entity.getProductCode();
            //dto.totalValue = entity.getTotalValue();
            //dto.cfop = entity.getCfop();
            return dto;
        }).toList();
    }
}

