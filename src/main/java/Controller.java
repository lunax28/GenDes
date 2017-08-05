import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import emoji4j.EmojiUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.lineSeparator;

public class Controller {

    @FXML
    private TextField upcTextField;

    @FXML
    private Label sourceLabel;

    @FXML
    private File sourceFolderPath;

    private File logFile;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private List<String> keywords = new ArrayList<String>(Arrays.asList("►Meditation and Mindfulness Practice◄\n" +
            "Instrumental background music to use during meditation retreats. This music is perfect to create the right atmosphere in your meditation room to practice mindfulness, deep meditation. It takes its inspiration from oriental asian meditation music, using concentration soothing sounds like tibetan singing bowls, tibetan monks’ om chants and nature sounds of birds, waters, crickets and forest sounds. It’s also good to use as ambient music on the guided meditations of Deepak Chopra and Osho, with a wide range of sounds that recall shamanic meditation and healing music for body, mind and spirit and out of body experiences.\n" +
            "#meditation #mindfulness #deep #guided #meditationmusic #zen #health #innerpeace #mindbody #kindness #wellness\n","►Relaxation Music◄\n" +
            "Relaxing is part of the experience of Meditation Relax Club, providing listeners and followers with amazing tracks for their ears and beautiful videos for their eyes. Within this instrumental music, heavily inspired by Enya and other new age music gurus, you will find soothing harp sounds, classical relaxing piano music, chilling flute melodies recorded with live nature sounds for all people who are looking for a moment of inner peace, far from stress and anxieties. Important in our production are the guided relaxation you can find on the channel, with soothing voices and ethereal music to guide you into a trance state of deep chill.\n" +
            "#relax #relaxation #peace #love #healing #happy #weekend #TGIF #stress #antistress #wellbeing\n","►Oriental Zen Music◄\n" +
            "India, China, Japan: these oriental countries have a long tradition of music that is able to generate a profound sense of relaxation and meditation. Here you will find both traditional music from the Orient, but also new interpretations of local music culture. Shakuhachi flute, hang drum, koto, sitar, gu zheng, duduk… These are only a part of the instrumental music you can find here and you can use for your personal session of meditation and relaxation. An amazing journey to the Far East, where they know well how to release their stress, free the mind and live a life full of joy and meaningful experiences.\n" +
            "#zen #japan #china #india #orientalmusic #harmony #inspiration #silence #serenity #buddha\n","►Sleep Music for Dreams and Rest◄\n" +
            "Sleeping music lullabies and calming soothing songs with relaxing sounds of nature to help you put you to sleep. Some of these songs contain special sounds and frequencies that are said to help you sleep and relax at night, as a natural sleep aid against sleep problems or disorders like insomnia. All our sleep music has been created to help you fall asleep faster and spend a restful and regenerating night. Not to mention our sleep music playlists made for little babies and children, to help them sleep at night and to calm them, using some particular magic box lullabies.\n" +
            "#goodnight #mystic #nature #zen #smile #love #night #sleep #drems #luciddream #rest #relax\n","►Binaural Beats and α, β, γ, δ, θ brainwaves◄\n" +
            "Binaural beats and isochronic tones are special sounds emitted at specific ranges of hertz. These healing frequencies are often barely audible by the human ear, but their vibrations are synchronized with the same frequency at which our brain operates. Discover the full range of audio brainwaves: delta waves, gamma waves, alpha waves, theta waves, beta waves, each one with a specific use respectively. Sleep better, concentrate, relax and chill, improve study and memory, meditate better and free your mind. Look also for Solfeggio Frequencies and Schumann Resonance to complete this powerful set of very effective sounds.\n" +
            "#binaural #relaxation #ASMR #relaxmusic #whisper #chill #meditation #study #concentration #brainwaves\n","►Music for Study and Concentration◄\n" +
            "A good combination of brainwaves like alpha and gamma waves are able to help you with concentration, focus and exam preparation, using this studying music for stress relief during the exam sessions. Meditation Relax Club classical study music is also great as homework music or as background office music to listen to while working.\n" +
            "#study #learn #studytip #education #kids #concentration #studiotime #brain #brainwaves #memory #focus\n","►Spa & Massage Music◄\n" +
            "Meditation Relax Club has some wonderful music video playlists for spa and beauty centers. You can also play this spa massage music while having a spa day at home with some beauty treatments. Our light and peaceful spa music is a wonderful soundtrack for sauna and spa thermae for massage therapy. Oriental music is used to create a zen atmosphere, perfect for ayurveda, thai spa and relaxing healing massages.\n" +
            "#massage #spa #spamusic #wellness #wellnesscenter #relax #skincare #salon #fashion #beauty #healing\n","►Healing & Reiki◄\n" +
            "Positive meditation music is available online on our channel to help you reach positive thinking and affirmation. Spiritual healing music and reiki meditation music are mixed with uplifting melodies and celestial sounds for mind balance and zen vibrations, to take you to a higher level of consciousness; chakra music is also very popular here on Meditation Relax Club, for mind-body balance, center your crystals and heal the broken chakras with deep meditation.\n" +
            " #reiki #healing #healingmusic #soothing #spirituality #chakra #7chakras #meditation #yoga #massage #acupuncture\n","►Yoga Exercises Music and Pilates Relaxation◄\n" +
            "Here at Meditation Relax Club we create yoga and pilates songs with the help of experts from these disciplines. Some tunes are conceived for yoga practice, pilates stretching, help the natural breathing and cool down exercises. Our music is optimized also for sun salutation practice and yoga nidra for sleep. This music is influenced by indian tunes, using asian instruments like bamboo indian flute, sitar and chinese music with guzheng and harp.\n" +
            "#yoga #meditation #pilates #exercise #relaxation #sunsalutation #yogi #nidra #mudra #kundalini #workout\n","►Classical and Holiday Music◄\n" +
            "When that time of the year comes, Meditation Relax Club provides traditional music, celtic music, piano songs, classical and non-classical folk music to enlighten the listener’s holiday moments. May it be Christmas, Thanksgiving, Halloween, Easter or other calendar holidays, here you will always find the best instrumental music soundtrack to create the perfect atmosphere, chill out and release your stress.\n" +
            "#holiday #christmas #travel #classical #classicalmusic #traditional #folk #blackfriday #halloween #NYE #music\n"));

