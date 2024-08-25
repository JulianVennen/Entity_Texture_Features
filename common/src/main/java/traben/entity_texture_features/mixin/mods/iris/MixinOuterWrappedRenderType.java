package traben.entity_texture_features.mixin.mods.iris;
#if MC == MC_20_2 || MC < MC_20_1
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import traben.entity_texture_features.ETFException;

@Pseudo
@Mixin(ETFException.class)
public class MixinOuterWrappedRenderType {}
#else
import net.irisshaders.iris.layer.OuterWrappedRenderType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import traben.entity_texture_features.utils.ETFRenderLayerWithTexture;

import java.util.Optional;


/**
 * Required in case Iris wraps an instance of {@link ETFRenderLayerWithTexture}
 */
@Pseudo
@Mixin(value = OuterWrappedRenderType.class)
public abstract class MixinOuterWrappedRenderType implements ETFRenderLayerWithTexture {


    @Shadow
    public abstract RenderType unwrap();

    @Override
    public Optional<ResourceLocation> etf$getId() {
        if (unwrap() instanceof ETFRenderLayerWithTexture etf)
            return etf.etf$getId();
        return Optional.empty();
    }
}
#endif
