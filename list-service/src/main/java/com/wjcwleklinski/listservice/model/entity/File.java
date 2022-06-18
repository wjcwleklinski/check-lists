package com.wjcwleklinski.listservice.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FLE_FILES")
@Getter
@Setter
@Builder
@SequenceGenerator(name = "FLE_SEQUENCE", sequenceName = "FLE_SEQUENCE")
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @Column(name = "FLE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLE_SEQUENCE")
    private Long id;

    @Column(name = "FLE_NAME")
    private String name;

    @Column(name = "FLE_TYPE")
    private String type;

    @Column(name = "FLE_DATA")
    private byte[] data;
}
