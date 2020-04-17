package day2.programalarm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.ResourcePathExtractor;

public class CalculateNounAndVerb {

	private final static String DESIREDOUTPUT = "19690720";
	private static String intcode;
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculateNounAndVerb.class);

	static{
		try {
			Path path = ResourcePathExtractor.openResource("Day2/Day2Input1.txt");
			intcode = new String(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int calculatePairParameters(){
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				String outputSequence = IntcodeComputer.runIntcode(IntcodeComputer.applyTransformations(intcode, i, j));
				if(outputSequence.split(",")[0].equals(DESIREDOUTPUT)){
					LOGGER.info("Noun: "+i);
					LOGGER.info("Verb: "+j);
					return 100 * i + j;
				}
			}
		}
		throw new RuntimeException("Invalid sequence. Parameter combination not found for desired output");
	}
}
