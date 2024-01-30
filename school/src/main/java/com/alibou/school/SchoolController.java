// Importation des packages nécessaires
package com.alibou.school;

// Annotation Lombok pour générer automatiquement le constructeur requis
import lombok.RequiredArgsConstructor;

// Classes du framework Spring
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Importation de la classe modèle School et de la liste
import java.util.List;

// Définition du contrôleur REST pour les écoles
@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    // Injection de dépendance du service associé au contrôleur
    private final SchoolService service;

    // Endpoint pour enregistrer une école en utilisant la méthode HTTP POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody School school  // Corps de la requête contenant l'école à enregistrer
    ) {
        service.saveSchool(school);  // Appel du service pour enregistrer l'école
    }

    // Endpoint pour récupérer toutes les écoles en utilisant la méthode HTTP GET
    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(service.findAllSchools());  // Renvoie la liste de toutes les écoles
    }

    // Endpoint pour récupérer une école avec ses étudiants en utilisant la méthode HTTP GET
    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllSchools(
            @PathVariable("school-id") Integer schoolId  // Variable dans l'URL représentant l'identifiant de l'école
    ) {
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));  // Renvoie l'école avec ses étudiants
    }
}
