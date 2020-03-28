package hiberspring.domain.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDto {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer clients;
    @XmlElement(name = "branch")
    private String branch;



    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Min(value=0)
    @NotNull
    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }


    @NotNull
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
