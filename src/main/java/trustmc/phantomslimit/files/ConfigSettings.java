package trustmc.phantomslimit.files;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ConfigSettings
{
    private static File file;
    private static FileConfiguration cfg;
    private static HashMap<String, String> cachedSettings;

    public ConfigSettings() {
        this.initializeConfig();
        this.existFile();
        setStandart();
        saveInHashmap();
        System.out.println("# Settings-Config loaded");
    }

    private void initializeConfig() {
        ConfigSettings.file = new File("plugins/PhantomsLimit", "settings.yml");
        ConfigSettings.cfg = (FileConfiguration) YamlConfiguration.loadConfiguration(ConfigSettings.file);
    }

    private void existFile() {
        final File dir = new File(ConfigSettings.file.getParent());
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!ConfigSettings.file.exists()) {
            try {
                ConfigSettings.file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ConfigSettings.cfg.load(ConfigSettings.file);
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InvalidConfigurationException e3) {
            e3.printStackTrace();
        }
    }

    private static void setStandart() {
        ConfigSettings.cfg.options().copyDefaults(true);
        ConfigSettings.cfg.addDefault("settings.limit", (Object)"en");
        saveFile();
    }

    private static void saveFile() {
        try {
            ConfigSettings.cfg.save(ConfigSettings.file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveInHashmap() {
        ConfigurationSection cs;
        if (ConfigSettings.cfg.isConfigurationSection("settings")) {
            cs = ConfigSettings.cfg.getConfigurationSection("settings");
        }
        else {
            cs = ConfigSettings.cfg.createSection("settings");
        }
        ConfigSettings.cachedSettings.clear();
        for (final String settings : cs.getKeys(true)) {
            ConfigSettings.cachedSettings.put(settings, ConfigSettings.cfg.getString("settings." + settings));
        }
    }

    public FileConfiguration getCfg() {
        return ConfigSettings.cfg;
    }

    public File getFile() {
        return ConfigSettings.file;
    }

    public HashMap<String, String> getCachedSettings() {
        return ConfigSettings.cachedSettings;
    }

    static {
        ConfigSettings.cachedSettings = new HashMap<String, String>();
    }
}