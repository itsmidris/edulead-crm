package com.itsmidris.edulead_crm.course.entity;

import com.itsmidris.edulead_crm.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String courseName;

    @Column(nullable = false)
    private Integer durationInYears;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private boolean active = true;
}
