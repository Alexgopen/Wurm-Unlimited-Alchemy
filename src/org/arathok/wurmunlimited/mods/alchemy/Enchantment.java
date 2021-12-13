package org.arathok.wurmunlimited.mods.alchemy;


import com.wurmonline.server.Items;
import com.wurmonline.server.NoSuchItemException;
import com.wurmonline.server.Players;
import com.wurmonline.server.creatures.SpellEffects;
import com.wurmonline.server.items.*;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.spells.SpellEffect;

import java.util.Map;

public class Enchantment {

    static int oilTimer = Config.oilDuration * 1000;
    static Item i = null;
    static Player p = null;
    static Long wurmId = 0L;
    SpellEffect eff = null;

    public static void EnchantmentHandler() throws NoSuchItemException {


        for (Map.Entry<Long, Long> set : Alchemy.weaponsWithOils.entrySet()) {
            if (oilTimer < System.currentTimeMillis()) {

                wurmId = set.getKey();
                if (wurmId != null) {

                    i = Items.getItem(wurmId);
                    p = Players.getInstance().getPlayerOrNull(i.getOwnerId());
                    if (System.currentTimeMillis() < set.getValue()) {
                    i.deleteAllEffects();
                    p.getCommunicator().sendAlertServerMessage("The oil dried completely off your "+ i.getName() + " .");
                    i.setName(i.getObjectName());

                    }


                }
            }
        }
    }
}

