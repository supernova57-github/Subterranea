package supernova57.subterranea.registry;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import supernova57.subterranea.main.Subterranea;
import supernova57.subterranea.potion.SBTREffect;

public class SBTREffectRegistry {

	public static final RegistryObject<SBTREffect> FROSTBITE = Subterranea.MOB_EFFECTS.register("frostbite", () -> new SBTREffect(MobEffectCategory.HARMFUL, 14545919));
	
	public static void register() {
		Subterranea.MOB_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

}
