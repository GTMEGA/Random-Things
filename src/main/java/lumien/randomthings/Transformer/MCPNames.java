package lumien.randomthings.Transformer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import net.minecraft.launchwrapper.Launch;

public class MCPNames
{
	private static Map<String, String> fields;
	private static Map<String, String> methods;

	static
	{
		if (mcp())
		{
			Path mappingDir = Paths.get(System.getProperty("user.home"), ".gradle", "caches", "minecraft", "net", "minecraftforge", "forge", "1.7.10-10.13.4.1614-1.7.10", "unpacked", "conf");

			fields = readMappings(mappingDir.resolve("fields.csv").toFile());
			methods = readMappings(mappingDir.resolve("methods.csv").toFile());
		}
		else
		{
			fields = methods = null;
		}
	}

	public static boolean mcp()
	{
		return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}

	public static String field(String srgName)
	{
		if (mcp())
		{
			return fields.get(srgName);
		}
		else
		{
			return srgName;
		}
	}

	public static String method(String srgName)
	{
		if (mcp())
		{
			return methods.get(srgName);
		}
		else
		{
			return srgName;
		}
	}

	private static Map<String, String> readMappings(File file)
	{
		if (!file.isFile())
		{
			throw new RuntimeException("Couldn't find MCP mappings.");
		}
		try
		{
			return Files.readLines(file, Charsets.UTF_8, new MCPFileParser());
		}
		catch (IOException e)
		{
			throw new RuntimeException("Couldn't read SRG->MCP mappings", e);
		}
	}

	private static class MCPFileParser implements LineProcessor<Map<String, String>>
	{

		private static final Splitter splitter = Splitter.on(',').trimResults();
		private final Map<String, String> map = Maps.newHashMap();
		private boolean foundFirst;

		@Override
		public boolean processLine(String line) throws IOException
		{
			if (!foundFirst)
			{
				foundFirst = true;
				return true;
			}

			Iterator<String> splitted = splitter.split(line).iterator();
			try
			{
				String srg = splitted.next();
				String mcp = splitted.next();
				if (!map.containsKey(srg))
				{
					map.put(srg, mcp);
				}
			}
			catch (NoSuchElementException e)
			{
				throw new IOException("Invalid Mappings file!", e);
			}

			return true;
		}

		@Override
		public Map<String, String> getResult()
		{
			return ImmutableMap.copyOf(map);
		}
	}
}
