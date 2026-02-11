package com.assercraft.crafting;

import java.util.List;

public record Recipe(String id, int width, int height, List<String> pattern, String result, int resultCount) {}
