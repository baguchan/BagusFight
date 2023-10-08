package baguchan.bagus_fight.mixin;

import baguchan.bagus_fight.client.PatchedEnchantLayer;
import baguchan.enchantwithmob.client.render.layer.EnchantLayer;
import baguchan.enchantwithmob.client.render.layer.EnchantedEyesLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.client.model.AnimatedMesh;
import yesman.epicfight.client.renderer.patched.entity.PatchedEntityRenderer;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;
import yesman.epicfight.client.renderer.patched.layer.EmptyLayer;
import yesman.epicfight.client.renderer.patched.layer.PatchedLayer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@Mixin(PatchedLivingEntityRenderer.class)
public abstract class PLivingRendererMixin<E extends LivingEntity, T extends LivingEntityPatch<E>, M extends EntityModel<E>, AM extends AnimatedMesh> extends PatchedEntityRenderer<E, T, LivingEntityRenderer<E, M>, AM> {

    public PLivingRendererMixin() {
        super();
    }

    @Inject(remap = false, method = "<init>", at = @At("TAIL"))
    public void init(CallbackInfo callbackInfo){

        addPatchedLayer(EnchantLayer.class, new PatchedEnchantLayer());
        addPatchedLayer(EnchantedEyesLayer.class, new EmptyLayer<>());
    }

    @Shadow
    public void addPatchedLayer(Class<?> originalLayerClass, PatchedLayer<E, T, M, ? extends RenderLayer<E, M>, AM> patchedLayer) {
    }
}
