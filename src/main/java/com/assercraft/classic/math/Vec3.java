package com.assercraft.classic.math;

public record Vec3(float x, float y, float z) {
    public Vec3 add(Vec3 other) {
        return new Vec3(x + other.x, y + other.y, z + other.z);
    }

    public Vec3 sub(Vec3 other) {
        return new Vec3(x - other.x, y - other.y, z - other.z);
    }

    public Vec3 scale(float scalar) {
        return new Vec3(x * scalar, y * scalar, z * scalar);
    }

    public float dot(Vec3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public float length() {
        return (float) Math.sqrt(dot(this));
    }

    public Vec3 normalize() {
        float len = length();
        if (len == 0f) {
            return this;
        }
        return scale(1f / len);
    }
}
