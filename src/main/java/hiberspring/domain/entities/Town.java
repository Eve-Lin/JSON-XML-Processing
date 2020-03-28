package hiberspring.domain.entities;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="towns")
public class Town extends BaseEntity {
    private String name;
    private Long population;

    public Town() {
    }

    @Column(name="name", nullable =false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="population", nullable =false)
    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
