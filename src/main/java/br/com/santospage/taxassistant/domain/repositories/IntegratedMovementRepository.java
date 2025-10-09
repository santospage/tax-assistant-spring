package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.IntegratedMovement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IntegratedMovementRepository {

    @PersistenceContext
    private EntityManager em;

    public List<IntegratedMovement> findIntegratedMovements(String company) {
        String sql = """
                SELECT DISTINCT
                    F2B.F2B_FILIAL AS company,
                    F2B.F2B_TRIB AS trib,
                    F2C.F2C_DESC AS description
                FROM F2BT10 F2B
                INNER JOIN F2ET10 F2E
                    ON F2E.F2E_TRIB = F2B.F2B_TRIB AND F2E.D_E_L_E_T_ = ' '
                INNER JOIN F2CT10 F2C
                    ON F2E.F2E_FILIAL = F2C.F2C_FILIAL
                    AND F2E.F2E_IDTRIB = F2C.F2C_CODIGO
                    AND F2C.D_E_L_E_T_ = ' '
                WHERE F2B.D_E_L_E_T_ = ' '
                """;

        if (company != null) {
            sql += " AND F2B.F2B_FILIAL = :company";
        }

        sql += " ORDER BY F2B.F2B_FILIAL, F2B.F2B_TRIB";

        Query query = em.createNativeQuery(sql);

        if (company != null) {
            query.setParameter("company", company);
        }

        List<Object[]> rawList = query.getResultList();

        return rawList.stream()
                .map(o -> new IntegratedMovement(
                        (String) o[0],
                        (String) o[1],
                        (String) o[2]
                ))
                .toList();
    }
}
