package br.com.fagnerdev.controller;

import br.com.fagnerdev.model.Person;
import br.com.fagnerdev.services.PersonServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonServices personServices;



    // Injeção de dependência via construtor
    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    // Endpoint GET para retornar todos os "person"
    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> people = personServices.findAll();
        return ResponseEntity.ok(people);  // Retorna 200 OK com a lista de pessoas
    }

    // Endpoint GET para retornar uma pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Person person = personServices.findById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
        // Se a pessoa não for encontrada, retorna 404
    }

    // Endpoint POST para criar uma nova pessoa
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person createdPerson = personServices.create(person);
        return ResponseEntity.status(201).body(createdPerson); // Retorna 201 Created com o novo recurso
    }

    // Endpoint PUT para atualizar uma pessoa existente
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = personServices.update(id, person);
        return ResponseEntity.ok(updatedPerson);
    }


    // Endpoint DELETE para remover uma pessoa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personServices.delete(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content (sem corpo)
    }
}
