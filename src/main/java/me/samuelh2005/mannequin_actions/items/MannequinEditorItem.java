package me.samuelh2005.mannequin_actions.items;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import me.samuelh2005.mannequin_actions.MannequinActions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.Mannequin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class MannequinEditorItem extends SimplePolymerItem  {
    public MannequinEditorItem(Properties settings) {
        super(settings, Items.STICK);
    }

    public InteractionResult onInteractMannequin(Player player, Mannequin mannequin, InteractionHand hand,
            Vec3 location) {
        MannequinActions.LOGGER.info("Interacting with mannequin using editor item. Player: {}, Mannequin: {}, Hand: {}, Location: {}", player, mannequin, hand, location);
        return InteractionResult.SUCCESS;
    }
}
