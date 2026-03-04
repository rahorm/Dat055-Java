package Other;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class PictureMessage extends Message {
    // Message with an additional variable for image path e.g. "C:\Users\..."
    private String imagePath;
    private int pictureId;
    private byte[] imageBytes;

    public PictureMessage(String sender, int chatID, String imagePath) {
        super(sender, chatID, "");
        IdGenerator generator = IdGenerator.getInstance();
        this.pictureId = generator.generateId();
        this.imagePath = imagePath;
    }

    public PictureMessage(int msgId, int pictureId, String sender, int chatID, LocalDateTime time, String imagePath) {
        super(msgId, sender, chatID, "", time);
        this.pictureId = pictureId;
        this.imagePath = imagePath;
    }

    public byte[] getImageBytes(String imagePath) {
        try {
            this.imageBytes = toImageBytes(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.imageBytes;
    }

    private static byte[] toImageBytes(String imagePath) throws IOException {
        return Files.readAllBytes(new File(imagePath).toPath());

    }
}