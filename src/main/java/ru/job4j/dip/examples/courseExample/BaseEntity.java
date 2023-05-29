package ru.job4j.dip.examples.courseExample;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BaseEntity {

    protected int id;
    protected String name;
}
