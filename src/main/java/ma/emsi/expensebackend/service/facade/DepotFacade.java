package ma.emsi.expensebackend.service.facade;

import java.util.List;
import ma.emsi.expensebackend.entity.Depot;

public interface DepotFacade {
    Depot saveDepot(Depot depot, Long userId);
    void deleteDepot(Long depotId);
    Depot updateDepot(Depot depot);
    List<Depot> getAllDepots(); 
}
