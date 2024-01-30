// Package de la classe
package com.alibou.school;

// Importation des classes nécessaires
import com.alibou.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

// Annotation déclarant la classe comme un service Spring
@Service
// Annotation générant un constructeur avec les champs obligatoires
@RequiredArgsConstructor
public class SchoolService {

    // Injection des dépendances nécessaires à la classe
    private final SchoolRepository repository;
    private final StudentClient client;

    // Méthode pour sauvegarder une école
    public void saveSchool(School school) {
        repository.save(school); // Enregistrement dans le repository
    }

    // Méthode pour trouver toutes les écoles
    public List<School> findAllSchools() {
        return repository.findAll(); // Récupération de toutes les écoles depuis le repository
    }

    // Méthode pour trouver une école avec ses étudiants
    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        // Recherche de l'école par ID dans le repository
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        // Récupération de la liste des étudiants par l'ID de l'école à l'aide du client
        var students = client.findAllStudentsBySchool(schoolId);
        // Construction de la réponse avec le nom, l'email de l'école et la liste des étudiants
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}
