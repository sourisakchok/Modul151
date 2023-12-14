package com.example.jwt.domain.level;

import com.example.jwt.core.generic.ExtendedEntity;
import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.user.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "level")
public class Level extends ExtendedEntity {

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LevelEnum name;
    // private int seeds;

    public Level(UUID id, LevelEnum name) {
        super(id);
        this.name = name;
    }

    public Level() {

    }

    public LevelEnum getName() {
        return name;
    }

    public Level setName(LevelEnum name) {
        this.name = name;
        return this;
    }

    // public void addSeeds(int newSeeds) {
    // this.seeds += newSeeds;
    // updateLevel();
    // }

    // private void updateLevel() {
    // for (LevelEnum l : LevelEnum.values()) {
    // if (this.seeds >= l.getSeed()) {
    // this.name = l;
    // break;
    // }
    // }
    // }
}