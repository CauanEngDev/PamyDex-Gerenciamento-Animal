package com.cauanengdev.pamydex.models;

import com.cauanengdev.pamydex.enums.HumanSex;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class PamMaster extends Identificator {
    private LocalDate age;
    private HumanSex sex;
    private String phone;
    private String email;
    @Embedded
    private Address address;
    @ManyToOne @JoinColumn(name = "pamgym_id")
    private PamGym pamGym;
    @OneToMany(mappedBy = "pamMaster") @Setter(AccessLevel.NONE)
    private Set<PamNimal> pamNimals = new HashSet<>();

    public PamMaster(String name, LocalDate age, HumanSex sex, String phone, String email, Address address, PamGym pamGym) {
        super(name);
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.pamGym = pamGym;
    }

    public void addAnimal(PamNimal newPamNimal) {
        this.pamNimals.add(newPamNimal);
    }

    public void removeAnimal(PamNimal pamNimal) {
        pamNimals.remove(pamNimal);
    }

    public void switchAllAnimal(PamMaster newPamMaster) {
        new HashSet<>(pamNimals).forEach(a -> switchAnimal(a, newPamMaster));
    }

    public void switchAnimal(PamNimal pamNimal, PamMaster newPamMaster) {
        newPamMaster.addAnimal(pamNimal);
        this.removeAnimal(pamNimal);
        pamNimal.setPamMaster(newPamMaster);
    }
}
