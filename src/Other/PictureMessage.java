package Other;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class PictureMessage extends Message {
    // Message with an additional variable for image path e.g. "C:\Users\..."
    //private String imagePath;
    private int pictureId;
    private byte[] imageBytes;

    public PictureMessage(String sender, int chatID, String imagePath) {
        super(sender, chatID, "");
        IdGenerator generator = IdGenerator.getInstance();
        this.pictureId = generator.generateId();

        try {
            this.imageBytes = PictureMessage.toImageBytes(imagePath);
        } catch (IOException e) {
            System.out.println("ERROR: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public PictureMessage(int msgId, int pictureId, String sender, int chatID, LocalDateTime time, byte[] image) {
        super(msgId, sender, chatID, "", time);
        this.pictureId = pictureId;
        this.imageBytes = image;
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