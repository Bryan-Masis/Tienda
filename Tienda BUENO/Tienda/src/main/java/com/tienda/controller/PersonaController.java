package com.tienda.controller;

import com.tienda.entity.Pais;
import com.tienda.entity.Persona;
import com.tienda.service.IPaisService;
import com.tienda.service.IPersonaService;
import com.tienda.service.PersonaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j; //esto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //esto
public class PersonaController {

    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IPaisService paisService;

    @GetMapping("/personas")
    public String index(Model model) {
        List<Persona> listaPersonas = personaService.getAllperson();
        model.addAttribute("titulo", "Personas");
        model.addAttribute("personas", listaPersonas);
        return "personas";
    }

    @GetMapping("/personasN")
    public String crearPersona(Model model) {
        model.addAttribute("persona", new Persona());
        //personaService.savePerson(persona);
        return "crear";
    }

    @PostMapping("/save")
    public String guardarPersona(@ModelAttribute Persona persona) {
        personaService.savePerson(persona);
        return "redirect:/persona";
    }

    @GetMapping("/nuevoPersona")
    public String nuevaPerson(Persona persona) {
        return "modificarPersona";
    }

    //lo comente porque arriba hay uno que se llama igual y el de arriba es el del crud
//    @PostMapping("/guardarPersona")
//    public String guardarPersona(Persona persona){
//        personaService.savePerson(persona);
//        return "redirect:/";
//    }
    @GetMapping("/delete/{id}")
    public String eliminarPersona(@PathVariable("id") Long idPersona) {
        personaService.delete(idPersona);
        return "redirect/persona";
    }
    
    @GetMapping("/editPersona/{id}")
    public String editarPersona(@PathVariable("id") Long idPersona, Model model) {
        Persona p = personaService.getPersonById(idPersona);
        List<Pais> listaPais = paisService.listCountry();
        model.addAttribute("persona", p);
        model.addAttribute("paises", listaPais);
        return "crear";
    }

}
