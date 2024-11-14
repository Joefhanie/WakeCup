
package com.example.crud.categoryEntity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// anotation
@Data
@NoArgsConstructor
@Entity
@Table(name = "listOfCategories")
public class Category {
    
    @Id
    @GeneratedValue
    private int id;
    private LocalDate dateCreated;
    private String name;
    private String description;
    private String status;    
    private String action;    
}
