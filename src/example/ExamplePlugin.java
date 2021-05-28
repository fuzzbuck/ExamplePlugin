package example;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.net.Administration.*;
import mindustry.world.blocks.storage.*;

public class ExamplePlugin extends Plugin{

    public static boolean doShutdown = false;
    @Override
    public void init(){
        Events.on(GameOverEvent.class, event -> {
            if (doShutdown)
                Core.app.exit();
        });
    }

    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("autoshutdown", "Turn on the autoshutdown upon gameover event", args -> {
            doShutdown = !doShutdown;
            Log.info("Auto Shutdown:", doShutdown);
        });
    }

    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register("info", "", "?", (args, player) -> {
            player.sendMessage("These [accent]games[/accent] are hosted by the [accent]<[/]io[accent]>[/] community.\nWant to create your own for [accent]free[/]? Visit [accent]https://discord.io.community");
        });

    }
}
