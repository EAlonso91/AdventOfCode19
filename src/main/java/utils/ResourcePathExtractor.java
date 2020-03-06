package utils;

import java.io.File;
import java.nio.file.Path;

/**
 * @author Enrique Alonso
 */
public class ResourcePathExtractor {

	public static Path openResource(String resourcePath) {
		return new File(ResourcePathExtractor.class.getClassLoader()
				.getResource(resourcePath)
				.getFile()).toPath();
	}
}
