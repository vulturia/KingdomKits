package tk.martijn_heil.kingdomkits.model;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tk.martijn_heil.nincore.api.entity.NinOnlinePlayer;

import java.util.UUID;

public class COnlinePlayer extends COfflinePlayer
{

    /**
     * Constructor
     *
     * @param uuid The player's UUID
     */
    public COnlinePlayer(UUID uuid)
    {
        super(uuid);
    }


    public NinOnlinePlayer toNinOnlinePlayer()
    {
        return NinOnlinePlayer.fromPlayer(this.toPlayer());
    }


    public Player toPlayer()
    {
        return Bukkit.getServer().getPlayer(uuid);
    }
}