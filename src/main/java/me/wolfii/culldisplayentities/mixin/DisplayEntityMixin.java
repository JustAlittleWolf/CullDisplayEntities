package me.wolfii.culldisplayentities.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.wolfii.culldisplayentities.client.DisplayEntityBoundingBoxCalculator;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DisplayEntity.class)
public abstract class DisplayEntityMixin {
    @Shadow
    private Box visibilityBoundingBox;

    @Shadow
    protected abstract float getDisplayWidth();

    @Shadow
    protected abstract float getDisplayHeight();

    @WrapMethod(method = "updateVisibilityBoundingBox")
    private void modifyBoundingBox(Operation<Void> original) {
        DisplayEntity displayEntity = ((DisplayEntity) (Object) this);

        float width = this.getDisplayWidth();
        float height = this.getDisplayHeight();
        if (width != 0 && height != 0) {
            original.call();
            return;
        }

        displayEntity.ignoreCameraFrustum = false;
        this.visibilityBoundingBox = DisplayEntityBoundingBoxCalculator.getWithTransform(
                displayEntity,
                DisplayEntityAccessor.getTransformation(displayEntity.getDataTracker()).getMatrix()
        );
    }
}
