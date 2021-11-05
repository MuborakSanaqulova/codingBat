package uz.pdp.vazifa2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String text;

    private String solution;

    private String hint;

    @Column(nullable = false)
    private String method;

    private Boolean hasStar;

    @ManyToOne
    private Category category;

}
