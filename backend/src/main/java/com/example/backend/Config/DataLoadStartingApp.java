package com.example.backend.Config;

import com.example.backend.entities.Building;
import com.example.backend.entities.Category;
import com.example.backend.entities.City;
import com.example.backend.entities.AppUser;
import com.example.backend.repository.BuildingRepository;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.CityRepository;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.enums.AppRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataLoadStartingApp {

    private final CityRepository cityRepository;
    private final BuildingRepository buildingRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() throws JsonProcessingException {
        ajouter_un_utilisateur("Ghilas", "ghilas@expert.com", "12345678", AppRole.ROLE_EXPERT);
        ajouter_un_utilisateur("Dylan", "dylan@expert.com", "12345678", AppRole.ROLE_EXPERT);
        ajouter_un_utilisateur("Théo", "theo@visiteur.com", "12345678", AppRole.ROLE_VISITEUR);

        // --- Villes et Bâtiments ---
        if (cityRepository.count() == 0) {
            ajouter_categorie();
            initializeData();

        }
    }

    private void ajouter_categorie(){
if(categoryRepository.count() == 0){
    List<Category> categories= List.of(

            new Category(null, "Musées","🏛", "https://www.ecomusee-montmorillonnais.org/wp-content/uploads/2023/11/musees.jpg"),
            new Category(null, "Ponts","🌉", "https://cms.visitczechia.com/cms/getmedia/f5078b1a-8314-4d4e-b751-aa41692036ef/prague-troja-bridge-shutterstock_633423338-Nadezda-Murmakova.jpg?width=1000&height=666"),
            new Category(null, "Lieux de culte","⛪", "https://cdn.generationvoyage.fr/2014/10/lieux-de-culte-sites-religieux-spectaculaires-monde-24.jpg"),
            new Category(null, "Gratte-ciels","🏙", "https://ewnqp79wvj7.exactdn.com/wp-content/uploads/2024/07/One-World-Trade-Center-New-York-gratte-ciel.jpeg"),
            new Category(null, "Châteaux & Palais","🏰", "https://www.barnes-proprietes-chateaux.com/wp-content/uploads/2017/01/chateau-chantilly-renaissance.jpg"),
            new Category(null, "Bâtiments civils", "🏢", "https://media.istockphoto.com/id/1140466131/photo/shenzhen-downtown-area-during-sunset.jpg?s=612x612&w=0&k=20&c=t5AlLy589h5yGMQtOQ8dIrkWD67yYWyauDqkqyo6VPY="));
    categoryRepository.saveAll(categories);
}

    }

    private void ajouter_un_utilisateur(String username, String email, String password, AppRole role) {
        if (appUserRepository.findByEmail(email).isEmpty()) {
            AppUser user = new AppUser();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            appUserRepository.save(user);
        }
    }

    private void initializeData() throws JsonProcessingException {

        Category musee = categoryRepository.findById(1L).get(); //Musée
        Category pont = categoryRepository.findById(2L).get(); //Ponts
        Category lieuxDeCulte = categoryRepository.findById(3L).get(); //Lieux de culte
        Category gc = categoryRepository.findById(4L).get(); //Gratte-ciels
        Category cp = categoryRepository.findById(5L).get(); //Chateaux & palais
        Category bc = categoryRepository.findById(6L).get(); //Bâtiments civils


        // création des villes manuellement en dur
        City paris = new City(null, "Paris", "France", "", "75000",
                "https://www.allsuites.com/wp-content/uploads/2024/09/Paris.jpg", null, null);

        City tokyo = new City(null, "Tokyo", "Japon", null, "111-0032",
                "https://cdn-blog.superprof.com/blog_fr/wp-content/uploads/2018/03/palais-imperial-tokyo.jpg", null, null);
        City newYork = new City(null, "New York", "États-Unis", null, "NY 10001",
                "https://cdn-imgix.headout.com/tour/30357/TOUR-IMAGE/6cdcf542-452d-4897-beed-76cf68f154e4-1act-de005e04-05d9-4715-96b0-6a089d5c3460.jpg?auto=format&w=1222.3999999999999&h=687.6&q=90&ar=16%3A9&crop=faces&fit=crop", null, null);
        City londres = new City(null, "Londres", "Royaume-Uni", null, "SW1A 1AA",
                "https://media.clondres.com/resize/uploads/sites/5/2018/03/big-ben-londres-696x478.jpg?format=auto", null, null);
        City rome = new City(null, "Rome", "Italie", null, "00186",
                "https://www.webcity.fr/wp-content/uploads/2017/11/monument-rome-1.jpg", null, null);
        City barcelone = new City(null, "Barcelone", "Espagne", null, "08013",
                "https://jacheteenespagne.com/wp-content/uploads/2024/06/0bda496746574de3ac1ec5c68616272b-1.jpg", null, null);
//ajouter les ville a la bdd
        cityRepository.saveAll(List.of(paris, tokyo, newYork, londres, rome, barcelone));

        // création des batiments comme Dylan
        creer_un_batiment(paris, musee,
                "Musée du Louvre",
                "https://i.notretemps.com/2000x1125/smart/2023/10/19/musee-du-louvre.jpg",
                "Rue de Rivoli",
                "75001",
                "1793",
                "Multiples architectes",
                "Classique",

                "Ancien palais royal transformé en musée à la Révolution française, le Musée du Louvre est aujourd'hui l'un des plus grands et des plus célèbres " +
                        "musées du monde. Situé au cœur de Paris, il abrite des collections exceptionnelles couvrant plusieurs millénaires d'histoire et de civilisations. De l'Égypte antique à l'art occidental du XIXᵉ siècle, ses galeries rassemblent des œuvres emblématiques comme la Joconde ou la Vénus de Milo. Son architecture mêle héritage classique et modernité, notamment avec la pyramide de verre inaugurée en 1989. Symbole culturel majeur, le Louvre attire chaque année des millions de visiteurs venus admirer la richesse artistique qu'il conserve.",

                17,
                "2h-4h",
                "Recommandée",
                "Visitable",
                true,
                48.8606, 2.3376, louvreHoraires());


        creer_un_batiment(paris,  bc,"Tour Eiffel", "https://www.secretdeparis.com/blog/wp-content/uploads/2025/04/eiffel-tower-975004_1280.jpg",
                "Champ de Mars, 5 Avenue Anatole France", "75007", "1889", "Gustave Eiffel", "Industrielle",
                "Construite pour l'Exposition universelle de 1889, la Tour Eiffel est devenue le symbole emblématique de Paris et de la France. Imaginée par l'ingénieur Gustave Eiffel, elle devait initialement être démontée après l'événement et illustrer le savoir-faire industriel français de la fin du XIXᵉ siècle. Haute de 324 mètres, elle fut pendant plus de quarante ans la plus haute structure du monde. Entièrement réalisée en fer puddlé, elle incarne l'audace technique et l'innovation de son époque. D'abord vivement critiquée, elle est aujourd'hui l'un des monuments les plus visités au monde et offre des panoramas exceptionnels sur la capitale.",
                29, "1h30-2h30", "Obligatoire", "Visitable", true,
                48.8584, 2.2945, tourEiffelHoraires());

        creer_un_batiment(tokyo,  lieuxDeCulte,"Sanctuaire Asakusa", "https://media.istockphoto.com/id/1329537420/fr/photo/tokyo-japon-27-octobre-2017-tokyo-japon-horizon-de-la-ville-au-temple-asakusa.jpg?s=612x612&w=0&k=20&c=0mRZPy-eg603K8FPB7k_O73e7tqHLv4PIDjWIKcXXH4=",
                "2-3-1 Asakusa, Taito City", "111-0032", "645", "Inconnu", "Traditionnelle japonaise",
                "Fondé au VIIᵉ siècle, le sanctuaire d'Asakusa, également connu sous le nom de Sensō-ji, est l'un des plus anciens et des plus vénérés temples bouddhistes de Tokyo. Reconstruit à plusieurs reprises au fil des siècles, il demeure un symbole spirituel majeur de la capitale japonaise. Son imposante porte Kaminarimon et sa pagode à cinq étages attirent fidèles et visiteurs venus découvrir l'architecture traditionnelle japonaise. Entouré d'une rue commerçante animée, le sanctuaire incarne l'équilibre entre héritage religieux et vie urbaine moderne. Lieu de festivals et de cérémonies, il conserve une atmosphère solennelle tout en restant profondément ancré dans le quotidien tokyoïte.",
                0, "45-60 min", "Non requis", "Accès libre", false,
                35.7148, 139.7967, asakusaHoraires());

        creer_un_batiment(tokyo, bc,"Tokyo Tower", "https://byfood.b-cdn.net/api/public/assets/59153/content?optimizer=image",
                "4-2-8 Shibakoen, Minato City", "105-0011", "1958", "Tachū Naitō", "Moderne",
                "Inaugurée en 1958, la Tokyo Tower s'inspire de la Tour Eiffel tout en affirmant une identité propre au Japon d'après-guerre. Haute de 333 mètres, elle fut construite pour soutenir les antennes de diffusion télévisuelle et symboliser le renouveau économique du pays. Peinte en blanc et orange pour répondre aux normes aériennes, elle domine le paysage tokyoïte et offre des plateformes panoramiques spectaculaires. Mélange de fonctionnalité technique et d'esthétique moderne, la tour est devenue un repère iconique de la capitale. Elle demeure aujourd'hui un lieu touristique incontournable, particulièrement appréciée au coucher du soleil et de nuit.",
                9, "1h-1h30", "Recommandée", "Visitable", true,
                35.6586, 139.7454, tokyoTowerHoraires());


        creer_un_batiment(newYork,  gc, "Empire State Building", "https://cdn.calendarz.com/uploads/events/may/1/41113/empire-state-building_compressed.jpg",
                "20 W 34th St", "NY 10001", "1931", "Shreve, Lamb & Harmon", "Art déco",
                "Achevé en 1931 au cœur de Manhattan, l'Empire State Building est l'un des gratte-ciel les plus célèbres au monde. Construit en pleine Grande Dépression, il incarnait l'ambition et la résilience américaines. Son style Art déco, caractérisé par des lignes élancées et des détails géométriques, en fait un chef-d'œuvre architectural du XXᵉ siècle. Pendant près de quarante ans, il fut le plus haut immeuble du monde. Ses observatoires offrent une vue panoramique exceptionnelle sur New York et ses environs. Véritable icône de la skyline new-yorkaise, il demeure un symbole fort de modernité et de puissance urbaine.",
                41, "1h-2h", "Recommandée", "Visitable", true,
                40.7484, -73.9857, ESBHoraires());

        creer_un_batiment(newYork,  bc,
                "Statue de la Liberté",
                "https://cdn.sortiraparis.com/images/80/66131/1184658-connaissez-vous-les-origines-parisiennes-de-la-statue-de-la-liberte-de-new-york.jpg",
                "Liberty Island",
                "NY 10004",
                "1886",
                "Frédéric Auguste Bartholdi",
                "Néo-classique",
                "Offerte par la France aux États-Unis en 1886, la Statue de la Liberté est devenue l'un des symboles les plus puissants de liberté et de démocratie. Conçue par le sculpteur Frédéric Auguste Bartholdi avec une structure interne imaginée par Gustave Eiffel, elle accueille les visiteurs à l'entrée du port de New York. Haute de 93 mètres avec son piédestal, elle représente la déesse romaine Libertas tenant une torche et une tablette gravée de la date de l'indépendance américaine. Monument néo-classique monumental, elle incarne l'espoir et l'émancipation. Aujourd'hui encore, elle demeure un repère historique et culturel majeur.",
                22,
                "2h-3h",
                "Obligatoire",
                "Visitable",
                true,
                40.6892, -74.0445,
                statueLiberteHoraires());

        creer_un_batiment(londres,    cp,
                "Buckingham Palace",
                "https://cdn-imgix.headout.com/media/images/31a4bf553f447246bf5bd92b00cb1707-12286-london-changing-of-the-guard-tour-with-optional-buckingham-palace-entry-11.jpg",
                "Buckingham Palace",
                "SW1A 1AA",
                "1703",
                "William Winde",
                "Néo-classique",
                "Résidence officielle des souverains britanniques à Londres, Buckingham Palace est un symbole majeur de la monarchie britannique. Construit au début du XVIIIᵉ siècle puis agrandi au fil du temps, il adopte un style néo-classique sobre et monumental. Le palais compte plus de 700 pièces, dont des salons d'apparat utilisés lors de cérémonies officielles et de réceptions d'État. La célèbre relève de la garde attire quotidiennement de nombreux visiteurs. Au-delà de sa fonction résidentielle, Buckingham Palace représente la continuité et la tradition de la Couronne au cœur de la capitale britannique.",
                32,
                "1h30-2h30",
                "Obligatoire",
                "Accès restreint",
                true,
                51.5014, -0.1419,
                buckinghamHoraires());

        creer_un_batiment(londres,    pont,
                "Tower Bridge",
                "https://cdn.britannica.com/35/156335-050-62245FCA/Tower-Bridge-River-Thames-London.jpg",
                "Tower Bridge Rd",
                "SE1 2UP",
                "1894",
                "Horace Jones",
                "Néogothique",
                "Inauguré en 1894, le Tower Bridge est l'un des ponts les plus reconnaissables de Londres. Conçu dans un style néogothique afin de s'harmoniser avec la Tour de Londres voisine, il combine esthétique historique et prouesse technique. Son mécanisme basculant permet aux navires de grande taille de circuler sur la Tamise. Les passerelles supérieures offrent aujourd'hui une vue panoramique sur la ville et retracent l'histoire industrielle du pont. Véritable emblème londonien, il illustre l'ingénierie victorienne et demeure un élément central du paysage urbain.",
                12,
                "1h-1h30",
                "Recommandée",
                "Visitable",
                true,
                51.5055, -0.0754,
                towerBridgeHoraires());

        creer_un_batiment(rome,     bc,
                "Fontaine de Trevi",
                "https://as2.ftcdn.net/v2/jpg/01/21/87/47/1000_F_121874733_ObMVN0dprpejLPmvkQumpLRvxOWOI7sW.jpg",
                "Piazza di Trevi",
                "00187",
                "1762",
                "Nicola Salvi",
                "Baroque",
                "Achevée en 1762, la Fontaine de Trevi est l'un des chefs-d'œuvre du baroque romain. Conçue par Nicola Salvi, elle marque le point d'arrivée de l'ancien aqueduc de l'Acqua Vergine. Son décor spectaculaire représente Neptune sur un char tiré par des chevaux marins, entouré de sculptures allégoriques. La composition théâtrale, les jeux d'eau et la richesse des détails en font un monument particulièrement impressionnant. Selon la tradition, jeter une pièce dans la fontaine garantit un retour à Rome. Elle demeure aujourd'hui l'un des sites les plus visités et photographiés de la capitale italienne.",
                0,
                "20-30 min",
                "Non requis",
                "Accès libre",
                true,
                41.9009, 12.4833,
                treviHoraires());

        creer_un_batiment(rome,      lieuxDeCulte,
                "Le Panthéon",
                "https://voyageur-attitude.fr/wp-content/uploads/Le-Pantheon-1024x644.jpg",
                "Piazza della Rotonda",
                "00186",
                "125",
                "Apollodore de Damas",
                "Antique",
                "Construit au IIᵉ siècle sous l'empereur Hadrien, le Panthéon de Rome est l'un des monuments antiques les mieux conservés au monde. Dédié à l'ensemble des divinités romaines, il impressionne par son immense coupole en béton coiffée d'un oculus central laissant pénétrer la lumière naturelle. Son portique à colonnes corinthiennes témoigne du génie architectural de l'Antiquité. Transformé en église chrétienne au VIIᵉ siècle, il a ainsi échappé à la destruction. Chef-d'œuvre d'ingénierie antique, le Panthéon continue de fasciner par l'harmonie de ses proportions et sa longévité exceptionnelle.",
                5,
                "45-60 min",
                "Recommandée",
                "Visitable",
                false,
                41.8986, 12.4769,
                pantheonHoraires());

        creer_un_batiment(barcelone, lieuxDeCulte,
                "Sagrada Família",
                "https://lelephant-larevue.fr/wp-content/uploads/2018/03/%CE%A3%CE%B1%CE%B3%CF%81%CE%B1%CC%81%CE%B4%CE%B1_%CE%A6%CE%B1%CE%BC%CE%B9%CC%81%CE%BB%CE%B9%CE%B1_2941.jpg",
                "Carrer de Mallorca, 401",
                "08013",
                "1882",
                "Antoni Gaudí",
                "Modernisme catalan",
                "Commencée en 1882, la Sagrada Família est l'œuvre monumentale d'Antoni Gaudí et l'un des symboles majeurs de Barcelone. Mélange unique de spiritualité, de nature et d'innovation architecturale, elle incarne le modernisme catalan dans toute sa créativité. Ses façades richement sculptées racontent des épisodes bibliques tandis que l'intérieur, baigné de lumière colorée, évoque une forêt de colonnes élancées. Toujours en construction plus d'un siècle après son lancement, la basilique témoigne d'un projet artistique ambitieux et visionnaire. Classée au patrimoine mondial de l'UNESCO, elle attire des millions de visiteurs chaque année.",
                26,
                "1h30-2h",
                "Obligatoire",
                "Visitable",
                true,
                41.4036, 2.1744,
                sagradaFamiliaHoraires()
        );

        creer_un_batiment(barcelone, bc,
                "Parc Güell",
                "https://www.guidesulysse.com/images/destinations/iStock-534846887.jpg",
                "Carrer d'Olot, 5",
                "08024",
                "1900",
                "Antoni Gaudí",
                "Modernisme catalan",
                "Imaginé au début du XXᵉ siècle par Antoni Gaudí, le Parc Güell est un vaste ensemble paysager mêlant architecture et nature. Conçu à l'origine comme un projet résidentiel, il est devenu un parc public emblématique de Barcelone. Ses mosaïques colorées, ses formes ondulantes et ses structures organiques illustrent pleinement le modernisme catalan. La célèbre salamandre en céramique et la terrasse panoramique offrent des points de vue spectaculaires sur la ville. Véritable œuvre d'art à ciel ouvert, le parc reflète la vision poétique et innovante de son architecte.",
                10,
                "1h-2h",
                "Recommandée",
                "Visitable",
                true,
                41.4145, 2.1527,
                parcGuellHoraires()
        );
    }





    private void creer_un_batiment(City city,Category category, String name, String image, String address, String postalCode,
                                String year, String architect, String style, String description,
                                Integer ticketPrice, String visitDuration, String booking, String accessStatus, boolean accessible,
                                double lat, double lng, Map<String, Object> horaires) throws JsonProcessingException {
        Building building = new Building();
        building.setCity(city);
        building.setName(name);
        building.setImage(image);
        building.setAddress(address);
        building.setPostalCode(postalCode);
        building.setConstructionYear(year);
        building.setArchitect(architect);
        building.setStyle(style);
        building.setDescription(description);
        building.setTicketPrice(ticketPrice);
        building.setVisitDuration(visitDuration);
        building.setBooking(booking);
        building.setAccessStatus(accessStatus);
        building.setAccessiblePRM(accessible);
        building.setLatitude(lat);
        building.setLongitude(lng);
        building.setCategories(category);

        // Stocker les horaires en JSON String //modifier les horaires pour stocker les horaires
        //avec le mapper on transforme la Map en string pour stocker en bdd puisqhe la bd stock que de text et string
        //puis je le met dans schedules avec setSchedules
        building.setSchedules(mapper.writeValueAsString(horaires));

        buildingRepository.save(building);
    }

    // --- Horaires exemple pour chaque bâtiment ---
    private Map<String, Object> louvreHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("mardi", List.of());
        days.put("mercredi", List.of(Map.of("start","09:00","end","21:00")));
        days.put("jeudi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("vendredi", List.of(Map.of("start","09:00","end","21:00")));
        days.put("samedi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("dimanche", List.of(Map.of("start","09:00","end","18:00")));

        horaires.put("days", days);

        horaires.put("note", "Fermé le mardi. La dernière admission est 1h avant la fermeture, l'évacuation 30 min avant.");

        horaires.put("officialHoursUrl", "https://www.louvre.fr/visiter");

        return horaires;
    }

    private Map<String, Object> tourEiffelHoraires() {
        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();
        days.put("lundi", List.of(Map.of("start","09:30","end","23:00")));
        days.put("mardi", List.of(Map.of("start","09:30","end","23:00")));
        days.put("mercredi", List.of(Map.of("start","09:30","end","23:00")));
        days.put("jeudi", List.of(Map.of("start","09:30","end","23:00")));
        days.put("vendredi", List.of(Map.of("start","09:30","end","23:00")));
        days.put("samedi", List.of(Map.of("start","09:30","end","23:00")));
        days.put("dimanche", List.of(Map.of("start","09:30","end","23:00")));

        horaires.put("days", days);

        horaires.put("note", "Horaires susceptibles de varier selon la saison et l'affluence. Vérifier le jour même si besoin.");
        horaires.put("officialHoursUrl", "https://www.toureiffel.paris/fr/tarifs-horaires");

        return horaires;
    }

    private Map<String, Object> asakusaHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of(Map.of("start","06:00","end","17:00")));
        days.put("mardi", List.of(Map.of("start","06:00","end","17:00")));
        days.put("mercredi", List.of(Map.of("start","06:00","end","17:00")));
        days.put("jeudi", List.of(Map.of("start","06:00","end","17:00")));
        days.put("vendredi", List.of(Map.of("start","06:00","end","17:00")));
        days.put("samedi", List.of(Map.of("start","06:00","end","17:00")));
        days.put("dimanche", List.of(Map.of("start","06:00","end","17:00")));

        horaires.put("days", days);

        horaires.put("note", "En général : le hall principal est ouvert de 06h00 à 17h00 (il peut ouvrir à 06h30 d'octobre à mars). Les abords et l'enceinte sont accessibles en continu.");

        horaires.put("officialHoursUrl", null);

        return horaires;
    }

    private Map<String, Object> tokyoTowerHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of(Map.of("start","09:00","end","23:00")));
        days.put("mardi", List.of(Map.of("start","09:00","end","23:00")));
        days.put("mercredi", List.of(Map.of("start","09:00","end","23:00")));
        days.put("jeudi", List.of(Map.of("start","09:00","end","23:00")));
        days.put("vendredi", List.of(Map.of("start","09:00","end","23:00")));
        days.put("samedi", List.of(Map.of("start","09:00","end","23:00")));
        days.put("dimanche", List.of(Map.of("start","09:00","end","23:00")));

        horaires.put("days", days);

        horaires.put("note", "Pour le Main Deck : la dernière admission est à 22h30. Le Top Deck Tour est ouvert de 09h00 à 22h45 (dernier tour aux alentours de 22h15).");

        horaires.put("officialHoursUrl", "https://www.tokyotower.co.jp/fee/");

        return horaires;
    }

    private Map<String, Object> ESBHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Variable");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of());
        days.put("mardi", List.of());
        days.put("mercredi", List.of());
        days.put("jeudi", List.of());
        days.put("vendredi", List.of());
        days.put("samedi", List.of());
        days.put("dimanche", List.of());

        horaires.put("days", days);

        horaires.put("note", "Les horaires varient d'un jour à l'autre. Par exemple du 6 janvier au 12 février, ce sera ouvert de 10h00 à 21h00 (l'entrée ferme à 20h00).");

        horaires.put("officialHoursUrl", "https://www.esbnyc.com/fr/visit/hours-of-operation");

        return horaires;
    }

    private Map<String, Object> statueLiberteHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of(Map.of("start","09:00","end","16:30")));
        days.put("mardi", List.of(Map.of("start","09:00","end","16:30")));
        days.put("mercredi", List.of(Map.of("start","09:00","end","16:30")));
        days.put("jeudi", List.of(Map.of("start","09:00","end","16:30")));
        days.put("vendredi", List.of(Map.of("start","09:00","end","16:30")));
        days.put("samedi", List.of(Map.of("start","09:00","end","16:30")));
        days.put("dimanche", List.of(Map.of("start","09:00","end","16:30")));

        horaires.put("days", days);

        horaires.put("note", "Accès uniquement via le ferry, les horaires de départ et de retour sont variables). Prévoir assez de temps.");

        horaires.put("officialHoursUrl", "https://www.nps.gov/stli/planyourvisit/index.htm");

        return horaires;
    }

    private Map<String, Object> buckinghamHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Variable");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of());
        days.put("mardi", List.of());
        days.put("mercredi", List.of());
        days.put("jeudi", List.of());
        days.put("vendredi", List.of());
        days.put("samedi", List.of());
        days.put("dimanche", List.of());

        horaires.put("days", days);

        horaires.put("note", "Ouverture au public surtout en été pour les State Rooms (en 2026 : du 9 juillet au 27 septembre). Hors saison : seulement certaines dates notamment en visites guidées).");

        horaires.put("officialHoursUrl", "https://www.rct.uk/visit/buckingham-palace");

        return horaires;
    }

    private Map<String, Object> towerBridgeHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("mardi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("mercredi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("jeudi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("vendredi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("samedi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("dimanche", List.of(Map.of("start","09:30","end","18:00")));

        horaires.put("days", days);

        horaires.put("note", "Dernière entrée à 17h00.");

        horaires.put("officialHoursUrl", "https://www.towerbridge.org.uk/");

        return horaires;
    }

    private Map<String, Object> treviHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Accès libre");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of(Map.of("start","00:00","end","23:59")));
        days.put("mardi", List.of(Map.of("start","00:00","end","23:59")));
        days.put("mercredi", List.of(Map.of("start","00:00","end","23:59")));
        days.put("jeudi", List.of(Map.of("start","00:00","end","23:59")));
        days.put("vendredi", List.of(Map.of("start","00:00","end","23:59")));
        days.put("samedi", List.of(Map.of("start","00:00","end","23:59")));
        days.put("dimanche", List.of(Map.of("start","00:00","end","23:59")));

        horaires.put("days", days);

        horaires.put("note", "La place est accessible toute la journée. Depuis le 2 février 2026, l'accès au bord de la fontaine est payant à certaines heures : du lundi au vendredi de 11h30 à 22h00 et le week-end de 09h00 à 22h00."
        );

        horaires.put("officialHoursUrl", "https://www.turismoroma.it/en/places/trevi-fountain");

        return horaires;
    }

    private Map<String, Object> pantheonHoraires() {

        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();

        days.put("lundi", List.of(Map.of("start","09:00","end","19:00")));
        days.put("mardi", List.of(Map.of("start","09:00","end","19:00")));
        days.put("mercredi", List.of(Map.of("start","09:00","end","19:00")));
        days.put("jeudi", List.of(Map.of("start","09:00","end","19:00")));
        days.put("vendredi", List.of(Map.of("start","09:00","end","19:00")));
        days.put("samedi", List.of(Map.of("start","09:00","end","19:00")));
        days.put("dimanche", List.of(Map.of("start","09:00","end","19:00")));

        horaires.put("days", days);

        horaires.put("note", "Dernière entrée à 18:45. Fermé : 1er janvier, 15 août, 25 décembre (et variations possibles lors de célébrations).");

        horaires.put("officialHoursUrl","https://www.pantheonroma.com/en/openings-info");

        return horaires;
    }

    private Map<String, Object> sagradaFamiliaHoraires() {
        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();
        days.put("lundi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("mardi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("mercredi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("jeudi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("vendredi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("samedi", List.of(Map.of("start","09:00","end","18:00")));
        days.put("dimanche", List.of(Map.of("start","10:30","end","18:00")));

        horaires.put("days", days);

        horaires.put("note", "Horaires saisonniers (différents de Mars à Octobre et d'Avril à Septembre).");
        horaires.put("officialHoursUrl", "https://sagradafamilia.org/fr/schedules-how-to-get");

        return horaires;
    }

    private Map<String, Object> parcGuellHoraires() {
        Map<String, Object> horaires = new HashMap<>();

        horaires.put("type", "Horaires");

        Map<String, List<Map<String, String>>> days = new HashMap<>();
        days.put("lundi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("mardi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("mercredi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("jeudi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("vendredi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("samedi", List.of(Map.of("start","09:30","end","18:00")));
        days.put("dimanche", List.of(Map.of("start","09:30","end","18:00")));

        horaires.put("days", days);

        horaires.put("note", "Créneaux de visite (tickets) + plages réservées aux résidents/adhérents (hors visite touristique). Horaires pouvant varier selon périodes.");
        horaires.put("officialHoursUrl", "https://parkguell.barcelona/fr/planifiez-votre-visite/horaires-et-tarifs");

        return horaires;
    }
}
