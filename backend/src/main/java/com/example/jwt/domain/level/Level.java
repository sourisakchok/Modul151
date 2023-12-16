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

    @Column(name = "discount_rate")
    private Double discountRate; // Rabattsatz

    @Column(name = "count_to_Upgrade")
    private int  countToUpgrade;

    public Level() {

    }

    public Level(LevelEnum name, Double discountRate, int countToUpgrade) {
        this.name = name;
        this.discountRate = discountRate;
        this.countToUpgrade = countToUpgrade;
    }

    public Level(UUID id, LevelEnum name, Double discountRate, int countToUpgrade) {
        super(id);
        this.name = name;
        this.discountRate = discountRate;
        this.countToUpgrade = countToUpgrade;
    }

    public LevelEnum getName() {
        return name;
    }

    public Level setName(LevelEnum name) {
        this.name = name;
        return this;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public Level setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public Level setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
        return this;
    }

    public int getCountToUpgrade() {
        return countToUpgrade;
    }

    public Level setCountToUpgrade(int countToUpgrade) {
        this.countToUpgrade = countToUpgrade;
        return this;
    }
}