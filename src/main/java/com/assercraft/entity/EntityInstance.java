package com.assercraft.entity;

import com.assercraft.entity.ai.AiState;

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
import java.util.List;

public final class EntityInstance {
    private final String id;
    private final String type;
    private final List<String> drops;
    private int health;
    private AiState state = AiState.IDLE;

    public EntityInstance(String id, String type, int health, List<String> drops) {
        this.id = id;
        this.type = type;
        this.health = health;
        this.drops = List.copyOf(drops);
=======
public final class EntityInstance {
    private final String id;
    private final String type;
    private int health;
    private AiState state = AiState.IDLE;

    public EntityInstance(String id, String type, int health) {
        this.id = id;
        this.type = type;
        this.health = health;
 >>>>>>> main
    }

    public void tick(double distanceToPlayer) {
        if (distanceToPlayer < 2.0) {
            state = AiState.ATTACK;
        } else if (distanceToPlayer < 10.0) {
            state = AiState.CHASE;
        } else if (distanceToPlayer < 20.0) {
            state = AiState.WANDER;
        } else {
            state = AiState.IDLE;
        }
    }

    public String id() { return id; }
    public String type() { return type; }
    public int health() { return health; }
    public AiState state() { return state; }
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
    public List<String> drops() { return drops; }
=======
 >>>>>>> main

    public void damage(int amount) {
        health = Math.max(0, health - amount);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
