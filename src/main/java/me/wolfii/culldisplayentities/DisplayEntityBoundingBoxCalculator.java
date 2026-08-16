package me.wolfii.culldisplayentities;

import net.minecraft.world.entity.Display;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

public class DisplayEntityBoundingBoxCalculator {
    private static final float modelSize = 1F;
    private static final float margin = 1F;

    private static final Vector3f[] corners = new Vector3f[]{
        new Vector3f(-margin, -margin, -margin),
        new Vector3f(-margin, -margin, modelSize + margin),
        new Vector3f(-margin, modelSize + margin, -margin),
        new Vector3f(-margin, modelSize + margin, modelSize + margin),
        new Vector3f(modelSize + margin, -margin, -margin),
        new Vector3f(modelSize + margin, -margin, modelSize + margin),
        new Vector3f(modelSize + margin, modelSize + margin, -margin),
        new Vector3f(modelSize + margin, modelSize + margin, modelSize + margin)
    };

    public static AABB getWithTransform(Display displayEntity, Matrix4fc transform) {
        Vector3f min = new Vector3f();
        Vector3f max = new Vector3f();
        Vector3f transformed = new Vector3f();
        for (Vector3f corner : corners) {
            transform.transformPosition(corner, transformed);
            min.min(transformed);
            max.max(transformed);
        }
        return new AABB(
            displayEntity.getX() + min.x,
            displayEntity.getY() + min.y,
            displayEntity.getZ() + min.z,
            displayEntity.getX() + max.x,
            displayEntity.getY() + max.y,
            displayEntity.getZ() + max.z
        );
    }
}
