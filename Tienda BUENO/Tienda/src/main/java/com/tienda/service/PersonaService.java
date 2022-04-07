
package com.tienda.service;
import com.tienda.entity.Persona;
import java.util.List;
import java.util.Optional;
import com.tienda.repository.PersonaRepository;
import com.tienda.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PersonaService implements IPersonaService{
    //inyeccion de dependencias
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getAllperson() {
        return(List<Persona>)personaRepository.findAll();
    }

    @Override
    public void savePerson(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public Persona getPersonById(long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        personaRepository.deleteById(id);
    }

    public PersonaRepository getPersonaRepository() {
        return personaRepository;
    }

    public void setPersonaRepository(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona findByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
                
    }
    
    
}
