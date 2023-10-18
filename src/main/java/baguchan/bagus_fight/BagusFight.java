package baguchan.bagus_fight;

import baguchan.enchantwithmob.mobenchant.MobEnchant;
import baguchan.enchantwithmob.registry.MobEnchants;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BagusFight.MODID)
public class BagusFight
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "bagus_fight";

    public static final DeferredRegister<MobEnchant> MOB_ENCHANT = DeferredRegister.create(MobEnchants.MOB_ENCHANT.getRegistryKey(), MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<MobEnchant> HOLD_OUT = MOB_ENCHANT.register("hold_out", () -> new EpicMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 4)).addAttributesModifier(EpicFightAttributes.STUN_ARMOR.get(), "71200505-2c73-a02a-23ec-1e988f51f1fe", 1.0F, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEnchant> IMPACT = MOB_ENCHANT.register("impact", () -> new EpicMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.VERY_RARE, 2)).addAttributesModifier(EpicFightAttributes.IMPACT.get(), "ef366709-b922-a9cd-503e-10346807c732", 0.25F, AttributeModifier.Operation.ADDITION));

    public BagusFight()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MOB_ENCHANT.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
}
