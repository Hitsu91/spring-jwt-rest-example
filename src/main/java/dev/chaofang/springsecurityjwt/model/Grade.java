package dev.chaofang.springsecurityjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private float score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Student student;
}
