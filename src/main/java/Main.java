/**
 * Search Product [x]
 *  1. Click
 *  2. Text Field tas pwedes mag search
 *  3. text field na may table tapos pinapakita lang saan category
 *
 * Confirm Purchases [x]
 *  1. Open summary of purchase
 *
 * Void Item [x]
 * 1. window na pwede mag select ng shit na tapos may delete button aanhd bavkc hthoanubno
 *
 * Clear Cart [x]
 * 1. clear cart2
 *
 * exit [x]
 * 1. papatyin
 */

package main.java;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class Main {

    private static final HashMap<String, JFrame> openedFrames = new HashMap<>();
    private static final HashMap<String, ImageIcon> cachedIcons = new HashMap<>();

    private static class Colors {
        private static final Color background = new Color(230, 230, 230);
        private static final Color transparent = new Color(0, 0, 0, 0);
    }

    private static class ImagePath {
        private static final String icon = "./assets/logo.png";
        private static final String painRelievers = "./assets/pain-relievers.png";
        private static final String antibiotics = "./assets/antibiotics.png";
        private static final String antiAllergy = "./assets/antiAllergy.png";
        private static final String respiratoryMedicine = "./assets/respiratoryMedicine.png";
        private static final String feverMedicine = "./assets/feverMedicine.png";
        private static final String vitamins = "./assets/vitamins.png";
        private static final String dietarySupplements = "./assets/dietarySupplements.png";
        private static final String mineralSupplements = "./assets/mineralSupplements.png";
        private static final String bandages = "./assets/bandages.png";
        private static final String cottonItems = "./assets/cottonItems.png";
        private static final String antiseptics = "./assets/antiseptics.png";
        private static final String personalHygiene = "./assets/personalHygiene.png";
        private static final String surgicalEquipment = "./assets/surgicalEquipment.png";
        private static final String assistiveDevices = "./assets/assistiveDevices.png";

        private static final String ibuprofen = "./assets/products/ibuprofen.png";
        private static final String acetaminophen = "./assets/products/acetaminophen.png";
        private static final String naproxenSodium = "./assets/products/naproxen_sodium.png";

        private static final String cephalexin = "./assets/products/cephalexin.png";
        private static final String amoxicillin = "./assets/products/amoxicillin.png";
        private static final String azithromycin = "./assets/products/azithromycin.png";

        private static final String loratidine = "./assets/products/loratadine.png";
        private static final String cetirizine = "./assets/products/cetirizine.png";
        private static final String levocetirizine = "./assets/products/levocetirizine_dihydrochloride.png";
        private static final String pirbuterol = "./assets/products/pirbuterol_acetate.png";
        private static final String salbutamol = "./assets/products/salbutamol.png";
        private static final String methylprednisolone = "./assets/products/methylprednisolone.png";
        private static final String paracetamol = "./assets/products/paracetamol.png";
        private static final String phenylephrineChlorphenamine = "./assets/products/phenylephrine_chlorphenamine_maleate.png";
        private static final String dextromethorphanPhenylpropanolamine = "./assets/products/dextromethorphan_phenylpropanolamine.png";
        private static final String mutlivitaminsEffervescentTablets = "./assets/products/multivitamin_effervescent_tablets.png";
        private static final String ascorbicAcid = "./assets/products/ascorbic_acid.png";
        private static final String vitaminBComplex = "./assets/products/vitramin_b_complex.png";
        private static final String astaxathinLycopeneVitaminE = "./assets/products/astaxanthin_lycopene_vitamin_e.png";
        private static final String spirulineTablets = ".assets/products/spiruline_tablets.png";
        private static final String garciniaMangostanaCapsule = "./assets/products/garcinia_mangostana_capsule.png";
        private static final String cholecalciferolMinerals = "./assets/products/cholecalciferol_minerals.png";
        private static final String calciumCarbonate = "./assets/products/calcium_carbonate.png";
        private static final String multivitaminIronCalcium = "./assets/products/multivitamins_iron_calcium.png";
        private static final String gauzeBandage = "./assets/products/gauze_bandage.png";
        private static final String elasticBandage = "./assets/products/elastic_bandage.png";
        private static final String tubularBandage = "./assets/products/tubular_bandage.png";
        private static final String cottonBuds = "./assets/products/cotton_buds.png";
        private static final String cottonBalls = "./assets/products/cotton_balls.png";
        private static final String cottonPads = "./assets/products/cotton_pads.png";
        private static final String povidoneIodine = "./assets/products/povidone_iodine.png";
        private static final String hexitidine = "./assets/products/hexetidine.png";
        private static final String hydrogenPeroxide = "./assets/products/hydrogen_peroxide.png";
        private static final String bodySoap = "./assets/products/body_soap.png";
        private static final String shampoo = "./assets/products/shampoo.png";
        private static final String toothpaste = "./assets/products/toothpaste.png";
        private static final String surgicalGloves = "./assets/products/surgical_gloves.png";
        private static final String disposableSyringe = "./assets/products/disposable_syringe.png";
        private static final String surgicalScissors = "./assets/products/surgical_scissors.png";
        private static final String assistiveCane = "./assets/products/assistive_cane.png";
        private static final String assistiveWalker = "./assets/products/assistive_walker.png";
        private static final String wheelchair = "./assets/products/wheelchair.png";
    }

    private static final Category[] categories = new Category[] {
            new Category(
                    "Pain\nRelievers",
                    "Pain Relievers",
                    ImagePath.painRelievers,
                    "Analgesics, also called painkillers are medications that relieve different types of pain — \nfrom headaches to injuries to arthritis. Anti-inflammatory analgesics reduce inflammation, and opioid\n analgesics change the way the brain perceives pain. Some analgesics can be bought over the counter,\n others require a prescription.",
                    new Product[] {
                            new Product(
                                    "Ibuprofen+Paracetamol (325mg)",
                                    ImagePath.ibuprofen,
                                    "Faster relief of mild to moderately severe pain of musculoskeletal origin eg muscle pain, arthritis, rheumatism, sprain, strain, bursitis, tendonitis, backache, stiff neck, tension headache, dysmenorrhea, toothache, pain after tooth extraction & minor surgical operations. Reduction of fever.",
                                    8.50),
                            new Product(
                                    "Acetaminophen (650mg)",
                                    ImagePath.acetaminophen,
                                    "Temporary relief of pain & discomfort from headache, fever, cold or flu, minor muscular aches, overexertion, menstrual cramps, toothache, minor arthritic pain.",
                                    9.25),
                            new Product(
                                    "Naproxen Sodium Tablet (275 mg)",
                                    ImagePath.naproxenSodium,
                                    "Used in the relief of inflammation, swelling, stiffness, and pain due to musculoskeletal and joint disorders such as arthritis and gout. Also used for mild to moderate pain caused by dysmenorrhea (menstrual pain), migraine, and after dental or other surgical procedures.",
                                    11.50),
                    }),
            new Category(
                    "Antibiotics",
                    "Antibiotics",
                    ImagePath.antibiotics,
                    "Antibiotics are medicines that fight infections caused by bacteria in humans and animals by either\n killing the bacteria or making it difficult for the bacteria to grow and multiply.",
                    new Product[] {
                            new Product(
                                    "Cephalexin",
                                    ImagePath.cephalexin,
                                    "Cephalexin Medication is commonly used to treat infections of the respiratory tract, skin, ear, urinary tract, and bone.",
                                    250.00),
                            new Product(
                                    "Amoxicillin",
                                    ImagePath.amoxicillin,
                                    "Amoxicillin is commonly used to treat infections such as pneumonia, bronchitis, ear infections, urinary tract infections, and strep throat.",
                                    18.75),
                            new Product(
                                    "Azithromycin",
                                    ImagePath.azithromycin,
                                    "Used to treat certain bacterial infections, such as bronchitis; pneumonia; sexually transmitted diseases (STD); and infections of the ears, lungs, sinuses, skin, throat, and reproductive organs",
                                    88.75)
                    }),
            new Category(
                    "Anti\nAllergy",
                    "Anti Allergy",
                    ImagePath.antiAllergy,
                    "A category of medicine that is most commonly used by people who have allergic reactions to pollen \n and other allergens. These medicines help ease a runny nose, itchy or watery eyes, hives, swelling,\nand other signs or symptoms of allergies.",
                    new Product[] {
                            new Product(
                                    "Loratadine",
                                    ImagePath.loratidine,
                                    "Provides a non-drowsy and fast-acting relief from symptoms associated with allergic rhinitis such as sneezing, runny or itchy nose, and itchy, red, watery eyes. Relief of allergic symptoms of urticaria such as itching, redness, and rash.",
                                    34.97),
                            new Product(
                                    "Cetirizine Dihydrochloride",
                                    ImagePath.cetirizine,
                                    "Treatment of allergic rhinitis, pinkeye, itch & Chronic hives (red, itchy skin welts that last more than six weeks.",
                                    16.00),
                            new Product(
                                    "Levocetrizine Hydrochloride",
                                    ImagePath.levocetirizine,
                                    " Levocetrizine hydrochloride is an antihistamine used to relieve allergy symptoms such as watery eyes, runny nose, itching eyes/nose, and sneezing. It works by blocking a certain natural substance (histamine) that the body makes during an allergic reaction.",
                                    15.50)
                    }),
            new Category(
                    "Respiratory\nMedicine",
                    "Respiratory Medicine",
                    ImagePath.respiratoryMedicine,
                    "Respiratory agents is a term used to describe a wide variety of medicines used to relieve, treat, or\n prevent respiratory diseases such as asthma, chronic bronchitis, chronic obstructive pulmonary disease\n (COPD), or pneumonia. Respiratory agents are available in many different forms, such as oral\n tablets, oral liquids, injections or inhalations.",
                    new Product[] {
                            new Product(
                                    "Pirbuterol",
                                    ImagePath.pirbuterol,
                                    "Maxair is a prescription medicine used to treat Asthma, Bronchitis, and Emphysema. Maxair may be used alone or with other medications.  It works by relaxing the muscles in the airways and widening the air passages to make breathing easier.",
                                    174.79),
                            new Product(
                                    "Albuterol",
                                    ImagePath.salbutamol,
                                    "Inhaler is a medical device used to deliver medication directly to the lungs. It is commonly used for the treatment of respiratory conditions such as asthma and chronic obstructive pulmonary disease (COPD).",
                                    475.75),
                            new Product(
                                    "Methylprednisolone",
                                    ImagePath.methylprednisolone,
                                    "Methylprednisolone is a type of medication that is used to reduce inflammation in the body, including respiratory conditions such asthma and chronic obstructive pulmonary disease",
                                    8.50),
                    }),
            new Category(
                    "Fever\nMedicine",
                    "Fever Medicine",
                    ImagePath.feverMedicine,
                    "Fever medicines are a group of medications taken individually or in combination as a treatment for the\n symptoms of the common cold and similar conditions of the upper respiratory tract.",
                    new Product[] {
                            new Product(
                                    "Paracetamol",
                                    ImagePath.paracetamol,
                                    "Paracetamol medication that is typically used to relieve mild to moderate pain such as headache, backache, menstrual cramps, muscular strain, minor arthritis pain, toothache, and reduce fevers caused by illnesses such as the common cold and flu.",
                                    45.00),
                            new Product(
                                    "Phenylephrine, Chlorophenamine Maleate, Paracetamol",
                                    ImagePath.phenylephrineChlorphenamine,
                                    " Treatment of colds and related symptoms. Phenylephrine HCl, Chlorphenamine Maleate and Paracetamol makes breathing easier, relieves allergies and related symptoms, and reduces fever.",
                                    110.00),
                            new Product(
                                    "Dextromethorphan, Phenylpropanolamine, Paracetamol",
                                    ImagePath.dextromethorphanPhenylpropanolamine,
                                    "Completed with Dextromethorphan, Phenylpropanolamine, and Paracetamo to stop coughs, clear obstructed air passages and nasal sinuses, and reduce fever and body pain.",
                                    210.00)
                    }),
            new Category(
                    "Vitamins",
                    "Vitamins",
                    ImagePath.vitamins,
                    "Vitamins have different jobs to help keep the body working properly. Some vitamins help resist infections\n and keep nerves healthy, while others may help the body get energy from food or also help\n blood clot properly.",
                    new Product[] {
                            new Product(
                                    "Multivitamin Effervescent Tablets",
                                    ImagePath.mutlivitaminsEffervescentTablets,
                                    "The formula of magnesium, zinc, calcium, vitamin C and eight B vitamins helps release energy from your food, reduces tiredness and fatigue and also supports physical and mental stamina",
                                    285.00),
                            new Product(
                                    "Ascorbic Acid 500mg",
                                    ImagePath.ascorbicAcid,
                                    "Contains ASCORBIC ACID 500Mg. This medicine is used in the treatment and prevention of Vitamin C deficiency.",
                                    2.25),
                            new Product(
                                    "Vitamin B-Complex",
                                    ImagePath.vitaminBComplex,
                                    "Treat Vitamin B deficiency and illnesses along with it such as muscle pain, loss of reflexes in the hands, knees, ankles, calves and feet and abnormal touch sensation (burning and pricking).",
                                    14.25)
                    }),
            new Category(
                    "Dietary\nSupp.",
                    "Dietary Supplements",
                    ImagePath.dietarySupplements,
                    "Dietary supplements are intended to add to or supplement nutrients that might be\n missing from a person's diet.",
                    new Product[] {
                            new Product(
                                    "Astaxanthin, Lycopene, and Vitamin E",
                                    ImagePath.astaxathinLycopeneVitaminE,
                                    "Astaxanthin, lycopene, and vitamin E supplement serves as a powerful antioxidant that helps remove harmful free radicals and promote cell and tissue renewal of the skin, lungs, muscles, and liver. It also helps maintain and improve stamina",
                                    195.00),
                            new Product(
                                    "Spirulina Tablets",
                                    ImagePath.spirulineTablets,
                                    "Contains high amounts of amino acids, carbohydrates, fats, vitamins, minerals and trace elements, phyto-nutrients and dietary fibers that addresses nutritional diseases resulting in anemia, poor weight gain and other nutrition-related health conditions",
                                    1),
                            new Product(
                                    "Garcinia Mangostana L. Food Supplement",
                                    ImagePath.garciniaMangostanaCapsule,
                                    "Contains natural Pure Xanthone, with antioxidant and anti-inflammatory properties that may help protect against free radicals causing diseases",
                                    1)
                    }),
            new Category(
                    "Mineral\nSupp.",
                    "Mineral Supplements",
                    ImagePath.mineralSupplements,
                    " Mineral Supplements are a category of medicine that provides essential minerals to the body, \nsuch as iron, calcium, and zinc. They are commonly used to support bone health, improve immune function, \nand prevent mineral deficiencies.",
                    new Product[] {
                            new Product(
                                    "Cholecalciferol + Minerals",
                                    ImagePath.cholecalciferolMinerals,
                                    "Calcium and Vitamin D and other musculoskeletal strengthening nutrients, which keeps the physical body strong and healthy.",
                                    230.00),
                            new Product(
                                    "Calcium Carbonate",
                                    ImagePath.calciumCarbonate,
                                    "Mineral-calcium supplements help prevent and treat osteoporosis, calcium malabsorption, and deficiency conditions",
                                    14.00),
                            new Product(
                                    "Ferrous Gluconate, Multivitamins and Minerals",
                                    ImagePath.multivitaminIronCalcium,
                                    "Replenishes Iron stores and increases hemoglobin levels in the body. Supports optimum blood health",
                                    545.00)
                    }),
            new Category(
                    "Bandages",
                    "Bandages",
                    ImagePath.bandages,
                    "Bandages are a type of medical product that is used to protect and cover wounds or injuries. \nThey should be used in situations where there is a cut, scrape, or other type of injury that \nneeds to be covered and protected to prevent further harm or infection.",
                    new Product[] {
                            new Product(
                                    "Gauze Bandage",
                                    ImagePath.gauzeBandage,
                                    "Gauze Bandage is used to hold a dressing in place on a wound, maintain pressure over a bulky pad to control bleeding, support an injured limb or joint, and apply pressure to a limb.",
                                    110.00),
                            new Product(
                                    "Roller Bandage",
                                    ImagePath.elasticBandage,
                                    "Roller Bandage is used to treat muscle sprains and strains by reducing the flow of blood to a particular area by the application of even stable pressure which can restrict swelling at the place of injury.",
                                    149.00),
                            new Product(
                                    "Tubular Bandage",
                                    ImagePath.tubularBandage,
                                    "Tubular elastic bandages are used to provide 360 degree uniform compression and support, and are commonly used to help treat lymphedema, prevent edema, and reduce post-burn scarring.",
                                    568.00)
                    }),
            new Category(
                    "Cotton\nItems",
                    "Cotton Items",
                    ImagePath.cottonItems,
                    "Cotton Items is a category of medical supplies that are used for various purposes such as cleaning wounds, \n applying ointments or creams, and absorbing bodily fluids. They can be used in treating a wide\n range of conditions, including cuts, bruises, burns, and other injuries that require gentle care.",
                    new Product[] {
                            new Product(
                                    "Cotton Swabs",
                                    ImagePath.cottonBuds,
                                    "Cotton Swabs are used to clean the ear canal by removing earwax and other dirt patches. Cotton swabs are also used for disinfection of patients' skin, treatment of wounds, and application of medical liquids.",
                                    195.00),
                            new Product(
                                    "Cotton balls",
                                    ImagePath.cottonBalls,
                                    "Cotton balls have multiple uses including cleaning out larger wounds with hydrogen peroxide or iodine, applying antiseptics or topical ointments, cleaning minor cuts and skin irritations, and stopping blood after an injection is given or blood withdrawn.",
                                    80.00),
                            new Product(
                                    "Cotton Pads",
                                    ImagePath.cottonPads,
                                    "Cotton pads are particularly useful in stopping or preventing bleeding from minor punctures, such as injections or venipuncture. Cotton pads are also often used for cleaning and disinfecting wounds, as well as applying medication or topical treatments.",
                                    128.00)
                    }),
            new Category(
                    "Antiseptics",
                    "Antiseptics",
                    ImagePath.antiseptics,
                    "Antiseptics are a type of medicine used to prevent infections by killing germs and bacteria on the \n skin or inside the body. They are typically used to clean wounds or prepare the skin \n before surgery to reduce the \n risk of infection. Antiseptics can also be used for \n everyday cuts and scrapes to prevent infection and promote healing.",
                    new Product[] {
                            new Product(
                                    "Povidone Iodine  15Ml",
                                    ImagePath.povidoneIodine,
                                    "Microbicide for topical use in minor wounds, cuts, abrasions, burns & post-op wounds. Eliminates all 5 major classes of harmful organisms: bacteria, fungi, virus, protozoa and spores, to stop infection and for faster wound healing",
                                    65.00),
                            new Product(
                                    "Hexetidine 60ml",
                                    ImagePath.hexitidine,
                                    "Hexetidine eliminates sore throat causing viruses and other oral transmitted bacteria to avoid coughs, colds or a flu. It is used for minor infections of mucous membranes.",
                                    114.00),
                            new Product(
                                    "Hydrogen Peroxide 120ml",
                                    ImagePath.hydrogenPeroxide,
                                    "Hydrogen peroxide is used for the treatment and cleansing of wounds. It can also be used as a bleaching agent. Its strong oxidizing properties make it a powerful disinfectant and cleaning solution",
                                    80.00)
                    }),
            new Category(
                    "Personal\nHygiene",
                    "Personal Hygiene",
                    ImagePath.personalHygiene,
                    "Personal hygiene is a category of products that are used to maintain cleanliness and promote \n"
                            + "good health. They are typically used to prevent the spread of germs, bacteria, and viruses, \n"
                            + "as well as to keep the body clean and fresh. Personal hygiene products can help prevent \n"
                            + " ailments such as infections, skin irritations, and bad odors. They are an essential part \n"
                            + "of a healthy lifestyle and should be used regularly to maintain good hygiene.",
                    new Product[] {
                            new Product(
                                    "Body Soap",
                                    ImagePath.bodySoap,
                                    "Hand bar soap cleans skin to wash away bacteria, providing essential cleansing to care for your family and prevent the spread of bacteria.",
                                    405.00),
                            new Product(
                                    "Shampoo",
                                    ImagePath.shampoo,
                                    "Nourishes scalp and roots to repair the appearance of damaged hair and to remove dirt and oil from the surface of the hair fibers and the scalp.",
                                    510.00),
                            new Product(
                                    "Toothpaste",
                                    ImagePath.toothpaste,
                                    "Prevent cavities, plaque build-up, tartar formation, gingivitis and sensitivity. It gives you fresh breath , protects your enamel, and removes stains, giving you a healthier, whiter smile.",
                                    383.00)
                    }),
            new Category(
                    "Surgical\nEquipment",
                    "Surgical Equipment",
                    ImagePath.surgicalEquipment,
                    " Surgical equipment is a type of medical equipment that is used by doctors during surgical \n"
                            + "procedures to help them operate on patients. This equipment is used in a variety of \n"
                            + "medical conditions that require surgery,such as broken bones, organ transplants, and tumor \n"
                            + " removal. It plays an essential role in helping medical professionals perform surgeries safely \n"
                            + " and effectively.",
                    new Product[] {
                            new Product(
                                    "Medical Gloves",
                                    ImagePath.surgicalGloves,
                                    "Disposable gloves are a type of protective handwear that are typically used once and then discarded. It provides a barrier between the skin of the healthcare worker and the patient, reducing the risk of transmitting infectious agents during medical procedures or examinations.",
                                    158),
                            new Product(
                                    "Syringe and Needle",
                                    ImagePath.disposableSyringe,
                                    "Syringe with Needle is used to administer medication or withdraw fluids from the body. They allow the fluids to be siphoned carefully and properly through veins. ",
                                    440.00),
                            new Product(
                                    "Medical Scissor",
                                    ImagePath.surgicalScissors,
                                    "Surgical scissors are used to cut soft tissue in order to perform various procedures, such as skin grafting, dental surgery, and other delicate surgical procedures. It has the ability to cut through soft tissues without causing excessive trauma or bleeding.",
                                    174.00),
                    }),
            new Category(
                    "Assistive\n Devices",
                    "Assistive Devices",
                    ImagePath.assistiveDevices,
                    "Assistive devices are tools or equipment that are designed to help individuals with \n"
                            + "physical or cognitive impairments to perform daily tasks and activities with greater \n"
                            + "ease and independence. They are used to assist people who have difficulty with mobility, \n"
                            + "vision, hearing, communication, or other areas of physical \n or cognitive functioning.",
                    new Product[] {
                            new Product(
                                    "Assistive cane",
                                    ImagePath.assistiveCane,
                                    "Assistive cane, used as a crutch or mobility aid. A cane can help redistribute weight from a lower leg that is weak or painful, improve stability by increasing the base of support, and provide tactile information about the ground to improve balance.",
                                    998.00),
                            new Product(
                                    "Walker",
                                    ImagePath.assistiveWalker,
                                    "A walker is a type of mobility aid used to help people who are still able to walk but need assistance. It is a four-legged frame that allows a person to lean on it for balance, support, and rest.",
                                    2100.00),
                            new Product(
                                    "Wheel Chair",
                                    ImagePath.wheelchair,
                                    "Wheelchair is a device used for people who cannot walk or have difficulty walking. It is a chair mounted on wheels that can be moved by the user or by an attendant. It is ideal for people who have difficulty standing or walking for long periods of time.",
                                    2597.00)
                    }),
    };

    public static void main(String[] args) {
        setLookAndFeel();
        openGreeting();
        openMainPanel();
        // openSearchPanel();
        // openExitPanel();
    }

    /**
     * Interface for lambdas. Basically, anything that accepts `PanelBuilder`
     * should have an anonymous function with types f :: JFrame -> JPanel
     */
    private static interface PanelBuilder {
        JPanel run(JFrame frame);
    }

    /**
     * This creates a panel if it is new, or reveals a previously created one.
     *
     * @param panelCode Unique identifier that allows the system to identify the
     *                  created panel.
     * @param panelName The title shown on the panel.
     * @param builder   An anonymous function that creates a JPanel instance when
     *                  called.
     */
    public static void spawnPanel(String panelCode, String panelName, PanelBuilder builder) {
        /// If we have created a `panelCode` before,
        /// then we should just reveal that.
        if (panelCode != null && openedFrames.containsKey(panelCode)) {
            /// If the panel is found, then just hide the others.
            /// and reveal this one.

            for (Entry<String, JFrame> openedFrameEntry : openedFrames.entrySet()) {
                String key = openedFrameEntry.getKey();
                JFrame frame = openedFrameEntry.getValue();

                if (key == null || !key.equals(panelCode)) {
                    frame.setVisible(false);
                } else {
                    frame.setVisible(true);
                }
            }

        } else {
            /// If the panel isn't found, then hide all others.
            for (JFrame openedFrame : openedFrames.values()) {
                openedFrame.setVisible(false);
            }

            /// Create a new frame and panel.
            JFrame frame = new JFrame(panelName);
            JPanel panel = builder.run(frame);

            frame.setMinimumSize(new Dimension(640, 500));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setVisible(true);
            frame.setResizable(false);

            frame.pack();
            openedFrames.put(panelCode, frame);
        }
    }

    /**
     * Opens the greeting panel.
     */
    public static void openGreeting() {
        // final String panelCode = "GREETING_PANEL";
        final String panelName = "Greeting";

        spawnPanel(null, panelName, (frame) -> new GreetingPanel(frame));
    }

    /**
     * Opens the main panel (should be the main window.)
     */
    public static void openMainPanel() {
        final String panelCode = "MAIN_PANEL";
        final String panelName = "Main";

        spawnPanel(panelCode, panelName, (frame) -> new MainPanel(frame));
    }

    /**
     * Opens the product navigation panel
     */
    public static void openNavigationPanel(MainPanel mainPanel, Category category) {
        final String panelName = "Product Navigation - " + category.title;

        spawnPanel(null, panelName, (frame) -> new NavigationPanel(mainPanel, category, frame));
    }

    public static void openSearchPanel() {
        // final String panelCode = "PRODUCT_SEARCH_PANEL";
        final String panelName = "Product Search ";

        spawnPanel(null, panelName, (frame) -> new SearchPanel(frame));
    }

    public static void openExitPanel(MainPanel mainPanel) {
        // final String panelCode = "EXIT_PANEL";
        final String panelName = "Exit";

        spawnPanel(null, panelName, (frame) -> new ExitPanel(mainPanel, frame));
    }

    public static void openVoidPanel(MainPanel mainPanel) {
        final String panelName = "Void Item";

        spawnPanel(null, panelName, (frame) -> new VoidPanel(mainPanel));
    }

    /**
     * Sets the theme of the program to match the current operating system.
     */
    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * A function that returns a cached version of the scaled icon (logo).
     * If the icon is called with a new `width` and `height`, then a new
     * `ImageIcon` is created, else it is returned from the cache.
     *
     * @param width  The width of the scaled icon
     * @param height The height of the scaled icon
     * @return The cached/new `ImageIcon`
     */
    private static ImageIcon getScaledImage(String path, int width, int height) {
        if (path == null) {
            path = ImagePath.icon;
        }

        /// Key to be used in the cache.
        String key = path + ";" + Integer.toString(width) + ";" +
                Integer.toString(height);

        System.out.println(key);

        /// If the key is in the cache (aka it has been called before)
        /// Then just return the saved one.
        if (cachedIcons.containsKey(key)) {
            return cachedIcons.get(key);
        } else {
            /// Otherwise, scale the icon.
            Image image = new ImageIcon(path).getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);

            /// After creating the scaled icon instance, put it in the cache.
            cachedIcons.put(key, icon);

            return icon;
        }
    }

    private static class GreetingPanel extends JPanel {
        /**
         * Creates a cached `JLabel` with the logo.
         *
         * @return Logo JLabel
         */
        private static JLabel createLogoLabel() {
            ImageIcon scaledIcon = getScaledImage(ImagePath.icon, 256, 256);
            JLabel picLabel = new JLabel(scaledIcon);

            return picLabel;
        }

        /**
         * Creates the multilined welcome label below the logo.
         *
         * @return Welcome Label
         */
        private static JPanel createWelcomeLabel() {
            /// PANEL SETUP
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();

            /// This is hard coded.
            String label = "Welcome to\nUncle Andy's Pharmacy";
            for (String line : label.split("\n")) {
                JLabel jLabel = new JLabel(line);

                /// Create a copy of the font with the set font style and font size.
                Font boldFont = new Font("Segoe UI", Font.BOLD, 20);

                jLabel.setFont(boldFont);
                panel.add(jLabel, constraints);

                ++constraints.gridy;
            }

            return panel;
        }

        /**
         * Creates the button that opens the next window.
         *
         * @return Button
         */
        private static Component createEntryButton() {
            JButton button = new JButton("Make a Purchase");
            button.setFocusPainted(false);
            button.setMargin(new Insets(12, 0, 12, 0));
            button.setPreferredSize(new Dimension(192, 48));
            button.addActionListener(e -> {
                openMainPanel();
            });

            return button;
        }

        /**
         * Creates the button that opens the exits the entire program.
         *
         * @param frame Reference to the frame that holds the entire program.
         * @return Button
         */
        private static Component createExitButton(JFrame frame) {
            JButton button = new JButton("Exit");
            button.setFocusPainted(false);
            button.setMargin(new Insets(12, 0, 12, 0));
            button.setPreferredSize(new Dimension(192, 48));
            button.addActionListener(e -> {
                frame.dispose();
            });

            return button;
        }

        private GreetingPanel(JFrame frame) {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.insets = new Insets(0, 0, -24, 0);

            this.add(createLogoLabel(), constraints);

            ++constraints.gridy;

            constraints.insets = new Insets(0, 0, 12, 0);

            this.add(createWelcomeLabel(), constraints);

            ++constraints.gridy;

            this.add(createEntryButton(), constraints);

            ++constraints.gridy;

            this.add(createExitButton(frame), constraints);

            constraints.insets = new Insets(0, 0, 0, 0);
        }
    }

    private static class MainPanel extends JPanel {
        private static String[][] data = {};
        private EntrySubmenuPanel entrySubmenuPanel;

        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private MainPanel(JFrame frame) {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.anchor = GridBagConstraints.NORTHWEST;
            constraints.weightx = 1.0;

            this.add(createTopBar(), constraints);

            ++constraints.gridy;
            constraints.fill = GridBagConstraints.HORIZONTAL;

            this.add(createCategoryArea(), constraints);

            constraints.weighty = 1.0;
            this.add(new JLabel(""), constraints);

            ++constraints.gridy;
            constraints.weighty = 0.0;

            ++constraints.gridy;
            constraints.gridheight = GridBagConstraints.REMAINDER;

            this.add(entrySubmenuPanel = new EntrySubmenuPanel(this, frame), constraints);
        }

        private Component createTopBar() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = generateConstraints();

            panel.add(createSmallLogo(), constraints);
            constraints.weightx = 1;
            ++constraints.gridx;

            panel.add(createTitle(), constraints);

            return panel;
        }

        private Component createSmallLogo() {
            ImageIcon icon = getScaledImage(ImagePath.icon, 64, 64);
            JLabel picLabel = new JLabel(icon);

            return picLabel;
        }

        private Component createTitle() {
            JLabel jlabel = new JLabel("Uncle Andy's Pharmacy");
            jlabel.setFont(new Font(jlabel.getName(), Font.BOLD, 20));

            return jlabel;
        }

        private Component createCategoryButton(int idx, Category category) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBackground(Colors.transparent);

            GridBagConstraints constraints = generateConstraints();
            constraints.insets = new Insets(0, 0, 0, 0);

            ImageIcon icon = getScaledImage(category.iconPath, 42, 42);
            JButton button = new JButton(icon);
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(52, 52));
            button.addActionListener(e -> {
                openNavigationPanel(this, category);
            });

            panel.add(button, constraints);

            ++constraints.gridy;

            for (String line : category.title.split("\n")) {
                JLabel jLabel = new JLabel(line);
                panel.add(jLabel, constraints);

                ++constraints.gridy;
            }

            return panel;
        }

        private Component createCategoryArea() {
            final int rowCount = 2;
            final int columnCount = Math.ceilDiv(categories.length, rowCount);

            JPanel gridPanel = new JPanel();
            gridPanel.setBorder(new CompoundBorder(new TitledBorder("System Database"), new EmptyBorder(8, 0, 0, 0)));
            gridPanel.setLayout(new GridBagLayout());
            gridPanel.setBackground(Colors.transparent);

            GridBagConstraints rowConstraints = generateConstraints();
            rowConstraints.ipadx = 4;
            rowConstraints.ipady = 4;
            rowConstraints.weightx = 1.0;
            rowConstraints.weighty = 1.0;
            rowConstraints.insets = new Insets(8, 8, 8, 8);
            rowConstraints.anchor = GridBagConstraints.NORTH;
            rowConstraints.fill = GridBagConstraints.HORIZONTAL;

            for (int y = 0; y < rowCount; ++y) {
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new GridBagLayout());
                rowPanel.setBackground(Colors.transparent);

                GridBagConstraints columnConstraints = generateConstraints();
                columnConstraints.weightx = 1.0;
                columnConstraints.weighty = 1.0;
                columnConstraints.anchor = GridBagConstraints.NORTH;

                for (int x = 0; x < columnCount; ++x) {
                    int i = y * columnCount + x;

                    if (i >= categories.length) {
                        break;
                    }

                    Category category = categories[i];
                    Component button = createCategoryButton(i + 1, category);

                    rowPanel.add(button, columnConstraints);
                    ++columnConstraints.gridx;
                }

                gridPanel.add(rowPanel, rowConstraints);
                ++rowConstraints.gridy;
            }

            return gridPanel;
        }

        private static class EntrySubmenuPanel extends JPanel {
            private DefaultTableModel cartModel;
            private MainPanel mainPanel;
            private JFrame frame;
            private JTable table;

            private EntrySubmenuPanel(MainPanel mainPanel, JFrame frame) {
                this.mainPanel = mainPanel;
                this.frame = frame;
                this.setLayout(new GridBagLayout());

                GridBagConstraints constraints = generateConstraints();
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.fill = GridBagConstraints.BOTH;

                this.add(createSummaryArea(), constraints);

                constraints.insets = new Insets(0, 8, 0, 8);
                ++constraints.gridx;
                this.add(createRightSubmenu(), constraints);

                constraints.insets = new Insets(0, 0, 0, 0);
            }

            private void addRowToTable(int index,
                    String name,
                    double price,
                    int quantity,
                    double total) {
                String[] newRow = {
                        "Index (" + index + ")",
                        name,
                        Double.toString(price),
                        Integer.toString(quantity),
                        Double.toString(total),
                };
                cartModel.addRow(newRow);
            }

            private void clearCart() {
                for (int y = cartModel.getRowCount() - 1; y >= 0; --y) {
                    cartModel.removeRow(y);
                }
            }

            private JButton createSearchButton() {
                JButton button = new JButton("Search Product");
                button.addActionListener(e -> {
                    openSearchPanel();
                });

                return button;
            }

            private JButton createConfirmPurchaseButton() {
                JButton button = new JButton("Confirm Purchases");
                button.addActionListener(e -> {
                    openExitPanel(mainPanel);
                });
                return button;
            }

            private JButton createVoidButton() {
                JButton button = new JButton("Void Item");
                button.addActionListener(e -> {
                    openVoidPanel(mainPanel);
                });
                return button;
            }

            private JButton createClearCartButton() {
                JButton button = new JButton("Clear Cart");
                button.addActionListener(e -> {
                    /// Ask for confirmation.
                    int answer = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to clear the selected products?");
                    switch (answer) {
                        case JOptionPane.YES_OPTION:
                            int itemCount = cartModel.getRowCount();

                            clearCart();

                            JOptionPane.showMessageDialog(null, "Cleared " + itemCount + " items.");
                            return;
                        case JOptionPane.NO_OPTION:
                        default:
                            /// Since they have not selected yes, then we do nothing.
                            return;
                    }
                });

                return button;
            }

            private JButton createExitButton() {
                JButton button = new JButton("Exit");
                button.addActionListener(e -> {
                    frame.dispose();
                });

                return button;
            }

            private Component createRightSubmenu() {
                JPanel panel = new JPanel();
                panel.setBorder(new CompoundBorder(new TitledBorder(""), new EmptyBorder(8, 0, 0, 0)));
                panel.setLayout(new GridBagLayout());
                panel.setBackground(Colors.background);

                GridBagConstraints constraints = generateConstraints();
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.weightx = 1;

                JButton search = createSearchButton();
                JButton confirm = createConfirmPurchaseButton();
                JButton voidItem = createVoidButton();
                JButton clear = createClearCartButton();
                JButton exit = createExitButton();

                panel.add(search, constraints);
                ++constraints.gridy;

                panel.add(confirm, constraints);
                ++constraints.gridy;

                panel.add(voidItem, constraints);
                ++constraints.gridy;

                panel.add(clear, constraints);
                ++constraints.gridy;

                panel.add(exit, constraints);
                ++constraints.gridy;

                JButton last = new JButton("Add dummy row");
                last.addActionListener(e -> {
                    /// Add item to table
                    addRowToTable(cartModel.getRowCount(), "Name", 0, 1, 0);
                });
                panel.add(last, constraints);
                ++constraints.gridy;

                return panel;
            }

            private void setupTableModels() {
                final String[] columnNames = { "Code", "Item", "Price", "Quantity", "Total" };
                cartModel = new DefaultTableModel(columnNames, 0);
                for (String[] row : data) {
                    cartModel.addRow(row);
                }
                cartModel.addTableModelListener(e -> table.revalidate());
            }

            private Component createSummaryArea() {
                JPanel panel = new JPanel();
                panel.setBorder(
                        new CompoundBorder(
                                new TitledBorder("Summary of Purchases"),
                                new EmptyBorder(8, 0, 0, 0)));
                panel.setLayout(new GridBagLayout());
                panel.setBackground(Colors.background);

                setupTableModels();

                table = new JTable(cartModel);
                table.setEnabled(false);
                table.getTableHeader().setResizingAllowed(false);
                table.getTableHeader().setReorderingAllowed(false);
                table.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
                table.setFont(new Font("Segoe UI", Font.BOLD, 12));
                table.setRowHeight((int) Math.floor(table.getFont().getSize() * 2.5));

                TableColumnModel tcm = table.getColumnModel();
                tcm.removeColumn(tcm.getColumn(0));

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                Enumeration<TableColumn> enumeration = tcm.getColumns();
                while (enumeration.hasMoreElements()) {
                    enumeration.nextElement().setCellRenderer(centerRenderer);
                }

                GridBagConstraints constraints = generateConstraints();
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.fill = GridBagConstraints.BOTH;
                constraints.weightx = 1;
                constraints.weighty = 1;

                JScrollPane scrollPane = new JScrollPane(table);
                panel.add(scrollPane, constraints);

                return panel;
            }

            private String[][] getCartItems() {
                int height = cartModel.getRowCount();
                int width = cartModel.getColumnCount();

                String[][] result = new String[height][width];
                for (int y = 0; y < height; ++y) {
                    for (int x = 0; x < width; ++x) {
                        result[y][x] = (String) cartModel.getValueAt(y, x);
                    }
                }

                return result;
            }
        }

        private void addProduct(Product product, int count) {
            double totalPrice = product.getPrice() * count;

            entrySubmenuPanel.addRowToTable(
                    entrySubmenuPanel.cartModel.getRowCount(),
                    product.getTitle(),
                    product.getPrice(),
                    count,
                    totalPrice);
        }

        private void removeRows(boolean[] activations) {
            for (int i = activations.length - 1; i >= 0; --i) {
                if (activations[i]) {
                    entrySubmenuPanel.cartModel.removeRow(i);
                }
            }
        }
    }

    private static class SearchPanel extends JPanel {
        private static final String[] columnNames = { "Name", "Category" };

        private final DefaultTableModel model;

        private SearchPanel(JFrame frame) {
            this.model = new DefaultTableModel(columnNames, 0);

            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 1.0;

            this.add(createDisplayTable(), constraints);
            ++constraints.gridy;

            this.add(createSearchBar(), constraints);
        }

        private Component createDisplayTable() {
            JTable table = new JTable(model);

            for (Category category : categories) {
                for (Product product : category.getProducts()) {
                    String[] row = { product.getTitle(), category.getName() };
                    model.addRow(row);
                }
            }
            model.addTableModelListener(e -> {
                table.revalidate();
            });

            table.setEnabled(false);
            table.getTableHeader().setResizingAllowed(false);
            table.getTableHeader().setReorderingAllowed(false);
            table.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.setFont(new Font("Segoe UI", Font.BOLD, 12));
            table.setRowHeight((int) Math.floor(table.getFont().getSize() * 2.5));

            DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
            leftRenderer.setHorizontalAlignment(JLabel.LEFT);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            TableColumnModel tcm = table.getColumnModel();
            Enumeration<TableColumn> enumeration = tcm.getColumns();
            while (enumeration.hasMoreElements()) {
                enumeration.nextElement().setCellRenderer(centerRenderer);
            }
            tcm.getColumn(0).setCellRenderer(leftRenderer);

            JScrollPane scrollPane = new JScrollPane(table);

            return scrollPane;
        }

        private Component createSearchBar() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;

            JTextField textField = new JTextField();
            TextPrompt textPrompt = new TextPrompt("Enter a keyword (`Amoxicillin`)", textField);
            textPrompt.changeAlpha(0.5f);

            textField.getDocument().addDocumentListener(new DocumentListener() {
                private String previous = "";
                private String now = "";

                public void changedUpdate(DocumentEvent e) {
                    common();
                }

                public void removeUpdate(DocumentEvent e) {
                    common();
                }

                public void insertUpdate(DocumentEvent e) {
                    common();
                }

                public void common() {
                    String captured = textField.getText().trim();

                    this.previous = now;
                    this.now = captured;
                    if (previous.equals(now)) {
                        System.out.println("It didn't change.");
                    } else {
                        String[][] sorted = getRows();
                        Arrays.sort(sorted,
                                Comparator.comparingInt(row -> {
                                    String name = row[0].toLowerCase();
                                    String pattern = captured.toLowerCase();

                                    int lowest = levenshtein(name, pattern);
                                    for (String target : name.split(" ")) {
                                        for (String keyword : pattern.split(" ")) {
                                            int distance = levenshtein(target.trim(), keyword.trim());
                                            if (distance < lowest) {
                                                lowest = distance;
                                            }
                                        }
                                    }
                                    return lowest;
                                }));

                        for (int y = model.getRowCount() - 1; y >= 0; --y) {
                            model.removeRow(y);
                        }
                        for (String[] row : sorted) {
                            model.addRow(row);
                        }
                        System.out.println("You wrote: " + captured);
                    }
                }
            });

            constraints.weightx = 1.0;
            panel.add(textField, constraints);
            ++constraints.gridx;
            constraints.weightx = 0.0;

            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> {
                openMainPanel();
            });

            panel.add(backButton, constraints);

            return panel;
        }

        private String[][] getRows() {
            int height = model.getRowCount();
            int width = model.getColumnCount();

            String[][] result = new String[height][width];
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    result[y][x] = (String) model.getValueAt(y, x);
                }
            }

            return result;
        }
    }

    /// This is shown when a category button has been pressed.
    private static class NavigationPanel extends JPanel {
        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private MainPanel mainPanel;
        private Category category;
        private JFrame frame;
        private final static String navigationInstructions = "Select the wanted item by clicking on it. In the pop-up window, descriptions and proper use of the item will be shown.\nOnce correct, indicate the desired quantity in the specified text field and click proceed, otherwise return\n";

        public String getInstructions() {
            String categoryInstructions = category.getInstructions();

            String instructions = navigationInstructions + " "
                    + (categoryInstructions == null ? "" : categoryInstructions);

            return instructions;
        }

        private NavigationPanel(MainPanel mainPanel, Category category, JFrame frame) {
            this.mainPanel = mainPanel;
            this.category = category;
            this.frame = frame;

            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 0.0;
            constraints.weighty = 1.0;
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.fill = GridBagConstraints.VERTICAL;

            this.add(createCategoryTitle(), constraints);

            ++constraints.gridy;

            constraints.fill = GridBagConstraints.BOTH;
            this.add(createNavigationInstructions(), constraints);
            constraints.fill = GridBagConstraints.VERTICAL;

            ++constraints.gridy;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(createProductList(), constraints);

            constraints.gridy += 2;
            constraints.anchor = GridBagConstraints.SOUTH;
            constraints.fill = GridBagConstraints.REMAINDER;
            this.add(createNavigationButtons(), constraints);

        }

        private Component createCategoryTitle() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 1;
            constraints.gridwidth = GridBagConstraints.REMAINDER;

            JLabel label = new JLabel(category.getName());
            Font boldFont = new Font(label.getFont().getName(), Font.BOLD, 20);
            label.setFont(boldFont);

            panel.add(label);

            return panel;
        }

        private Component createNavigationInstructions() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new CompoundBorder(new TitledBorder("Instructions"), new EmptyBorder(8, 0, 0, 0)));

            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 1;

            for (String line : getInstructions().split("\n")) {
                panel.add(new JLabel(line), constraints);
                ++constraints.gridy;
            }

            return panel;
        }

        private Component createProductButton(Product product) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBackground(Colors.transparent);

            GridBagConstraints constraints = generateConstraints();
            constraints.insets = new Insets(0, 0, 0, 0);

            ImageIcon icon = getScaledImage(product.iconPath, 42, 42);
            JButton button = new JButton(icon);
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(92, 92));
            button.addActionListener(e -> {
                int chosen = -1;
                do {
                    try {
                        String rawInput = JOptionPane.showInputDialog(null, "Quantity: ");
                        if (rawInput == null) {
                            /// The user clicked on 'Cancel'.
                            return;
                        }
                        int input = Integer.parseInt(rawInput);
                        if (input <= 0) {
                            continue;
                        }
                        chosen = input;
                        break;
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                        continue;
                    }
                } while (chosen == -1);

                mainPanel.addProduct(product, chosen);
            });

            panel.add(button, constraints);

            ++constraints.gridy;

            return panel;
        }

        private Component createProductList() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.anchor = GridBagConstraints.CENTER;

            for (int i = 0; i < category.getProducts().length; i++) {
                Product product = category.products[i];
                Component button = createProductButton(product);

                constraints.gridy = 0;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                panel.add(button, constraints);

                constraints.gridy++;

                constraints.ipadx = 0;
                constraints.ipady = 0;
                JLabel name = new JLabel(product.getTitle());
                panel.add(name, constraints);

                constraints.gridy++;
                JLabel price = new JLabel("P " + Double.toString(product.getPrice()));
                panel.add(price, constraints);

                constraints.gridx++;
            }

            return panel;
        }

        private Component createContinueButton() {
            JButton button = new JButton("Continue");
            button.addActionListener(e -> {
                openMainPanel();
            });
            return button;
        }

        private Component createExitButton() {
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> {
                frame.dispose();
            });

            return exitButton;
        }

        private Component createNavigationButtons() {
            JPanel panel = new JPanel();
            GridBagConstraints constraints = new GridBagConstraints();
            panel.setLayout(new GridBagLayout());

            Component continueButton = createContinueButton();
            constraints.insets = new Insets(0, 50, 0, 50);
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1.0;
            constraints.ipadx = 100;
            constraints.ipady = 50;
            panel.add(continueButton, constraints);

            constraints.gridx += 2;

            panel.add(createExitButton(), constraints);

            return panel;
        }

    }

    private static class VoidPanel extends JPanel {
        private final boolean[] activated;
        private final MainPanel mainPanel;

        private VoidPanel(MainPanel mainPanel) {
            this.mainPanel = mainPanel;
            this.activated = new boolean[mainPanel.entrySubmenuPanel.getCartItems().length];

            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.anchor = GridBagConstraints.NORTHWEST;

            constraints.weightx = 1;
            this.add(createTitleBar(), constraints);
            ++constraints.gridy;

            constraints.weighty = 1;
            this.add(createSelectionBar(), constraints);
            ++constraints.gridy;

            constraints.weighty = 0;
            this.add(createButtonBar(), constraints);
        }

        private Component createSelectionBar() {
            JPanel columnPanel = new JPanel();
            columnPanel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.anchor = GridBagConstraints.NORTHWEST;

            String[][] cart = mainPanel.entrySubmenuPanel.getCartItems();
            for (int i = 0; i < cart.length; ++i) {
                final int index = i;
                activated[index] = false;

                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new GridBagLayout());

                GridBagConstraints rowConstraints = generateConstraints();
                rowConstraints.fill = GridBagConstraints.BOTH;

                rowPanel.add(new JLabel(cart[i][1]), rowConstraints);
                ++rowConstraints.gridx;

                JRadioButton radio = new JRadioButton();
                rowPanel.add(radio, rowConstraints);
                radio.addActionListener(e -> {
                    activated[index] = radio.isSelected();
                });

                columnPanel.add(rowPanel, constraints);
                ++constraints.gridy;
            }

            return columnPanel;
        }

        private Component createButtonBar() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.insets = new Insets(0, 12, 0, 12);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> {
                /// Just cancel it.
                openMainPanel();
            });

            JButton saveButton = new JButton("Void Selections");
            saveButton.addActionListener(e -> {

                mainPanel.removeRows(activated);
                openMainPanel();
            });

            panel.add(cancelButton, constraints);
            ++constraints.gridx;
            panel.add(saveButton, constraints);

            return panel;
        }

        private Component createTitleBar() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = generateConstraints();
            constraints.insets = new Insets(0, 8, 0, 0);

            panel.add(new JLabel(getScaledImage(ImagePath.icon, 64, 64)), constraints);

            ++constraints.gridx;

            JLabel title = new JLabel("Void Item");
            title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
            panel.add(title, constraints);

            ++constraints.gridx;
            constraints.weightx = 1.0;
            panel.add(new JLabel(""), constraints);

            return panel;
        }
    }

    private static class ExitPanel extends JPanel {
        private MainPanel mainPanel;
        private DefaultTableModel model;
        private JTable table;

        @Override
        public Insets getInsets() {
            return new Insets(12, 12, 12, 12);
        }

        private ExitPanel(MainPanel mainPanel, JFrame frame) {
            this.setLayout(new GridBagLayout());
            this.mainPanel = mainPanel;

            System.out.println(mainPanel);

            GridBagConstraints constraints = generateConstraints();
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.anchor = GridBagConstraints.NORTHWEST;
            constraints.fill = GridBagConstraints.BOTH;

            this.add(createSummaryPanel(), constraints);
            constraints.insets = new Insets(0, 8, 0, 0);
            ++constraints.gridx;
            this.add(createPaymentPanel(), constraints);

            constraints.insets = new Insets(0, 0, 0, 0);

        }

        private void setupTableModels() {
            final String[] columnNames = { "Code", "Item", "Price", "Quantity", "Total" };

            double totalPrice = 0;

            model = new DefaultTableModel(columnNames, 0);
            for (String[] row : mainPanel.entrySubmenuPanel.getCartItems()) {
                model.addRow(row);
                totalPrice += Double.parseDouble(row[4]);
            }

            model.addRow(new String[] { "", " ", " ", "Total:", "P" + totalPrice });
            model.addTableModelListener(e -> table.revalidate());
        }

        private Component createSummaryPanel() {
            JPanel panel = new JPanel();
            panel.setBorder(
                    new CompoundBorder(
                            new TitledBorder("Summary of Purchases"),
                            new EmptyBorder(8, 0, 0, 0)));
            panel.setLayout(new GridBagLayout());
            panel.setBackground(Colors.background);

            setupTableModels();

            table = new JTable(model);
            table.setEnabled(false);
            table.getTableHeader().setResizingAllowed(false);
            table.getTableHeader().setReorderingAllowed(false);
            table.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            table.setFont(new Font("Segoe UI", Font.BOLD, 12));
            table.setRowHeight((int) Math.floor(table.getFont().getSize() * 2.5));

            TableColumnModel tcm = table.getColumnModel();
            tcm.removeColumn(tcm.getColumn(0));

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            Enumeration<TableColumn> enumeration = tcm.getColumns();
            while (enumeration.hasMoreElements()) {
                enumeration.nextElement().setCellRenderer(centerRenderer);
            }

            GridBagConstraints constraints = generateConstraints();
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 1;
            constraints.weighty = 1;

            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, constraints);

            return panel;
        }

        private Component createPaymentPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBorder(
                    new CompoundBorder(
                            new TitledBorder("Payment Information"),
                            new EmptyBorder(8, 0, 0, 0)));

            GridBagConstraints constraints = generateConstraints();
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;

            panel.add(new JLabel("Total Price:..."), constraints);
            ++constraints.gridy;
            panel.add(new JLabel("Enter Price:"), constraints);
            ++constraints.gridy;
            panel.add(new JTextField("..."), constraints);
            ++constraints.gridy;
            panel.add(new JRadioButton("Request for Senior Discount"), constraints);
            ++constraints.gridy;
            panel.add(new JLabel("Change:"), constraints);
            ++constraints.gridy;

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> {
                openMainPanel();
            });

            panel.add(closeButton, constraints);

            return panel;
        }
    }

    /// Data classes

    public static class Category {
        private final String title;

        public String getTitle() {
            return title;
        }

        private final String name;

        public String getName() {
            return name;
        }

        private final String iconPath;

        public String getIconPath() {
            return iconPath;
        }

        private final String instructions;

        public String getInstructions() {
            return instructions;
        }

        private final Product[] products;

        public Product[] getProducts() {
            return products;
        }

        public Category(String title, String name, String iconPath, String instructions, Product[] products) {
            this.title = title;
            this.name = name;
            this.iconPath = iconPath;
            this.instructions = instructions;
            this.products = products;
        }
    }

    public static class Product {
        private final String title;

        public String getTitle() {
            return title;
        }

        private final String iconPath;

        public String getIconPath() {
            return iconPath;
        }

        private final String description;

        public String getDescription() {
            return description;
        }

        private final double price;

        public double getPrice() {
            return price;
        }

        public Product(String title, String iconPath, String description, double price) {
            this.title = title;
            this.iconPath = iconPath;
            this.description = description;
            this.price = price;
        }
    }

    /// Helper methods

    /**
     * Helper method that returns a [GridBagConstraints] with `gridx` and `gridy`
     * already set to 0.
     *
     * @return GridBagConstraints
     */
    private static GridBagConstraints generateConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        return constraints;
    }

    public static int levenshtein(String source, String target) {
        int sourceLength = source.length();
        int targetLength = target.length();
        int[][] distanceMatrix = new int[sourceLength + 1][targetLength + 1];

        // Initialize the distance matrix. At the start of the algorithm, the distance
        // between any prefix of the source string and any prefix of the target string
        // is unknown, so we set all values in the distance matrix to zero.
        for (int i = 0; i <= sourceLength; i++) {
            for (int j = 0; j <= targetLength; j++) {
                distanceMatrix[i][j] = 0;
            }
        }

        // Initialize the first column of the matrix. The distance between any prefix
        // of the source string and the empty string can be found by deleting all
        // characters in the prefix, so we set the values in the first column of the
        // distance matrix accordingly.
        for (int i = 1; i <= sourceLength; i++) {
            distanceMatrix[i][0] = i;
        }

        // Initialize the first row of the matrix. The distance between the empty
        // string and any prefix of the target string can be found by inserting all
        // characters in the prefix, so we set the values in the first row of the
        // distance matrix accordingly.
        for (int j = 1; j <= targetLength; j++) {
            distanceMatrix[0][j] = j;
        }

        // Fill in the rest of the matrix. We can compute the distance between any
        // prefix of the source string and any prefix of the target string by examining
        // the last character of each prefix. If the last characters are the same,
        // then the distance is the same as the distance between the prefixes that
        // don't include those last characters. If the last characters are different,
        // then the distance is one more than the minimum of the distances between the
        // prefixes that don't include the last character of the source string, the
        // prefixes that don't include the last character of the target string, and the
        // prefixes that don't include either last character. We can use the distance
        // values that we've already computed to fill in the rest of the distance
        // matrix.
        for (int j = 1; j <= targetLength; j++) {
            for (int i = 1; i <= sourceLength; i++) {
                int substitutionCost = source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1000;
                distanceMatrix[i][j] = Math.min(
                        Math.min(
                                distanceMatrix[i - 1][j] + 1,
                                distanceMatrix[i][j - 1] + 1),
                        distanceMatrix[i - 1][j - 1] + substitutionCost);
            }
        }

        // The value in the bottom right corner of the distance matrix is the distance
        // between the entire source string and the entire target string.
        return distanceMatrix[sourceLength][targetLength];
    }

    /// External Code
    /**
     * The TextPrompt class will display a prompt over top of a text component when
     * the Document of the text field is empty. The Show property is used to
     * determine the visibility of the prompt.
     *
     * The Font and foreground Color of the prompt will default to those properties
     * of the parent text component. You are free to change the properties after
     * class construction.
     */
    public static class TextPrompt extends JLabel implements FocusListener, DocumentListener {
        public enum Show {
            ALWAYS,
            FOCUS_GAINED,
            FOCUS_LOST;
        }

        private JTextComponent component;
        private Document document;

        private Show show;
        private boolean showPromptOnce;
        private int focusLost;

        public TextPrompt(String text, JTextComponent component) {
            this(text, component, Show.ALWAYS);
        }

        public TextPrompt(String text, JTextComponent component, Show show) {
            this.component = component;
            setShow(show);
            document = component.getDocument();

            setText(text);
            setFont(component.getFont());
            setForeground(component.getForeground());
            setBorder(new EmptyBorder(component.getInsets()));
            setHorizontalAlignment(JLabel.LEADING);

            component.addFocusListener(this);
            document.addDocumentListener(this);

            component.setLayout(new BorderLayout());
            component.add(this);
            checkForPrompt();
        }

        /**
         * Convenience method to change the alpha value of the current foreground
         * Color to the specifice value.
         *
         * @param alpha value in the range of 0 - 1.0.
         */
        public void changeAlpha(float alpha) {
            changeAlpha((int) (alpha * 255));
        }

        /**
         * Convenience method to change the alpha value of the current foreground
         * Color to the specific value.
         *
         * @param alpha value in the range of 0 - 255.
         */
        public void changeAlpha(int alpha) {
            alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

            Color foreground = getForeground();
            int red = foreground.getRed();
            int green = foreground.getGreen();
            int blue = foreground.getBlue();

            Color withAlpha = new Color(red, green, blue, alpha);
            super.setForeground(withAlpha);
        }

        /**
         * Convenience method to change the style of the current Font. The style
         * values are found in the Font class. Common values might be:
         * Font.BOLD, Font.ITALIC and Font.BOLD + Font.ITALIC.
         *
         * @param style value representing the the new style of the Font.
         */
        public void changeStyle(int style) {
            setFont(getFont().deriveFont(style));
        }

        /**
         * Get the Show property
         *
         * @return the Show property.
         */
        public Show getShow() {
            return show;
        }

        /**
         * Set the prompt Show property to control when the promt is shown.
         * Valid values are:
         *
         * Show.AWLAYS (default) - always show the prompt
         * Show.Focus_GAINED - show the prompt when the component gains focus
         * (and hide the prompt when focus is lost)
         * Show.Focus_LOST - show the prompt when the component loses focus
         * (and hide the prompt when focus is gained)
         *
         * @param show a valid Show enum
         */
        public void setShow(Show show) {
            this.show = show;
        }

        /**
         * Get the showPromptOnce property
         *
         * @return the showPromptOnce property.
         */
        public boolean getShowPromptOnce() {
            return showPromptOnce;
        }

        /**
         * Show the prompt once. Once the component has gained/lost focus
         * once, the prompt will not be shown again.
         *
         * @param showPromptOnce when true the prompt will only be shown once,
         *                       otherwise it will be shown repeatedly.
         */
        public void setShowPromptOnce(boolean showPromptOnce) {
            this.showPromptOnce = showPromptOnce;
        }

        /**
         * Check whether the prompt should be visible or not. The visibility
         * will change on updates to the Document and on focus changes.
         */
        private void checkForPrompt() {
            // Text has been entered, remove the prompt

            if (document.getLength() > 0) {
                setVisible(false);
                return;
            }

            // Prompt has already been shown once, remove it

            if (showPromptOnce && focusLost > 0) {
                setVisible(false);
                return;
            }

            // Check the Show property and component focus to determine if the
            // prompt should be displayed.

            if (component.hasFocus()) {
                if (show == Show.ALWAYS
                        || show == Show.FOCUS_GAINED)
                    setVisible(true);
                else
                    setVisible(false);
            } else {
                if (show == Show.ALWAYS
                        || show == Show.FOCUS_LOST)
                    setVisible(true);
                else
                    setVisible(false);
            }
        }

        // Implement FocusListener

        public void focusGained(FocusEvent e) {
            checkForPrompt();
        }

        public void focusLost(FocusEvent e) {
            focusLost++;
            checkForPrompt();
        }

        // Implement DocumentListener

        public void insertUpdate(DocumentEvent e) {
            checkForPrompt();
        }

        public void removeUpdate(DocumentEvent e) {
            checkForPrompt();
        }

        public void changedUpdate(DocumentEvent e) {
        }
    }
}
