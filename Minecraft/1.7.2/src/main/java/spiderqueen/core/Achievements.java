package spiderqueen.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import com.radixshock.radixcore.core.IEnforcedCore;
import com.radixshock.radixcore.core.RadixCore;

public final class Achievements
{
	private static final Map<IEnforcedCore, List<Achievement>> registeredAchievements = new HashMap<IEnforcedCore, List<Achievement>>();

	public static Achievement createNewAchievement(IEnforcedCore mod, String id, int posX, int posY, Item itemIcon, Achievement prerequisiteAchievement)
	{
		final Achievement returnAchievement = new Achievement(id, id, posX, posY * -1, itemIcon, prerequisiteAchievement).registerStat();
		addAchievementToMap(mod, returnAchievement);
		return returnAchievement;
	}

	public static Achievement createNewAchievement(IEnforcedCore mod, String id, int posX, int posY, Block blockIcon, Achievement prerequisiteAchievement)
	{
		final Achievement returnAchievement = new Achievement(id, id, posX, posY * -1, blockIcon, prerequisiteAchievement).registerStat();
		addAchievementToMap(mod, returnAchievement);
		return returnAchievement;
	}

	public static Achievement createNewAchievement(IEnforcedCore mod, String id, int posX, int posY, Item itemIcon)
	{
		final Achievement returnAchievement = new Achievement(id, id, posX, posY * -1, itemIcon, null).registerStat();
		addAchievementToMap(mod, returnAchievement);
		return returnAchievement;
	}

	public static Achievement createNewAchievement(IEnforcedCore mod, String id, int posX, int posY, Block blockIcon)
	{
		final Achievement returnAchievement = new Achievement(id, id, posX, posY * -1, blockIcon, null).registerStat();
		addAchievementToMap(mod, returnAchievement);
		return returnAchievement;
	}

	public static AchievementPage createAchievementPage(IEnforcedCore mod, String buttonName)
	{
		final List<Achievement> achievementList = registeredAchievements.get(mod);
		final AchievementPage returnPage = new AchievementPage(buttonName, achievementList.toArray(new Achievement[achievementList.size()]));
		AchievementPage.registerAchievementPage(returnPage);
		return returnPage;
	}

	public static void exportAchievementsToLangFile(IEnforcedCore mod, String destination)
	{
		try
		{
			final List<Achievement> achievementList = registeredAchievements.get(mod);
			final FileOutputStream outputStream = new FileOutputStream(destination);
			final Properties properties = new Properties();

			for (Achievement achievement : achievementList)
			{
				properties.put("achievement." + achievement.statId, "");
				properties.put("achievement." + achievement.statId + ".desc", "");
			}
			
			properties.store(outputStream, "");
			outputStream.close();
		}

		catch (FileNotFoundException e)
		{
			RadixCore.getInstance().quitWithException("Destination directory is malformed or doesn't exist.", e);
		}
		
		catch (IOException e)
		{
			RadixCore.getInstance().quitWithException("Unexpected IOException when exporting achievements.", e);
		}
	}

	private static void addAchievementToMap(IEnforcedCore mod, Achievement achievement)
	{
		List<Achievement> modAchievements = registeredAchievements.get(mod);

		if (modAchievements == null)
		{
			modAchievements = new ArrayList<Achievement>();
		}

		modAchievements.add(achievement);
		registeredAchievements.put(mod, modAchievements);
	}
}
