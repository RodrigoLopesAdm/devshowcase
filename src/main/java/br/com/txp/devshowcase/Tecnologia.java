package br.com.txp.devshowcase.model;

import jakarta.persistence.Column;






@Entity
@Table (name = "tecnologia")
public class Tecnologia {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    public Tecnologia() {}

    public Tecnologia(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName (String name) { this.name = name; }
}
