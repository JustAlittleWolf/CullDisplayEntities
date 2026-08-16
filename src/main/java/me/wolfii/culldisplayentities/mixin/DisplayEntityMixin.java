package me.wolfii.culldisplayentities.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.wolfii.culldisplayentities.DisplayEntityBoundingBoxCalculator;
import net.minecraft.world.entity.Display;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Display.class)
public abstract class DisplayEntityMixin {
    @Shadow
    private AABB cullingBoundingBox;

    @Shadow
    protected abstract float getWidth();

    @Shadow
    protected abstract float getHeight();

    @Shadow
    private boolean noCulling;

    @WrapMethod(method = "updateCulling")
    private void modifyBoundingBox(Operation<Void> original) {
        Display displayEntity = ((Display) (Object) this);

        float width = this.getWidth();
        float height = this.getHeight();
        if (width != 0 && height != 0) {
            original.call();
            return;
        }

        this.noCulling = false;
        this.cullingBoundingBox = DisplayEntityBoundingBoxCalculator.getWithTransform(
            displayEntity,
            DisplayEntityAccessor.invokeCreateTransformation(displayEntity.getEntityData()).getMatrix()
        );
    }
}
