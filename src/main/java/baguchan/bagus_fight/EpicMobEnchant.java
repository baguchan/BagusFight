package baguchan.bagus_fight;

import baguchan.enchantwithmob.mobenchant.MobEnchant;

public class EpicMobEnchant extends MobEnchant {
    public EpicMobEnchant(Properties properties) {
        super(properties);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 30;
    }

    protected boolean canApplyTogether(MobEnchant ench) {
        return super.canApplyTogether(ench);
    }
}