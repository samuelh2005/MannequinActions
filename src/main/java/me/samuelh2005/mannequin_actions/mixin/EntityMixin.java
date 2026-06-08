package me.samuelh2005.mannequin_actions.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.Mannequin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.samuelh2005.mannequin_actions.interactions.EntityHandler;
import me.samuelh2005.mannequin_actions.interactions.MannequinEvents.PlayerInteractEvent;
import me.samuelh2005.mannequin_actions.items.MannequinWandItem;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(at = @At("HEAD"), method = "interact", cancellable = true)
	private void interact(final Player player, final InteractionHand hand, final Vec3 location, CallbackInfoReturnable<InteractionResult> cir) {
		Level level = player.level();
		// Only run on the server side, the sever is authoritative for interactions and state mutation.
		if (level.isClientSide()) return;

		Entity entity = (Entity)(Object)this;
		if (entity instanceof Mannequin mannequin) {
			// Check if hand is holding the mannequin editor. If so, pass the event to the editor instead of emitting it to the mannequin's events.
			if (player.getItemInHand(hand).getItem() instanceof MannequinWandItem editor) {
				InteractionResult result = editor.onInteractMannequin(player, mannequin, hand, location);
				if (result != InteractionResult.PASS) {
					cir.setReturnValue(result);
				}
				return;
			}

			// Otherwise, emit the event to the mannequin's events.
			PlayerInteractEvent event = new PlayerInteractEvent(player, mannequin, hand, location);
			InteractionResult result = EntityHandler.emit(mannequin, event);
			if (result != InteractionResult.PASS) {
				cir.setReturnValue(result);
			}
		}
	}
}
