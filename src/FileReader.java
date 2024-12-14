import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
	private Map<String, String> roomDescriptions;

	public FileReader(String filePath) {
		roomDescriptions = new HashMap<>();
		loadRoomDescriptions(filePath);
	}

	private void loadRoomDescriptions(String filePath) {
		try (BufferedReader br = BufferedReader(new FileReader(filePath))) {
			String line;
			String roomName = null;
			StringBuilder description = new StringBuilder();

			while ((line = br.readLine()) != null) {
				if (line.endsWith(":")) { // Room name line (ends with colon)
					if (roomName != null) { // Save the previous room and its description
						roomDescriptions.put(roomName, description.toString().trim());
					}
					roomName = line.substring(0, line.length() - 1).trim(); // Remove colon
					description = new StringBuilder(); // Reset description
				} else if (!line.isEmpty()) { // Append to the description
					description.append(line).append(" ");
				}
			}
			// Save the last room and its description
			if (roomName != null) {
				roomDescriptions.put(roomName, description.toString().trim());
			}
		} catch (IOException e) {
			System.err.println("Error reading room descriptions file: " + e.getMessage());
			// Consider adding more specific error handling or logging here,
			// such as logging the stack trace or notifying the user.
		}
	}

	private BufferedReader BufferedReader(FileReader fileReader) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRoomDescription(String roomName) {
		return roomDescriptions.getOrDefault(roomName, "Description not found.");
	}
}