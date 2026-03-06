package Other;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

public class PictureMessage extends Message {
    private int pictureId;
    private byte[] imageBytes;

    public PictureMessage(String sender, int chatID, String imagePath, String message) {
        super(sender, chatID, message);
        IdGenerator generator = IdGenerator.getInstance();
        this.pictureId = generator.generateId();

        try {
            this.imageBytes = PictureMessage.toImageBytes(imagePath);
        } catch (IOException e) {
            System.out.println("ERROR: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public PictureMessage(int msgId, int pictureId, String sender, int chatID, LocalDateTime time, byte[] image, String message) {
        super(msgId, sender, chatID, message, time);
        this.pictureId = pictureId;
        this.imageBytes = image;
    }

    public byte[] getImageBytes() { return this.imageBytes; }

    private static byte[] toImageBytes(String imagePath) throws IOException {
        return Files.readAllBytes(new File(imagePath).toPath());

    }
    public String toString(){
        return "msgId: "+super.getMessageID()+", sender: "+this.getSender()+", chatId: "+this.getChatID()+", time: "+this.getTimestamp()+", content: "+this.getContent()+", hasImg: true, imgId: "+this.pictureId;
    }
}