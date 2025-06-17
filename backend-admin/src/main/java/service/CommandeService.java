package service;

import model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CommandeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Transactional(readOnly = true)
    public Optional<Commande> findById(Long id) {
        return commandeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    @Transactional
    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Transactional
    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }
}