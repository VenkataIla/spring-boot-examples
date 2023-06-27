package com.example.springbootex.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    private Integer sid;
    private String sname;

    @ManyToOne
    @JoinColumn(name = "bidFk")
    private Book bob;

}
