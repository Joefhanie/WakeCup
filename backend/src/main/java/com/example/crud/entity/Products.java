
// for category management module
package com.example.crud.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "listOfProducts")
public class Products {

    @Id
    @GeneratedValue
    private int id;
    private LocalDate dateCreated;
    private String category;
    private String name;
    private int price;
    private String status;    
    private String action;    

}
