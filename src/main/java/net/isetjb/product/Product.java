package net.isetjb.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import net.isetjb.category.Category;

/**
 * Product entity.
 */
@Entity
@Table(name = "products")
public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Le nom ne doit pas être vide !")
    @Size(min = 2, max = 32, message = "pas moins de 2 caractères !")
    private String name;

    @NotNull(message = "Le prix ne doit pas être vide")
    @Min(0)
    @Column(precision = 19, scale = 3)
    private BigDecimal price;

    @OneToOne
    @NotNull(message = "La catégorie ne doit pas être vide !")
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
    private Category category;

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

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
}
