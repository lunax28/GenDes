import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {

    @FXML
    private TextField upcTextField;

    @FXML
    private Label sourceLabel;

    //@FXML
    //private File sourceFolderPath;

    //private File logFile;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private List<String> enKeywords = new ArrayList<String>(Arrays.asList("►Meditation and Mindfulness Practice◄\n" +
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
    private List<String> esKeywords = new ArrayList<String>(Arrays.asList("►Música para meditación y de atención plena◄\n" +
            "Música de fondo instrumental para escuchar en tu espacio de meditación. Estas músicas están inspirada en la música oriental de Asia, con sonidos para la concentración como las campanas tibetanas y el canto de los monjes tibetanos y otros sonidos de la naturaleza como el canto de los pájaros y el sonido del agua. Música de ambiente ideal para las meditaciones guiadas de Deepak Chopra y Osho, con referencias a la música chamánica para sanar tu cuerpo, espíritu y alma.\n","►Música Relajante◄\n" +
            "La relajación es una parte muy importante de Meditation Relax Club, que regala a su oyentes y seguidores videos con imágenes bonitas y canciones suaves. Toda esta música de relajación encuentra su inspiración en la música de Enya y otros gurú de la música new age, con sonidos del arpa, música de piano clásica relajante y melodías de flauta con sonidos naturales para todas las personas que buscan un momento de paz interior, lejos del estrés y de las ansiedades. En nuestra producción hay también las relajaciones guiadas con voces calmantes y música etérea.\n","►Música Zen Oriental◄\n" +
            "India, China y Japón son tres países con una larga tradición de música para relajarse y meditar. En este canal encontrará la música tradicional asiática y nuevas reinterpretaciones según la tradición local de Oriente. Los instrumentos más utilizados son la flauta shakuhachi, hang drum, koto, sitar, guzheng y duduk para un viaje completo en el Lejano Oriente contra el estrés, para una experiencia significativa y de alegría.\n","►Música para Dormir, Soñar y Descansar◄\n" +
            "Música para dormir y canciones suaves con sonidos relajantes para ayudarte a dormir. Algunas de estas canciones tienen sonidos y frecuencias especiales para dormir y descansar toda la noche, como una ayuda natural contra los trastornos del sueño como el insomnio. Toda nuestra música para dormir te ayuda a conciliar el sueño más rápidamente y pasar una noche tranquila y reparadora. Meditation Relax Club ha creado también algunas playlist para los bebés y los niños con el carillón.\n","►Sonidos binaurales y ondas cerebrales α, β, γ, δ, θ◄\n" +
            "Ritmo binaural y tonos isocrónicos son sonidos especiales emitidos en rangos específicos de hertz. Aquì tenemos música con vibraciones particulares para sanar el alma y el espíritu, con ondas delta para dormir, ondas gamma, alpha, theta y beta, cada una con un uso específico como, por ejemplo, para dormir, concentrarse, relajarse, mejorar el estudio, meditar y liberar la mente. Las más famosas de estas frecuencias son las frecuencia de solfeo y la resonancia Schumann.\n", "►Música para estudiar y concentrarse◄\n" +
            "Una buena combinación de ondas cerebrales como la alpha y gamma pueden ayudarte con la concentración para la preparación de los exámenes, escuchando la música para estudiar para aliviar el estrés durante la sesión de examen. La música clásica para estudiar de Meditation Relax Club se puede escuchar también para hacer las tareas o para trabajar en la oficina.\n","►Música Spa para Masajes◄\n" +
            "Meditation Relax Club ha creado algunas playlist de música para spa y centro de belleza. Puede también escuchar estas canciones relajantes en tu casa para uno spa day con tratamientos de bienestar. Nuestra música ligera y suave es perfecta para sauna y centros termales, para la terapia de los masajes. La música oriental va a crear una atmósfera perfecta para ayurveda, thai spa y masajes curativos relajantes.\n","►Música Reiki◄\n" +
            "Escucha en nuestro canal la música para la meditación positiva, para llegar al poder del pensamiento positivo de afirmación. La música de sanación y la música reiki se mezclan con melodías edificantes y sonidos celestiales para el equilibrio de la mente y las vibraciones zen. Importante es también la música chakra para sanar los chakras del cuerpo humano con la meditación profunda.\n", "►Música para Ejercicios de Yoga y Pilates◄\n" +
            "Con la colaboración de los más grandes expertos de yoga y pilates hemos creado las músicas adecuadas para estas disciplinas. Las canciones relajantes yoga existen para hacer los ejercicios, el stretching de pilates y te ayudan con la respiración natural. Estas canciones relajantes están diseñadas también para el saludo al sol de yoga nidra, para dormir, como las músicas de la India, con los instrumentos asiáticos como la flauta de bambú indiana, el sitar y la música china con guzheng y arpa.\n", "►Música Clásica para las vacaciones◄\n" +
            "Cuando llegan las vacaciones, Meditation Relax Club no se toma por sorpresa y va a crear las músicas tradicionales para las festividades como la música de Navidad, de Pascua, para el Dìa de la acción de gracias y Halloween, con música instrumental popular y las mejores canciones tradicionales de este tiempo.\n"));

    @FXML
    private List<String> deKeywords = new ArrayList<String>(Arrays.asList("►Meditation und Achtsamkeit◄\n" +
            "Instrumentale Hintergrundmusik für Meditation Zufluchtsort. Diese Musik ist perfekt, eine transzendentale Atmosphäre in deinem Meditation Zimmer zu nachschaffen. Das ist sehr gut für Achtsamkeitsmeditation und buddhistische Meditation. Diese Musik wird von der ostasiatischen Musik inspiriert, mit Konzentrationsklängen der tibetischen Klangschalen und Gesänge von tibetischen Mönchen, aber auch Naturgeräusche wie Vogelgezwitscher und Meeresrauschen. Diese sanfte Musik ist perfekt für Deepak Chopra und Osho geführte Meditation mit schamanischen Musik für Körper, Seele und Geist.\n", "►Entspannungsmusik◄\n" +
            "Entspannung ist ein wichtiger Teil der Erfahrung von Meditation Relax Club. Wir haben wunderbare Lieder und schöne Videos mit beruhigenden Bildern. Diese Instrumentalmusik ist stark von Enya und anderen New Age Musik Guru inspiriert, mit Harfenmusik, klassischen Klavier Musik zur Entspannung und Flöte Melodien mit Naturgeräuschen für eine Momente des Friedens von Stress und Angst. Wichtig in unserer Produktion sind die geführte Entspannungen, die du auf dem Kanal finden kannst, mit beruhigenden Stimmen und ätherischer Musik.\n","►Orientalische Zen Musik◄\n" +
            "Indien, China und Japan: diese orientalischen Länder haben eine lange Tradition der Entspannungsmusik zur Meditation. Hier kannst du traditionelle Musik aus Orient finden, aber auch neue Interpretationen der lokalen Traditionen. Die Instrumente diese Meditationsmusik sind: Shakuhachi Flöte, Hang Drum, Koto, Sitar, Guzheng, Duduk. Die ganze Sammlung dieser orientalischen Musik ist zum Stress abbauen, für ein Leben der Freude und aussagekräftige Erfahrung.\n", "►Schlafmusik zum Träumen und Ausruhen◄\n" +
            "Schlaflieder und sanfte beruhigende Musik mit Entspannungsklängen zum einschlafen. Einige dieser Lieder enthalten Töne und Frequenzen in der Nacht zum schlafen und entspannen, gegen Schlafstörungen wie Schlaflosigkeit. Unsere Schlafmusik wurde geschaffen, um dir zu helfen, schneller zu schlafen und eine erholsame Nacht zu verbringen. Wir haben auch Playlists mit Wiegenlieder für Babys und Kleinkinder mit Glockenspiel durch die Nacht zum schlafen.\n","►Binaurale Beats und α, β, γ, δ, θ Gehirnwellen◄\n" +
            "Binaurale Beats und isochronische Töne sind spezielle Klänge, die in bestimmten Bereichen von Hertz emittiert werden. Diese heilende Frequenzen sind oft kaum vom menschlichen Ohr hörbar, aber ihre Vibrationen sind mit der gleichen Frequenz synchronisiert, an der unser Gehirn arbeitet. Unsere Audio haben Delta Wellen, Gamma und Alpha Wellen, Theta und Beta Wellen und sie haben verschiedene Nutzungen. Besser schlafen, konzentrieren, entspannen und chillen, Verbesserung der Studie und Gedächtnis, Meditation und Wohlfühlung. Sehr bekannt sind auch die Solfeggio Frequenzen und Schumann Resonanz.\n","►Musik zum Lernen für Konzentration◄\n" +
            "Eine gute Kombination von Gehirnwellen wie Alpha- und Gamma Wellen können dir bei Konzentration, Fokus und Prüfungsvorbereitung helfen, für Stressabbau während der Prüfungssitzungen. Diese Musik ist sehr gut für Hausaufgaben und als Arbeitsmusik im Büro zu hören.\n", "►Spa & Massage Musik◄\n" +
            "Meditation Relax Club hat auch wunderbare Musik Video Playlist für Spa und Schönheitszentrum. Gut auch für einen Spa Tag zu Hause fūr Schönheits- Spa Behandlungen. Lichte Wellness Spa Musik für Sauna, Thermae und Massage Therapie. Orientalische Lieder schaffen eine Atmosphäre perfekt für Ayurveda, Thai Spa und entspannende Ganzkörpermassage\n","►Heilung & Reiki◄\n" +
            "Positive Meditationsmusik ist online verfügbar auf unserem Kanal, um Ihnen zu helfen, positives Denken und Bejahung zu erreichen. Spirituelle Heilmusik und Reiki Meditationsmusik sind mit erhebenden Melodien und himmlischen Klängen für Geist Balance und Zen Vibrationen gemischt, um dich auf ein höheres Bewusstseinsniveau zu bringen. Chakra-Musik ist auch sehr beliebt hier in Meditation Relax Club, für Körper-Geist Balance, die gebrochene Chakras mit tiefen Meditation zu heilen. \n","►Musik für Yoga Übungen und Pilates Entspannung◄\n" +
            "Meditation Relax Club hat einige sehr wohltuende Yoga und Pilates Musik, die mit der Hilfe von Experten kreirt wurden. Diese Lieder sind sehr gut für Yoga Kürse, Stretching, Atemübungen und Abkühlen Übungen. Unsere Musik ist auch für die Yoga Sonnengruß Praxis und Yoga Nidra für den Schlaf optimiert. Diese Musik wird von indischen Melodien beeinflusst, mit asiatischen Instrumenten wie Bambus indian Flöte, Sitar und chinesische Musik mit Guzheng und Harfe.\n","►Klassische Musik für Ferien◄\n" +
            "Wenn diese Zeit des Jahres kommt, bietet Meditation Relax Club traditionelle Musik, keltische Musik, Klavierlieder, klassische und nicht-klassische Volksmusik, um die Urlaubsmomente des Zuhörers zu verdeutlichen. So haben wir Weihnachtsmusik, Musik zum Erntedankfest, Halloween und Ostern, mit Instrumentalmusik zum entspannen, chillen und Stress abbauen.\n"));


    @FXML
    private List<String> ptKeywords = new ArrayList<String>(Arrays.asList("►Musica de meditação e de Atenção plena◄\n" +
            "Musica de fondo instrumental para ouvir no espaço de meditação. Estas músicas são inspiradas pela música do Leste Asiático, com sons de concentração como sinos tibetanos e canto dos monges tibetanos e outros sons da natureza como os sons dos pássaros cantando e os sons da água. Hilo musical ideal para as meditações guiadas de Deepak Chopra e Osho, com referências à música xamânica para curar o seu corpo, espírito e alma.\n", "►Musica Relaxante◄\n" +
            "O relaxamento é uma parte muito importante da Meditação Relax Club, que dá seus ouvintes e seguidores vídeos com belas imagens e músicas suaves para ouvir. Tudo isso música de relaxamento encontra inspiração na música de Enya e outro guru da música new age, com sons da harpa, musica de piano clássico relaxante e melodias da flauta com sons naturais para as pessoas que procuram um momento de paz, longe do stress e ansiedades. Em nossa produção também são relaxamento guiados com vozes suaves e música etéreas.\n","►Musica Zen Oriental◄\n" +
            "Índia, China e Japão são três países com uma longa tradição de música para relaxamento e meditação. Neste canal, há tanto a música tradicional que a música reinterpretado segundo a tradição local do Oriente. Os instrumentos mais utilizados são flauta shakuhachi, hang drum, koto, sitar, guzheng e duduk para uma turnê completa no Extremo Oriente contra o stress, para uma experiência significativa e alegria.\n","►Musica para Dormir, Sonhar e Descansar◄\n" +
            "Musica para dormir e canções suaves com sons relaxantes para dormir profundamente. Algumas dessas canções têm sons e freqüências especiais para dormir e descansar toda a noite, como uma ajuda natural contra distúrbios do sono como insônia. Toda a nossa música para dormir ajuda a adormecer mais rápido e passar uma noite repousante e tranquila. Meditation Relax Club também criou alguns lista de reprodução para bebês e crianças com carrilhão.\n", "►Sons binaurais e ondas cerebrais α, β, γ, δ, θ◄\n" +
            "Ritmo binaural e tons isocrônico são sons especiais feitas em intervalos específicos de hertz. Aqui música com vibrações particulares para curar a alma eo espírito, com ondas delta para dormir, ondas gamma, alfa, theta e beta, cada um com um uso específico, por exemplo, para dormir, concentrar-se, relaxar, melhorar a estudar, meditar e libertar a mente. O mais famoso desses freqüências são a frequência de teoria musical e ressonância Schumann.\n","►Musica para estudar e concentrarse◄\n" +
            "Uma boa combinação de ondas cerebrais como alfa e gama podem ajudar com concentração para a preparação de exames, com musica para estudar e aliviar o estresse durante a sessão de exame. A musica classica para estudar de Meditation Relax Club é usado para fazer lição de casa o para trabalhar no escritório.\n","►Musica Spa para Massagens◄\n" +
            "Meditation Relax Club criou algumas lista de reprodução para spa e centro de beleza. Você também pode ouvir estas canções calmante em casa para um dia de spa com tratamentos de bem-estar. Nossa música suave é perfeita para sauna e spa para massagem terapêutica. Música do Oriente irá criar uma atmosfera perfeita para ayurveda, thai spa e massagens relaxantes.\n","►Musica Reiki◄\n" +
            "Ouvir o nosso canal de música para meditação positiva para alcançar o poder do pensamento positivo e afirmação. A música reiki e a música de cura misturado com melodias edificantes e sons celestiais são para o equilíbrio da mente e vibrações zen. Importante também é a música chakra para curar os chakras do corpo humano com a meditação profunda.\n","►Musica para Exercicios de Yoga e Pilates◄\n" +
            "Com a colaboração do maior especialistas de yoga e pilates, criamos as canções perfeitas para essas disciplinas. As musicas relaxantes de yoga são para fazer exercícios, stretching de pilates e respiração natural. Essas músicas suaves também são projetados para saudação ao sol e yoga nidra para dormir, com a música da Índia com instrumentos asiáticos como a flauta de bambu indiana, sitar e guzheng chinesa e música de harpa.\n","►Musica Classica de férias◄\n" +
            "Quando chegam as férias, Meditação Relax Club va a criar a música tradicional para festas como musica de Natal, de Páscoa, Ação de Graças e Halloween, com musica instrumental popular e as melhores musicas tradicionais deste período.\n"));

    @FXML
    private List<String> itKeywords = new ArrayList<String>(Arrays.asList("►Meditazione e Mindfulness◄\n" +
            "Musica di sottofondo strumentale per la meditazione. Questa musica è il sottofondo musicale ideale per la tua stanza della meditazione. Si ispira alla musica orientale asiatica, unendo suoni della natura come il canto degli uccellini o il rumore dell’acqua ad altri suoni new age per la concentrazione come le campane tibetane e i canti dei monaci tibetani. Può essere usata come musica ambient per le meditazioni guidate di Deepak Chopra e Osho.\n","►Musica Rilassante◄\n" +
            "Il rilassamento è parte dell’esperienza di Meditation Relax Club con dei bellissimi video con immagini rilassanti ed una selezione di brani musicali pensati per il riposo assoluto, ispirati alla musica di Enya ed altri guru della musica new age. All’interno di queste musiche di sottofondo vengono utilizzati strumenti musicali quali l’arpa, il pianoforte classico e le note del flauto dolce, insieme all’impiego di strumenti orientali e suoni della natura per chi è alla ricerca di un momento di tranquillità, lontano dallo stress e dall’ansia. Nel nostro canale inoltre si possono ascoltare anche rilassamenti guidati di training autogeno per ritrovare la serenità interiore ed esteriore.\n","►Musica Zen Orientale◄\n" +
            "L’India, la Cina e il Giappone: questi Paesi Orientali vantano una lunga tradizione di musica in grado di generare un profondo senso di rilassamento e la giusta concentrazione necessaria per la meditazione. Il nostro canale di YouTube è in grado di regalarti sia musica tradizionale dall’Oriente che reinterpretazioni musicali della cultura locale utilizzando strumenti come il flauto shakuhachi, hang drum, koto, sitar, gu zheng e duduk.\n","►Musica per Dormire, Sognare e Riposarsi◄\n" +
            "Canzoni per addormentarsi e dormire profondamente e ninne nanne con suoni della natura e frequenze per stimolare il sonno profondo, curando problemi e disturbi legati al sonno come l’insonnia. Tutte le nostre musiche per dormire sono state create per addormentarsi più velocemente e trascorrere una notte tranquilla e rigenerante. Abbiamo, inoltre, utili playlist di musica di carillon per far addormentare i neonati e i bambini.\n","►Suoni Binaurali e Onde Cerebrali α, β, γ, δ, θ◄\n" +
            "I toni binaurali e isocronici sono suoni speciali emessi ad uno specifico range di Hertz. Queste frequenze curative sono spesso inudibili all’orecchio umano, ma vengono avvertite e percepite dal cervello. Scopri con noi tutti i benefici di questi battiti binaurali detti anche onde delta, onde gamma, onde alfa, theta e beta, ognuna con una sua specifica caratteristica e un suo proprio utilizzo. Dormi meglio, concentrati, rilassati, migliora il tuo apprendimento, medita e libera la mente. Tra le più note ci sono anche le Frequenze di Solfeggio e la Risonanza Schumann.\n","►Musica per Studiare e per la Concentrazione◄\n" +
            "La corretta combinazione di onde cerebrali come le alpha e la gamma contribuisce ad aiutarti con la concentrazione per preparare gli esami, usando la musica per studiare per alleviare lo stress da sessione d’esami. La musica classica per studiare di Meditation Relax Club può essere usata per fare i compiti o come musica di sottofondo per lavorare e da ascoltare in ufficio.\n","►Musica per Spa & Massaggi◄\n" +
            "Meditation Relax Club ha creato nel corso degli anni anche delle fantastiche playlist per i centri spa e benessere. Non solo, questa musica rilassante può anche essere usata per uno spa day a casa tua quando decidi di concederti dei trattamenti di bellezza rigeneranti. La nostra musica calma e soave è la colonna sonora ideale per la sauna e le terme o per la terapia dei massaggi. La musica orientale riprodotta contribuisce a creare l’atmosfera zen perfetta per l’ayurveda, thai e massaggi curativi.\n","►Reiki Curativo◄\n" +
            "Sul nostro canale è disponibile anche la musica per la meditazione del pensiero positivo e l’affermazione. Si tratta di musica spirituale e musica meditativa per il reiki mixata con melodie edificanti e suoni celestiali per l’equilibrio della mente e le vibrazioni zen, per riportarti ad un più alto livello di consapevolezza di te stesso. Tra le musiche più popolari si conta la musica chakra per curare i chakra danneggiati e sofferenti con la meditazione profonda.\n","►Musica per Esercizi di Yoga e Pilates◄\n" +
            "Con l’aiuto dei massimi esperti nel campo dello yoga e del pilates abbiamo creato delle musiche yoga ideali per queste due discipline. Questa musica per il benessere è in grado di agevolare lo stretching, gli esercizi di respirazione e di rilassamento, tra i quali il saluto al sole e lo yoga nidra per dormire. Influenzata dalla musica indiana, contiene strumenti asiatici come il flauto indiano di bambù e il sitar, e dalla musica cinese con il guzheng e l’arpa.\n","►Musica Classica e Tradizionale per le Festività◄\n" +
            "Quando arriva un certo periodo dell’anno, Meditation Relax Club è in prima linea per ricreare la giusta atmosfera per feste e celebrazioni come il Natale, la Pasqua, il Ringraziamento, Halloween e molte altre, producendo le colonne sonore con musiche tradizionali, classiche, folk e rivisitazioni dei grandi classici che accompagneranno questi importanti avvenimenti.\n"));

    @FXML
    private ChoiceBox<String> choiceBoxLanguage;

    ObservableList<String> obsList = FXCollections.observableArrayList("EN","ES","DE","IT","PT");



    @FXML
    private void initialize(){

        this.choiceBoxLanguage.setValue("EN");
        this.choiceBoxLanguage.setItems(this.obsList);
    }



    /*
    @FXML
    public void locateFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        this.sourceFolderPath = fileChooser.showOpenDialog(new Stage());

        if (this.sourceFolderPath != null) {
            this.sourceLabel.setText(this.sourceFolderPath.getAbsolutePath().toString());
        }


    }
    */

    @FXML
    public void clearTextArea(){

        this.upcTextField.clear();


    }


    @FXML
    public void checkApiArtists() {

        if(this.upcTextField.getText().isEmpty() && this.descriptionTextArea.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Please, make sure you inserted a valid UPC and a video description");
            alert.showAndWait();

            return;


        }


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

                String idLink = albumUrl.substring(albumUrl.indexOf("id"),albumUrl.indexOf("?"));

                System.out.println("idLink: " + idLink);

                String shortlink = "https://itunes.apple.com/album/" + idLink;

                System.out.println("ALBUM URL: " + albumUrl);

                System.out.println("SHORT LINK: " + shortlink);

                switch(this.choiceBoxLanguage.getValue()){

                    case ("EN"): this.generateEnDescription(shortlink);
                                break;

                    case ("ES"): this.generateEsDescription(shortlink);
                                break;

                    case ("DE"): this.generateDeDescription(shortlink);
                                break;

                    case ("PT"): this.generatePtDescription(albumUrl);
                                break;

                    case ("IT"): this.generateItDescription(shortlink);
                                 break;

                }




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

    private void generateItDescription(String albumUrl) {

        StringBuilder builder = new StringBuilder(constantFields.IT_FIRST_SENTENCE.getField());
        builder.append(albumUrl);

        builder.append("\n" + constantFields.IT_SECOND_SENTENCE.getField()+"\n\n");

        builder.append(this.descriptionTextArea.getText()+"\n\n");

        builder.append(constantFields.IT_TOP_DESCRIPTION.getField());

        builder.append("\n\n");

        //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        //builder.append(this.esKeywords.get(ThreadLocalRandom.current().nextInt(0, this.esKeywords.size())));

        ArrayList<Integer> list = new ArrayList<Integer>(this.itKeywords.size());
        for(int i = 1; i <= this.itKeywords.size(); i++) {
            list.add(i);
        }

        Random rand = new Random();
        while(list.size() > 7) {
            System.out.println("this.itKeywords.size(): " + this.itKeywords.size());
            System.out.println("LIST: " + list);
            int index = rand.nextInt(list.size()-1);
            System.out.println("INDEX: " + index);
            builder.append(this.itKeywords.get(list.remove(index)));
            builder.append("\n");
        }

        list = new ArrayList<Integer>(this.itKeywords.size());
        for(int i = 1; i <= this.itKeywords.size(); i++) {
            list.add(i);
        }


        builder.append("\n\n");

        builder.append(constantFields.IT_BOTTOM_DESCRIPTION.getField());

        System.out.println(builder);

        this.resultTextArea.setText(builder.toString());
    }

    private void generatePtDescription(String albumUrl) {

        StringBuilder builder = new StringBuilder(constantFields.PT_FIRST_SENTENCE.getField());
        builder.append(albumUrl);

        builder.append("\n" + constantFields.PT_SECOND_SENTENCE.getField()+"\n\n");

        builder.append(this.descriptionTextArea.getText()+"\n\n");

        builder.append(constantFields.PT_TOP_DESCRIPTION.getField());

        builder.append("\n\n");

        //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        //builder.append(this.esKeywords.get(ThreadLocalRandom.current().nextInt(0, this.esKeywords.size())));

        ArrayList<Integer> list = new ArrayList<Integer>(this.ptKeywords.size());
        for(int i = 1; i <= this.ptKeywords.size(); i++) {
            list.add(i);
        }

        Random rand = new Random();
        while(list.size() > 7) {
            System.out.println("this.ptKeywords.size(): " + this.ptKeywords.size());
            System.out.println("LIST: " + list);
            int index = rand.nextInt(list.size()-1);
            System.out.println("INDEX: " + index);
            builder.append(this.ptKeywords.get(list.remove(index)));
            builder.append("\n");
        }

        list = new ArrayList<Integer>(this.ptKeywords.size());
        for(int i = 1; i <= this.ptKeywords.size(); i++) {
            list.add(i);
        }


        builder.append("\n\n");

        builder.append(constantFields.PT_BOTTOM_DESCRIPTION.getField());

        System.out.println(builder);

        this.resultTextArea.setText(builder.toString());
    }

    private void generateDeDescription(String albumUrl) {

        StringBuilder builder = new StringBuilder(constantFields.DE_FIRST_SENTENCE.getField());
        builder.append(albumUrl);

        builder.append("\n" + constantFields.DE_SECOND_SENTENCE.getField()+"\n\n");

        builder.append(this.descriptionTextArea.getText()+"\n\n");

        builder.append(constantFields.DE_TOP_DESCRIPTION.getField());

        builder.append("\n\n");

        //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        //builder.append(this.esKeywords.get(ThreadLocalRandom.current().nextInt(0, this.esKeywords.size())));

        ArrayList<Integer> list = new ArrayList<Integer>(this.deKeywords.size());
        for(int i = 1; i <= this.deKeywords.size(); i++) {
            list.add(i);
        }

        Random rand = new Random();
        while(list.size() > 7) {
            System.out.println("this.deKeywords.size(): " + this.deKeywords.size());
            System.out.println("LIST: " + list);
            int index = rand.nextInt(list.size()-1);
            System.out.println("INDEX: " + index);
            builder.append(this.deKeywords.get(list.remove(index)));
            builder.append("\n");
        }

        list = new ArrayList<Integer>(this.deKeywords.size());
        for(int i = 1; i <= this.deKeywords.size(); i++) {
            list.add(i);
        }


        builder.append("\n\n");

        builder.append(constantFields.DE_BOTTOM_DESCRIPTION.getField());

        System.out.println(builder);

        this.resultTextArea.setText(builder.toString());





    }

    private void generateEsDescription(String albumUrl) {

        StringBuilder builder = new StringBuilder(constantFields.ES_FIRST_SENTENCE.getField());
        builder.append(albumUrl);

        builder.append("\n" + constantFields.ES_SECOND_SENTENCE.getField()+"\n\n");

        builder.append(this.descriptionTextArea.getText()+"\n\n");

        builder.append(constantFields.ES_TOP_DESCRIPTION.getField());

        builder.append("\n\n");

        //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        //builder.append(this.esKeywords.get(ThreadLocalRandom.current().nextInt(0, this.esKeywords.size())));

        ArrayList<Integer> list = new ArrayList<Integer>(this.enKeywords.size());
        for(int i = 1; i <= this.enKeywords.size(); i++) {
            list.add(i);
        }

        Random rand = new Random();
        while(list.size() > 7) {
            System.out.println("this.esKeywords.size(): " + this.esKeywords.size());
            System.out.println("LIST: " + list);
            int index = rand.nextInt(list.size()-1);
            System.out.println("INDEX: " + index);
            builder.append(this.esKeywords.get(list.remove(index)));
            builder.append("\n");
        }

        list = new ArrayList<Integer>(this.esKeywords.size());
        for(int i = 1; i <= this.esKeywords.size(); i++) {
            list.add(i);
        }


        builder.append("\n\n");

        builder.append(constantFields.ES_BOTTOM_DESCRIPTION.getField());

        System.out.println(builder);

        this.resultTextArea.setText(builder.toString());
    }

    private void generateEnDescription(String albumUrl) {


        StringBuilder builder = new StringBuilder(constantFields.EN_FIRST_SENTENCE.getField());
        builder.append(albumUrl);

        builder.append("\n" + constantFields.EN_SECOND_SENTENCE.getField()+"\n\n");

        builder.append(this.descriptionTextArea.getText()+"\n\n");

        builder.append(constantFields.EN_TOP_DESCRIPTION.getField());

        builder.append("\n\n");

        ArrayList<Integer> list = new ArrayList<Integer>(this.enKeywords.size());
        for(int i = 1; i <= this.enKeywords.size(); i++) {
            list.add(i);
        }

        System.out.println("LIST: " + list);

        //int randomNum = ThreadLocalRandom.current().nextInt(0, this.enKeywords.size());

        Random rand = new Random();
        while(list.size() > 7) {
            System.out.println("this.enKeywords.size(): " + this.enKeywords.size());
            System.out.println("LIST: " + list);
            int index = rand.nextInt(list.size()-1);
            System.out.println("INDEX: " + index);
            builder.append(this.enKeywords.get(list.remove(index)));
            builder.append("\n");
        }

        list = new ArrayList<Integer>(this.enKeywords.size());
        for(int i = 1; i <= this.enKeywords.size(); i++) {
            list.add(i);
        }


        builder.append(constantFields.EN_BOTTOM_DESCRIPTION.getField());

        System.out.println(builder);

        this.resultTextArea.setText(builder.toString());



    }


    public void aboutItemAction() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GenDes v1.0");
        alert.setHeaderText("GenDes v1.0\n");
        //alert.setContentText("The results are saved in your .txt file!");

        alert.showAndWait();


    }
}
