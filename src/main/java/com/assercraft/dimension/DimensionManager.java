package com.assercraft.dimension;

public final class DimensionManager {
    private DimensionType current = DimensionType.OVERWORLD;

    public DimensionType current() {
        return current;
    }

    public void travelToNether() {
        current = DimensionType.NETHER;
    }

    public void travelToEnd() {
        current = DimensionType.END;
    }

    public boolean isBossDimension() {
        return current == DimensionType.END;
    }
}
