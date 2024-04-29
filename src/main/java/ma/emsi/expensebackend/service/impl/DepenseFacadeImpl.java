package ma.emsi.expensebackend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.emsi.expensebackend.entity.Depense;
import ma.emsi.expensebackend.repository.DepenseRepository;
import ma.emsi.expensebackend.service.facade.DepenseFacade;

@Service
public class DepenseFacadeImpl implements DepenseFacade {

    @Autowired
    private final DepenseRepository depenseRepository;

    public DepenseFacadeImpl(DepenseRepository depenseRepository) {
        this.depenseRepository = depenseRepository;
    }

    @Override
    public Depense saveDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    @Override
    public void deleteDepense(Long depenseId) {
        depenseRepository.deleteById(depenseId);
    }

    @Override
    public Depense updateDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    @Override
    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }
}
