package com.assercraft.classic.math;

public final class Mat4 {
    private final float[] m = new float[16];

    private Mat4() {}

    public static Mat4 identity() {
        Mat4 result = new Mat4();
        result.m[0] = 1f;
        result.m[5] = 1f;
        result.m[10] = 1f;
        result.m[15] = 1f;
        return result;
    }

    public static Mat4 perspective(float fovRad, float aspect, float near, float far) {
        Mat4 result = new Mat4();
        float f = 1f / (float) Math.tan(fovRad / 2f);
        result.m[0] = f / aspect;
        result.m[5] = f;
        result.m[10] = (far + near) / (near - far);
        result.m[11] = -1f;
        result.m[14] = (2f * far * near) / (near - far);
        return result;
    }

    public static Mat4 translate(Vec3 v) {
        Mat4 result = identity();
        result.m[12] = v.x();
        result.m[13] = v.y();
        result.m[14] = v.z();
        return result;
    }

    public Mat4 mul(Mat4 other) {
        Mat4 out = new Mat4();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                out.m[col + row * 4] =
                        m[row * 4] * other.m[col] +
                        m[row * 4 + 1] * other.m[col + 4] +
                        m[row * 4 + 2] * other.m[col + 8] +
                        m[row * 4 + 3] * other.m[col + 12];
            }
        }
        return out;
    }

    public float[] raw() {
        return m.clone();
    }
}
