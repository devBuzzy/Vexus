package pw.vexus.core.commands;

import net.cogzmc.core.Core;
import net.cogzmc.core.modular.command.*;
import net.cogzmc.core.player.CPlayer;
import org.bukkit.entity.Player;
import pw.vexus.core.VexusCommand;
import pw.vexus.core.VexusCore;

@CommandMeta(description = "Teleport the target player to you.")
@CommandPermission("vexus.tphere")
public class TpHereCommand extends VexusCommand {

    public TpHereCommand() {
        super("tphere");
    }

    @Override
    protected void handleCommand(CPlayer player, String[] args) throws CommandException {
        if (args.length == 0) throw new ArgumentRequirementException("You must specify a player to teleport!");
        CPlayer target;
        target = Core.getPlayerManager().getFirstOnlineCPlayerForStartOfName(args[0]);

        if (target == null) throw new ArgumentRequirementException("The player you specified is invalid!");

        Player tPlayer = target.getBukkitPlayer();
        Player sPlayer = player.getBukkitPlayer();
        tPlayer.teleport(sPlayer.getLocation());

        player.sendMessage(VexusCore.getInstance().getFormat("player-tped-here", new String[]{"<player>", target.getName()}));
    }
}
