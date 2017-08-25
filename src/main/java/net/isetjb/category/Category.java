package net.isetjb.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Category entity.
 */
@Entity
@Table(name = "categories")
public class Category
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //@NotNull(message = "Le nom ne doit pas être vide !")
    @Size(min = 2, max = 32, message = "pas moins de 2 caractères !")
    private String name;

    public Category()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
