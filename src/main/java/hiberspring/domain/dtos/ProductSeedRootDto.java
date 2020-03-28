package hiberspring.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {

    @XmlElement(name="product")
    private List<ProductSeedDto> products;

    public List<ProductSeedDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSeedDto> products) {
        this.products = products;
    }
}
