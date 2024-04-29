package ma.emsi.expensebackend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.emsi.expensebackend.entity.Depot;
import ma.emsi.expensebackend.repository.DepotRepository;
import ma.emsi.expensebackend.service.facade.DepotFacade;

@Service
public class DepotFacadeImpl implements DepotFacade {

    @Autowired
    private final DepotRepository depotRepository;

    public DepotFacadeImpl(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }

    @Override
    public Depot saveDepot(Depot depot) {
        return depotRepository.save(depot);
    }

    @Override
    public void deleteDepot(Long depotId) {
        depotRepository.deleteById(depotId);
    }

    @Override
    public Depot updateDepot(Depot depot) {
        return depotRepository.save(depot);
    }

    @Override
    public List<Depot> getAllDepots() {
        return depotRepository.findAll();
    }
}
