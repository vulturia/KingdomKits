package tk.martijn_heil.kingdomkits.modules;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import tk.martijn_heil.kingdomkits.KingdomKits;
import tk.martijn_heil.kingdomkits.util.ItemStacks;
import tk.martijn_heil.nincore.api.CoreModule;


/**
 * Handles unbreakable items.
 */
public class UnbreakableItemsModule extends CoreModule<KingdomKits> implements Listener
{

    public UnbreakableItemsModule(KingdomKits core)
    {
        super(core);
    }


    @Override
    public void onEnable()
    {
        this.getLogger().info("Registering event handlers..");
        Bukkit.getPluginManager().registerEvents(this, this.getCore());
    }


    @Override
    public String getName()
    {
        return "UnbreakableItemsModule";
    }


    @EventHandler(priority = EventPriority.HIGHEST) // Prevent soulbound items from being damaged upon use.
    public void onPlayerItemDamage(PlayerItemDamageEvent e)
    {
        // Soulbound items cannot be damaged
        if (ItemStacks.isUnbreakable(e.getItem()))
        {
            e.setCancelled(true);
            e.getPlayer().updateInventory();
        }
    }
}
