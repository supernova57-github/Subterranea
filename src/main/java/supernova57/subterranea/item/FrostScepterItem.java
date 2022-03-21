package supernova57.subterranea.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import supernova57.subterranea.entity.projectile.SnowflakeEntity;

public class FrostScepterItem extends Item {

	public FrostScepterItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		double damage = this.getDamage(player.getItemInHand(hand));
		if (damage <= 19 && !world.isClientSide) {
			SnowflakeEntity snowflake = new SnowflakeEntity(player, world);			
			snowflake.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 0.0F);
			world.addFreshEntity(snowflake);
			if (!player.getAbilities().instabuild) {
				this.setDamage(player.getItemInHand(hand), (int)damage + 1);
			}
			return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), false);
		}
		player.swing(hand);
		return InteractionResultHolder.fail(player.getItemInHand(hand));
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		if (entity instanceof SnowflakeEntity) stack.setDamageValue(stack.getDamageValue() - 1);
		return false;
	}
	
	@Override
	public int getMaxDamage(ItemStack stack) {
		return 20;
	}
}