    @FXML
    private final String TOP_DESCRIPTION = " \uD83D\uDC4D Social Connections: \n" +
            "ⓕ Facebook: https://www.facebook.com/MeditationRelaxClub\n" +
            "ⓣ Twitter: https://twitter.com/MeditationRClub\n" +
            "ⓟ Pinterest: http://www.pinterest.com/meditationrelax/\n" +
            "ⓖ Google+: http://plus.google.com/+meditationrelaxclub/ \n" +
            "\n" +
            "\uD83C\uDFB5 Discography:\n" +
            "► https://itunes.apple.com/artist/id576613424#see-all/albums\n" +
            "► https://open.spotify.com/artist/39t4EeLBfpT72UQJVkIeuj\n" +
            "► http://www.deezer.com/artist/4624253\n" +
            "\n" +
            "Meditation Relax Club is not only a simple free relaxing music provider on YouTube. It’s overall the most famous and prepared music stream of instrumental meditation music to bring harmony and peace combined with balance in your life, once you choose which music you want to play. We have a wide selection of songs for relaxation, deep meditation, yoga exercises, study and concentration, restful sleep and dreams, music to de-stress, healing music and much more.\n" +
            " \n" +
            "\n" +
            "Some of our best videos are for:\n\n";

    @FXML
    private final String BOTTOM_DESCRIPTION = "\n\nMeditation Relax Club is also a world wide music label, mother of hundreds of top selling albums across countless nations, which can boast a proud catalog capable of satisfying the musical needs of the most avid and demanding New Age enthusiasts. More Youtube channels have stemmed from the main one, each one of which was tailored to suit a specific need from our public:\n" +
            "\n" +
            "☮ Meditate lost in the asian vibes of Buddha Tribe\n" +
            "♫ https://www.youtube.com/buddhatribe\n" +
            "\n" +
            "✿ Fall asleep with the gentle notes of Sleep Music Relax Zone \n" +
            "♫ https://www.youtube.com/sleepmusicrelaxzone\n" +
            "\n" +
            "\uD83C\uDF20  Enjoy 8 hours or more of sleep with Sleep Music Lullabies\n" +
            "♫ https://www.youtube.com/sleepmusiclullabies\n" +
            "\n" +
            " \uD83C\uDF0A Relax with soft music and nature sounds on RelaxRiver\n" +
            "♫ https://www.youtube.com/relaxriverofficial\n" +
            "\n" +
            "\uD83C\uDF34 Lay back through the enticing ambience of Chillout Lounge Relax\n" +
            "♫ https://www.youtube.com/chilloutloungerelax\n" +
            "\n" +
            "\uD83D\uDC44 Live your most intimate moments with Sensual Music Club\n" +
            "♫ https://www.youtube.com/sensualmusicclub\n" +
            "\n" +
            "All together these channels reach the amazing audience of more than ❤ 1,5 million ❤ of subscribers (and counting...)! Be part of our success... subscribe now!\n";

