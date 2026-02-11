package com.assercraft.classic.gfx;

import org.lwjgl.opengl.GL33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class ShaderProgram {
    private final int programId;

    public ShaderProgram(String vertexPath, String fragmentPath) {
        int vertex = compile(GL33.GL_VERTEX_SHADER, readResource(vertexPath));
        int fragment = compile(GL33.GL_FRAGMENT_SHADER, readResource(fragmentPath));

        programId = GL33.glCreateProgram();
        GL33.glAttachShader(programId, vertex);
        GL33.glAttachShader(programId, fragment);
        GL33.glLinkProgram(programId);
        if (GL33.glGetProgrami(programId, GL33.GL_LINK_STATUS) == GL33.GL_FALSE) {
            throw new IllegalStateException("Program link failed: " + GL33.glGetProgramInfoLog(programId));
        }

        GL33.glDeleteShader(vertex);
        GL33.glDeleteShader(fragment);
    }

    public void use() {
        GL33.glUseProgram(programId);
    }

    public void destroy() {
        GL33.glDeleteProgram(programId);
    }

    private static int compile(int type, String src) {
        int shaderId = GL33.glCreateShader(type);
        GL33.glShaderSource(shaderId, src);
        GL33.glCompileShader(shaderId);
        if (GL33.glGetShaderi(shaderId, GL33.GL_COMPILE_STATUS) == GL33.GL_FALSE) {
            throw new IllegalStateException("Shader compile failed: " + GL33.glGetShaderInfoLog(shaderId));
        }
        return shaderId;
    }

    private static String readResource(String path) {
        InputStream in = ShaderProgram.class.getResourceAsStream(path);
        if (in == null) {
            throw new IllegalArgumentException("Missing shader: " + path);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load shader: " + path, e);
        }
    }
}
