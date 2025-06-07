package jhomt.com.studytimeapi.Domain.Medal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.StudentMedal.StudentMedal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medals")
public class Medal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "criteria", nullable = false)
    private String criteria;

    @OneToMany(mappedBy = "medal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentMedal> studentMedalList;

    public Medal(DataRegisterMedal registerMedal){
        this.name = registerMedal.name();
        this.description = registerMedal.description();
        this.criteria = registerMedal.criteria();
    }

    public void update (DataUpdateMedal updateMedal){
        EntityUpdater.updateIfNotNull(updateMedal.name(), this::setName);
        EntityUpdater.updateIfNotNull(updateMedal.description(), this::setDescription);
        EntityUpdater.updateIfNotNull(updateMedal.criteria(), this::setCriteria);
    }
}