package service;

import dto.CommandeDashboardDTO;
import model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CommandeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Transactional(readOnly = true)
    public Optional<Commande> findById(Long id) {
        return commandeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<CommandeDashboardDTO> getCommandesEnAttenteDTO() {
        return commandeRepository.findByStatus("EN_ATTENTE").stream()
                .map(c -> new CommandeDashboardDTO(
                        c.getId(),
                        c.getUtilisateur().getUsername(),
                        c.getDate(),
                        c.getMontant()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Commande> findByIdWithLignes(Long id) {
        return commandeRepository.findByIdWithLignes(id); // <-- Ajouté
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