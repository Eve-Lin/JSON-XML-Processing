package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeSeedDto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "position")
    private String position;
    @XmlElement(name = "card")
    private String card;
    @XmlElement(name = "branch")
    private String branch;



    @NotNull
    public String getFirstName() {
        return firstName;
    }

    @NotNull
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @NotNull
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotNull
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
