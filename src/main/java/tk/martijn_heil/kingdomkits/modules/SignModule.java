package tk.martijn_heil.kingdomkits.modules;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import tk.martijn_heil.kingdomkits.KingdomKitsSign;
import tk.martijn_heil.kingdomkits.model.COnlinePlayer;
import tk.martijn_heil.kingdomkits.model.PlayerClass;
import tk.martijn_heil.kingdomkits.util.SignActionType;
import tk.martijn_heil.kingdomkits.util.Signs;
import tk.martijn_heil.nincore.api.Core;
import tk.martijn_heil.nincore.api.CoreModule;
import tk.martijn_heil.nincore.api.entity.NinOnlinePlayer;

/**
 * Handles all sign related operations and commands.
 */
public class SignModule extends CoreModule implements Listener
{

    public SignModule(Core core)
    {
        super(core);
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        if(e.getClickedBlock() == null) return;
        if(!(e.getClickedBlock() instanceof Sign)) return;
        Sign sign = (Sign) e.getClickedBlock();

        if(!Signs.isKingdomKitsSign(sign)) return;

        KingdomKitsSign ks = new KingdomKitsSign(sign);

        if(ks.getSignActionType().equals(SignActionType.SET_CLASS))
        {
            if(!PlayerClass.PlayerClassExists(ks.getValue()))
            {
                NinOnlinePlayer.fromPlayer(e.getPlayer()).sendError("That player class does not exist.");
                return;
            }

            PlayerClass pc = new PlayerClass(ks.getValue());
            new COnlinePlayer(e.getPlayer().getUniqueId()).setPlayerClass(pc);
        }
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e)
    {
        if(e.getPlayer().hasPermission("kingdomkits.signs.create.switchclass") &&
                Signs.isKingdomKitsSign(e.getLine(0)))
        {
            if(!SignActionType.isValidSignActionType(e.getLine(1)))
            {
                NinOnlinePlayer.fromPlayer(e.getPlayer()).sendError("Invalid sign action type.");
                return;
            }

            // Validation passed, make it a kingdomkits sign.
            e.setLine(0, Signs.getKingdomKitsPrefix());
        }
    }
}