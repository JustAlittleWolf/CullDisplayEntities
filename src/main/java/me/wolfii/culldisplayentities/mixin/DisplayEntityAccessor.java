package me.wolfii.culldisplayentities.mixin;

import com.mojang.math.Transformation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Display.class)
public interface DisplayEntityAccessor {
    @Invoker("createTransformation")
    static Transformation createTransformation(SynchedEntityData synchedEntityData) {
        throw new UnsupportedOperationException();
    }
}
