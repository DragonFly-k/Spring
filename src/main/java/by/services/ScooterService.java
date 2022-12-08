package by.services;

import by.model.Scooter;
import by.repository.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService {
    @Autowired
    ScooterRepository scooterRepository;

    public List<Scooter> getAll() {
        return scooterRepository.findAll();
    }

    public Scooter getById(Long id) {
        Scooter scooter = scooterRepository.findScooterById(id);
        return scooter;
    }
    public Scooter getByModel(String model) {
        Scooter scooter = scooterRepository.findScooterByModel(model);
        return scooter;
    }
    public void create(Scooter scooter) {
        scooterRepository.save(scooter);
    }
    public void delete(Scooter scooter) {
        scooterRepository.delete(scooter);
    }

}