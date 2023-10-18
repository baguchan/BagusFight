package baguchan.bagus_fight.client;

import baguchan.enchantwithmob.api.IEnchantCap;
import baguchan.enchantwithmob.client.render.layer.EnchantLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.api.client.model.AnimatedMesh;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.renderer.EpicFightRenderTypes;
import yesman.epicfight.client.renderer.patched.layer.PatchedLayer;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import static baguchan.enchantwithmob.client.render.layer.EnchantLayer.ANCIENT_GLINT;
import static baguchan.enchantwithmob.client.render.layer.EnchantLayer.enchantSwirl;

public class PatchedEnchantLayer<T extends LivingEntity, P extends LivingEntityPatch<T>, M extends EntityModel<T>, A extends AnimatedMesh>  extends PatchedLayer<T, P, M, EnchantLayer<T, M>, A> {
    public AnimatedMesh currentMesh;
    public PatchedEnchantLayer() {
        super(null);
    }

    @Override
    protected void renderLayer(P p, T entitylivingbaseIn, EnchantLayer<T, M> tmEnchantLayer, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLightIn, OpenMatrix4f[] openMatrix4fs, float bob, float yRot, float xRot, float partialTicks) {
        float tick = (float)entitylivingbaseIn.tickCount + partialTicks;
        if (entitylivingbaseIn instanceof IEnchantCap cap) {
            if (cap.getEnchantCap().hasEnchant()) {
                AnimatedMesh animatedMesh = ClientEngine.getInstance().renderEngine.getEntityRenderer(entitylivingbaseIn).getMesh(p);
                if (this.currentMesh != null) {
                    float intensity = cap.getEnchantCap().getMobEnchants().size() < 3 ? (float) cap.getEnchantCap().getMobEnchants().size() / 3.0F : 3.0F;
                    float f = (float) entitylivingbaseIn.tickCount + partialTicks;
                    VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(EpicFightRenderTypes.triangles(enchantSwirl(cap.getEnchantCap().isAncient() ? ANCIENT_GLINT : ItemRenderer.ENCHANTED_GLINT_ENTITY)));
                    this.currentMesh.drawModelWithPose(poseStack, ivertexbuilder, packedLightIn, intensity, intensity, intensity, 1.0F, OverlayTexture.NO_OVERLAY, Armatures.getArmatureFor(p), openMatrix4fs);
                }else {
                    this.currentMesh = animatedMesh;
                    this.currentMesh.initialize();
                }
            }
        }

    }
}