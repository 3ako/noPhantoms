package trustmc.phantomslimit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PhantomsLimit extends JavaPlugin {
    @Override
    public void onEnable() {
       this.getServer().getPluginManager().registerEvents(new EventHandler(),this);
    }

    class EventHandler implements Listener {

        public void onSpawnMob(CreatureSpawnEvent e){
            if (e.getEntity().getType().equals(EntityType.PHANTOM)){
                e.setCancelled(true);
            }
        }
    }

}
