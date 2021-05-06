package PL53.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entities.FormativeAction;
import Entities.Professional;
import Entities.Session;

public class FileGenerator {
	static String path = "Files" + File.separator;

	public static void generateFile(String sender, String receiver, String subject, List<String> body, String filename)
			throws IOException {

		// Generate email file content
		List<String> lines = Arrays.asList("<sender>" + sender + "</sender>", "<receiver>" + receiver + "</receiver>",
				"<subject>" + subject + "</subject>", "", "<body>");

		lines = new ArrayList<>(lines);
		lines.addAll(body);
		lines.add("</body>");

		// Determine path of file
		Path filePath = Paths.get(path + File.separator + filename);

		// Create path to file if not exists
		File file = filePath.toFile().getParentFile();
		if (!file.exists()) {
			file.mkdirs();
		}

		// Write content to file path
		Files.write(filePath, lines, StandardCharsets.UTF_8);
	}

	public static List<String> bodyConfirmationEnrollment(FormativeAction fA, Professional p, List<Session> ss,
			float fee, float payed) throws IOException {

		// Concatenate the content of the email to a list of strings
		List<String> lines = Arrays.asList("Dear " + p.getName() + " " + p.getSurname() + ",",
				"hereby we inform you that your registration for the formative action " + fA.getName()
						+ " is confirmed.");
		List<String> lines2 = Arrays.asList("We look forward to seeing you.", "Best regards", "The COIIPA Team");
		lines = new ArrayList<>(lines);
		lines2 = new ArrayList<>(lines2);

		
		// Add information about refund in case the payed amount exceeds the expected fee 
		if ((payed - fee)>0) {
			lines.add("We received a payment of " +  payed + "€ from you instead of the expected fee of "+ fee +"€.");

			lines.add("The exceeding amount of money will be refunded to you in the following days.");

		}
		// Include informations about the session
		lines.add("The session dates of this formative action are: ");
		for (int i = 0; i < ss.size(); i++) {
			Session s = ss.get(i);
			lines.add("- " + s.getSessionStart() + ", Location: " + s.getLocation());
		}
		lines.addAll(lines2);

		return lines;
	}

	public static List<String> bodyWarningEnrollment(FormativeAction fA, Professional p, float fee) throws IOException {

		// Concatenate the content of the email to a list of strings
		List<String> lines = Arrays.asList("Dear " + p.getName() + " " + p.getSurname() + ",",
				"hereby we inform you that your enrollment for the formative action " + fA.getName()
						+ "is in the pending payment list. From now, you will have 48 hours to made the corresponding payment.",
				"The fee is: " + fee + "�");
		List<String> lines2 = Arrays.asList("We look forward to seeing you.", "Best regards", "The COIIPA Team");
		lines = new ArrayList<>(lines);
		lines2 = new ArrayList<>(lines2);

		lines.addAll(lines2);

		return lines;
	}

	public static void deleteFolderRecursively() {

		File file = new File(path);
		if (file.exists()) {
			for (File subFile : file.listFiles()) {
				if (subFile.isDirectory()) {
					for (File subsubFile : subFile.listFiles()) {
						System.out.println(subsubFile);
						subsubFile.delete();
					}
					subFile.delete();
				} else {
					System.out.println(subFile);
					subFile.delete();
				}
			}
			file.delete();
		}
	}
}
