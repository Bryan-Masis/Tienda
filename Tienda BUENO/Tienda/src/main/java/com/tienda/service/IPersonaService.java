package com.tienda.service;
import java.util.List;
import com.tienda.entity.Persona;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaService {
    public List<Persona> getAllperson();
    public void savePerson(Persona persona);
    public Persona getPersonById(long id);
    public void delete(long id);
    public Persona findByNombre(String nombre);
}