    @FXML
    public void locateFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        this.sourceFolderPath = fileChooser.showOpenDialog(new Stage());

        if (this.sourceFolderPath != null) {
            this.sourceLabel.setText(this.sourceFolderPath.getAbsolutePath().toString());
        }


    }

    @FXML
    public void clearTextArea(){

        this.upcTextField.clear();


    }


    @FXML
    public void checkApiArtists() {


        iTunesApiQueryUtils apiClass = new iTunesApiQueryUtils();
        String link = "";

        Scanner scanner = null;
        String tmp = "";
        String tmpFormat = "";

        BufferedWriter bw = null;
        FileWriter fw = null;

        //this.logFile = new File(this.sourceFolderPath + "/UpcChecker.txt");

        //try {


            //fw = new FileWriter(this.sourceFolderPath);
            //bw = new BufferedWriter(fw);

            tmp = upcTextField.getText();
            tmpFormat = tmp.trim();
            link = ("https://itunes.apple.com/lookup?upc=" + tmpFormat);
            System.out.println("LINK: " + link);


            JsonObject json = apiClass.getJson(link);

            if (json == null) {
                //JOptionPane.showMessageDialog(this, "RATE LIMIT!\nWait a few seconds before resuming the API CALLS\nRefer to the developer for further info", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int result = json.get("resultCount").getAsInt();

            System.out.println("APIGUI JSON: " + json.toString());
            System.out.println("RESULTCOUNT: " + result);

            if (result > 0) {

                JsonArray jArray = json.get("results").getAsJsonArray();

                JsonObject jsonObjArr = jArray.get(0).getAsJsonObject();

                String albumUrl = jsonObjArr.get("collectionViewUrl").getAsString();

                System.out.println("ALBUM URL: " + albumUrl);

                this.generateDescription(albumUrl);


                //bw.write(tmp + "," + lineSeparator());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("No iTunes Albums associated with this UPC.\nInsert a different UPC.");
                alert.showAndWait();

                return;

            }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("DONE!");
        //alert.setContentText("The results are saved in your .txt file!");

        alert.showAndWait();


    }

    private void generateDescription(String albumUrl) {


        StringBuilder builder = new StringBuilder(EmojiUtils.getEmoji("white_check_mark").getEmoji() + " Full album on iTunes & AppleMusic: ");
        builder.append(albumUrl);

        builder.append("\n✅ Join the MRC community: http://meditationrelaxclub.com/\n\n");

        builder.append(this.descriptionTextArea.getText()+"\n\n");

        builder.append(this.TOP_DESCRIPTION);

        builder.append("\n\n");

        //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        builder.append(this.keywords.get(ThreadLocalRandom.current().nextInt(0, this.keywords.size())));

        builder.append("\n\n");

        builder.append(this.BOTTOM_DESCRIPTION);

        System.out.println(builder);

        this.resultTextArea.setText(builder.toString());



    }


    public void testEmoji() {

        System.out.println(EmojiUtils.getEmoji("blue_car").getEmoji());


    }
}
