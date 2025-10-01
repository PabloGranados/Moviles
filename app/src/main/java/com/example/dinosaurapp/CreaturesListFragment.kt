package com.example.dinosaurapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragment que muestra la lista de criaturas de un período
 */
class CreaturesListFragment : Fragment() {

    private lateinit var rvCreatures: RecyclerView
    private lateinit var creatureAdapter: CreatureAdapter
    private var periodId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            periodId = it.getString(ARG_PERIOD_ID) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_creatures_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        rvCreatures = view.findViewById(R.id.rvCreatures)
        setupRecyclerView()
        
        // Aplicar animación de entrada
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    private fun setupRecyclerView() {
        val creatures = createCreaturesData(periodId)
        
        creatureAdapter = CreatureAdapter(creatures) { creature ->
            showCreatureDetails(creature)
        }
        
        rvCreatures.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = creatureAdapter
        }
    }

    private fun showCreatureDetails(creature: Creature) {
        // Crear diálogo personalizado
        val dialogView = LayoutInflater.from(context).inflate(
            R.layout.dialog_creature_details, 
            null
        )
        
        val dialog = android.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()
        
        // Configurar ventana del diálogo
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        
        // Configurar vistas del diálogo
        setupDialogViews(dialogView, creature, dialog)
        
        dialog.show()
    }

    private fun setupDialogViews(
        dialogView: View, 
        creature: Creature, 
        dialog: android.app.AlertDialog
    ) {
        // Configurar imagen
        val ivCreatureIcon = dialogView.findViewById<android.widget.ImageView>(R.id.ivCreatureIcon)
        ivCreatureIcon.setImageResource(creature.imageResource)
        
        // Configurar textos
        val tvCreatureName = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureName)
        val tvCreatureScientific = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureScientific)
        val tvCreatureDescription = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureDescription)
        val tvCreatureCharacteristics = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureCharacteristics)
        val tvCreatureHabitat = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureHabitat)
        val tvCreatureDiet = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureDiet)
        val tvCreatureSize = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureSize)
        val tvCreatureDiscovery = dialogView.findViewById<android.widget.TextView>(R.id.tvCreatureDiscovery)
        
        tvCreatureName.text = creature.name
        tvCreatureScientific.text = creature.scientificName
        tvCreatureDescription.text = creature.description
        tvCreatureCharacteristics.text = creature.characteristics.joinToString("\n• ", "• ")
        tvCreatureHabitat.text = creature.habitat
        tvCreatureDiet.text = creature.diet
        tvCreatureSize.text = creature.size
        tvCreatureDiscovery.text = creature.discovery
        
        // Configurar botones
        val ivCloseDialog = dialogView.findViewById<android.widget.ImageView>(R.id.ivCloseDialog)
        val btnCloseDialog = dialogView.findViewById<android.widget.Button>(R.id.btnCloseDialog)
        val btnExploreMore = dialogView.findViewById<android.widget.Button>(R.id.btnExploreMore)
        
        ivCloseDialog.setOnClickListener { dialog.dismiss() }
        btnCloseDialog.setOnClickListener { dialog.dismiss() }
        btnExploreMore.setOnClickListener {
            // Aquí se podría navegar a más información
            Toast.makeText(context, "Explorando más sobre ${creature.name}...", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

    private fun getCreatureImageResource(imageName: String): Int {
        // Ahora usamos las imágenes movidas directamente a drawable
        return when (imageName) {
            "coelophysis" -> R.drawable.creature_coelophysis
            "plateosaurus" -> R.drawable.creature_plateosaurus
            "desmatosuchus" -> R.drawable.creature_desmatosuchus
            "brachiosaurus" -> R.drawable.creature_brachiosaurus
            "stegosaurus" -> R.drawable.creature_stegosaurus
            "allosaurus" -> R.drawable.creature_allosaurus
            "tyrannosaurus" -> R.drawable.creature_tyrannosaurus
            "triceratops" -> R.drawable.creature_triceratops
            "spinosaurus" -> R.drawable.creature_spinosaurus
            "dunkleosteus" -> R.drawable.creature_dunkleosteus
            "acanthostega" -> R.drawable.creature_acanthostega
            "ichthyostega" -> R.drawable.creature_ichthyostega
            "arthropleura" -> R.drawable.creature_arthropleura
            "meganeura" -> R.drawable.creature_meganeura
            "hylonomus" -> R.drawable.creature_hylonomus
            "dimetrodon" -> R.drawable.creature_dimetrodon
            "gorgonops" -> R.drawable.creature_gorgonops
            "scutosaurus" -> R.drawable.creature_scutosaurus
            "basilosaurus" -> R.drawable.creature_basilosaurus
            "uintatherium" -> R.drawable.creature_uintatherium
            "hyaenodon" -> R.drawable.creature_hyaenodon
            "megalodon" -> R.drawable.creature_megalodon
            "sivatherium" -> R.drawable.creature_sivatherium
            "australopithecus" -> R.drawable.creature_australopithecus
            "mammuthus" -> R.drawable.creature_mammuthus
            "smilodon" -> R.drawable.creature_smilodon
            "homo_sapiens" -> R.drawable.creature_homo_sapiens
            else -> android.R.drawable.ic_menu_gallery
        }
    }

    private fun createCreaturesData(periodId: String): List<Creature> {
        return when (periodId) {
            "triassic" -> listOf(
                Creature(
                    id = "coelophysis",
                    name = "Coelophysis",
                    scientificName = "Coelophysis bauri",
                    description = "Uno de los primeros dinosaurios terópodos, ágil cazador del Triásico tardío. Vivía en manadas y era un depredador oportunista.",
                    periodId = periodId,
                    characteristics = listOf("Bípedo", "Carnívoro", "Ágil", "Cuello largo", "Vida en manada"),
                    habitat = "Llanuras secas y bosques abiertos",
                    diet = "Carnívoro - peces, pequeños reptiles e insectos",
                    size = "3 metros de largo, 60 cm de alto",
                    discovery = "Descubierto en Nuevo México, 1881 por David Baldwin",
                    imageResource = getCreatureImageResource("coelophysis")
                ),
                Creature(
                    id = "plateosaurus",
                    name = "Plateosaurus",
                    scientificName = "Plateosaurus trossingensis",
                    description = "Dinosaurio herbívoro de cuello largo, precursor de los saurópodos gigantes. Podía caminar tanto en dos como en cuatro patas.",
                    periodId = periodId,
                    characteristics = listOf("Herbívoro", "Cuello largo", "Bípedo/Cuadrúpedo", "Grande", "Dientes en forma de hoja"),
                    habitat = "Bosques de coníferas y helechos",
                    diet = "Herbívoro - coníferas, helechos y cícadas",
                    size = "8 metros de largo, 4 metros de alto",
                    discovery = "Descubierto en Alemania, 1834 por Hermann von Meyer",
                    imageResource = getCreatureImageResource("plateosaurus")
                ),
                Creature(
                    id = "desmatosuchus",
                    name = "Desmatosuchus",
                    scientificName = "Desmatosuchus spurensis",
                    description = "Reptil acorazado arcosaurio del Triásico, herbívoro con pesada armadura ósea que lo protegía de depredadores.",
                    periodId = periodId,
                    characteristics = listOf("Acorazado", "Herbívoro", "Cuadrúpedo", "Armadura ósea", "Hocico de cerdo"),
                    habitat = "Llanuras aluviales y bosques",
                    diet = "Herbívoro - raíces, plantas bajas y frutos",
                    size = "5 metros de largo, 1.5 metros de alto",
                    discovery = "Descubierto en Texas, 1877 por Jacob Boll",
                    imageResource = getCreatureImageResource("desmatosuchus")
                )
            )
            "jurassic" -> listOf(
                Creature(
                    id = "brachiosaurus",
                    name = "Brachiosaurus",
                    scientificName = "Brachiosaurus altithorax",
                    description = "Gigantesco saurópodo con patas delanteras más largas que las traseras, lo que le permitía alcanzar las copas de los árboles más altos.",
                    periodId = periodId,
                    characteristics = listOf("Gigantesco", "Cuello muy largo", "Herbívoro", "Cuadrúpedo", "Fosas nasales en la cabeza"),
                    habitat = "Bosques de coníferas altas y llanuras",
                    diet = "Herbívoro - copas de árboles altos, coníferas",
                    size = "25 metros de largo, 13 metros de alto",
                    discovery = "Descubierto en Colorado, 1903 por Elmer Riggs",
                    imageResource = getCreatureImageResource("brachiosaurus")
                ),
                Creature(
                    id = "stegosaurus",
                    name = "Stegosaurus",
                    scientificName = "Stegosaurus stenops",
                    description = "Dinosaurio herbívoro famoso por sus placas dorsales y púas en la cola. Las placas podían ayudar a regular la temperatura corporal.",
                    periodId = periodId,
                    characteristics = listOf("Placas dorsales", "Púas en cola", "Herbívoro", "Blindado", "Cerebro pequeño"),
                    habitat = "Llanuras con abundante vegetación",
                    diet = "Herbívoro - plantas bajas, helechos, musgos",
                    size = "9 metros de largo, 4 metros de alto",
                    discovery = "Descubierto en Colorado, 1877 por Othniel Charles Marsh",
                    imageResource = getCreatureImageResource("stegosaurus")
                ),
                Creature(
                    id = "allosaurus",
                    name = "Allosaurus",
                    scientificName = "Allosaurus fragilis",
                    description = "Temible depredador del Jurásico con crestas sobre los ojos y poderosas garras. Era el principal cazador de su época.",
                    periodId = periodId,
                    characteristics = listOf("Depredador ápex", "Crestas oculares", "Garras poderosas", "Bípedo", "Cazador solitario"),
                    habitat = "Bosques abiertos y llanuras aluviales",
                    diet = "Carnívoro - saurópodos jóvenes, estegosaurios",
                    size = "12 metros de largo, 4 metros de alto",
                    discovery = "Descubierto en Colorado, 1877 por Ferdinand Vandiveer Hayden",
                    imageResource = getCreatureImageResource("allosaurus")
                )
            )
            "cretaceous" -> listOf(
                Creature(
                    id = "tyrannosaurus",
                    name = "Tyrannosaurus Rex",
                    scientificName = "Tyrannosaurus rex",
                    description = "El rey de los dinosaurios depredadores, uno de los carnívoros terrestres más grandes. Sus mandíbulas podían ejercer una presión de 12,800 kilos por cm².",
                    periodId = periodId,
                    characteristics = listOf("Depredador ápex", "Mandíbulas poderosas", "Bípedo", "Brazos pequeños", "Excelente visión"),
                    habitat = "Bosques subtropicales y llanuras costeras",
                    diet = "Carnívoro - grandes herbívoros, carroña",
                    size = "12 metros de largo, 4 metros de alto",
                    discovery = "Descubierto en Montana, 1902 por Barnum Brown",
                    imageResource = getCreatureImageResource("tyrannosaurus")
                ),
                Creature(
                    id = "triceratops",
                    name = "Triceratops",
                    scientificName = "Triceratops horridus",
                    description = "Herbívoro con tres cuernos característicos y gran cresta ósea protectora. Vivía en manadas para defenderse de depredadores.",
                    periodId = periodId,
                    characteristics = listOf("Tres cuernos", "Cresta ósea", "Herbívoro", "Defensa grupal", "Pico córneo"),
                    habitat = "Bosques abiertos y praderas",
                    diet = "Herbívoro - plantas fibrosas, palmeras, helechos",
                    size = "9 metros de largo, 3 metros de alto",
                    discovery = "Descubierto en Colorado, 1889 por Othniel Charles Marsh",
                    imageResource = getCreatureImageResource("triceratops")
                ),
                Creature(
                    id = "spinosaurus",
                    name = "Spinosaurus",
                    scientificName = "Spinosaurus aegyptiacus",
                    description = "El dinosaurio depredador más grande conocido, con una distintiva vela dorsal. Adaptado para la vida semiacuática.",
                    periodId = periodId,
                    characteristics = listOf("Vela dorsal", "Semiacuático", "Hocico alargado", "Pescador", "Gigantesco"),
                    habitat = "Ríos, manglares y costas tropicales",
                    diet = "Piscivoro - peces grandes, tiburón, otros dinosaurios",
                    size = "15 metros de largo, 6 metros de alto",
                    discovery = "Descubierto en Egipto, 1912 por Ernst Stromer",
                    imageResource = getCreatureImageResource("spinosaurus")
                )
            )
            // ERA PALEOZOICA
            "devonian" -> listOf(
                Creature(
                    id = "dunkleosteus",
                    name = "Dunkleosteus",
                    scientificName = "Dunkleosteus terrelli",
                    description = "Pez acorazado gigante con mandíbulas en forma de cuchilla. Uno de los depredadores más temibles de los océanos del Devónico.",
                    periodId = periodId,
                    characteristics = listOf("Pez acorazado", "Mandíbulas-cuchilla", "Depredador ápex", "Sin dientes", "Gigante marino"),
                    habitat = "Océanos tropicales profundos",
                    diet = "Carnívoro - tiburones, otros peces acorazados",
                    size = "10 metros de largo, 4 toneladas",
                    discovery = "Descubierto en Ohio, 1867 por Jay Terrell",
                    imageResource = getCreatureImageResource("dunkleosteus")
                ),
                Creature(
                    id = "acanthostega",
                    name = "Acanthostega",
                    scientificName = "Acanthostega gunnari",
                    description = "Tetrápodo primitivo con ocho dedos, importante eslabón en la evolución de vertebrados terrestres.",
                    periodId = periodId,
                    characteristics = listOf("Ocho dedos", "Branquias", "Anfibio primitivo", "Aletas-patas", "Acuático"),
                    habitat = "Pantanos y aguas poco profundas",
                    diet = "Carnívoro - peces pequeños, invertebrados",
                    size = "60 cm de largo",
                    discovery = "Descubierto en Groenlandia, 1987 por Jenny Clack",
                    imageResource = getCreatureImageResource("acanthostega")
                ),
                Creature(
                    id = "ichthyostega",
                    name = "Ichthyostega",
                    scientificName = "Ichthyostega eigili",
                    description = "Uno de los primeros vertebrados terrestres, con características tanto de pez como de anfibio.",
                    periodId = periodId,
                    characteristics = listOf("Primer terrestre", "Cuatro patas", "Cola de pez", "Anfibio primitivo", "Histórico"),
                    habitat = "Orillas de ríos y pantanos",
                    diet = "Carnívoro - peces, artrópodos acuáticos",
                    size = "1.5 metros de largo",
                    discovery = "Descubierto en Groenlandia, 1932 por Gunnar Säve-Söderbergh",
                    imageResource = getCreatureImageResource("ichthyostega")
                )
            )
            "carboniferous" -> listOf(
                Creature(
                    id = "arthropleura",
                    name = "Arthropleura",
                    scientificName = "Arthropleura armata",
                    description = "Milpiés gigante, el artrópodo terrestre más grande de todos los tiempos. Herbívoro gigante de los bosques carboníferos.",
                    periodId = periodId,
                    characteristics = listOf("Artrópodo gigante", "Herbívoro", "Exoesqueleto", "Múltiples patas", "Terrestre"),
                    habitat = "Bosques húmedos de helechos gigantes",
                    diet = "Herbívoro - helechos, plantas en descomposición",
                    size = "2.5 metros de largo, 0.5 metros de ancho",
                    discovery = "Descubierto en Alemania, 1854 por Hermann von Meyer",
                    imageResource = getCreatureImageResource("arthropleura")
                ),
                Creature(
                    id = "meganeura",
                    name = "Meganeura",
                    scientificName = "Meganeura monyi",
                    description = "Libélula gigante con envergadura de águila. Depredador aéreo dominante de los pantanos carboníferos.",
                    periodId = periodId,
                    characteristics = listOf("Libélula gigante", "Volador", "Depredador aéreo", "Ojos compuestos", "Alas transparentes"),
                    habitat = "Pantanos y bosques húmedos",
                    diet = "Carnívoro - insectos, anfibios pequeños",
                    size = "75 cm de envergadura, 35 cm de cuerpo",
                    discovery = "Descubierto en Francia, 1885 por Charles Brongniart",
                    imageResource = getCreatureImageResource("meganeura")
                ),
                Creature(
                    id = "hylonomus",
                    name = "Hylonomus",
                    scientificName = "Hylonomus lyelli",
                    description = "Considerado el primer reptil verdadero, pequeño lagarto que vivía en troncos huecos.",
                    periodId = periodId,
                    characteristics = listOf("Primer reptil", "Huevos amnióticos", "Escamas", "Pequeño", "Terrestre"),
                    habitat = "Bosques de lycopsidas y troncos caídos",
                    diet = "Carnívoro - insectos, arácnidos",
                    size = "20 cm de largo",
                    discovery = "Descubierto en Nueva Escocia, 1852 por John William Dawson",
                    imageResource = getCreatureImageResource("hylonomus")
                )
            )
            "permian" -> listOf(
                Creature(
                    id = "dimetrodon",
                    name = "Dimetrodon",
                    scientificName = "Dimetrodon limbatus",
                    description = "Sinápsido con vela dorsal para termorregulación. Depredador ápex del Pérmico temprano.",
                    periodId = periodId,
                    characteristics = listOf("Vela dorsal", "Termorregulación", "Depredador", "Sinápsido", "Dientes diferenciados"),
                    habitat = "Llanuras áridas y bosques abiertos",
                    diet = "Carnívoro - reptiles, anfibios, peces",
                    size = "4 metros de largo, 1.7 metros de alto",
                    discovery = "Descubierto en Texas, 1878 por Edward Drinker Cope",
                    imageResource = getCreatureImageResource("dimetrodon")
                ),
                Creature(
                    id = "gorgonops",
                    name = "Gorgonops",
                    scientificName = "Gorgonops torvus",
                    description = "Depredador con dientes de sable, ancestro de los mamíferos. Cazador eficiente del Pérmico tardío.",
                    periodId = periodId,
                    characteristics = listOf("Dientes de sable", "Ancestro mamíferos", "Depredador", "Terapsido", "Visión binocular"),
                    habitat = "Llanuras secas y sabanas",
                    diet = "Carnívoro - herbívoros grandes, Dinocephalia",
                    size = "3 metros de largo, 1.2 metros de alto",
                    discovery = "Descubierto en Sudáfrica, 1876 por Robert Owen",
                    imageResource = getCreatureImageResource("gorgonops")
                ),
                Creature(
                    id = "scutosaurus",
                    name = "Scutosaurus",
                    scientificName = "Scutosaurus karpinskii",
                    description = "Herbívoro acorazado masivo con cabeza blindada. Uno de los pareiasaurios más grandes.",
                    periodId = periodId,
                    characteristics = listOf("Acorazado masivo", "Herbívoro", "Cabeza blindada", "Cuadrúpedo", "Pareiasaurio"),
                    habitat = "Llanuras con vegetación densa",
                    diet = "Herbívoro - helechos, coníferas primitivas",
                    size = "3.5 metros de largo, 2 metros de alto",
                    discovery = "Descubierto en Rusia, 1922 por Vladimir Amalitzky",
                    imageResource = getCreatureImageResource("scutosaurus")
                )
            )
            // ERA CENOZOICA
            "paleogene" -> listOf(
                Creature(
                    id = "basilosaurus",
                    name = "Basilosaurus",
                    scientificName = "Basilosaurus cetoides",
                    description = "Ballena primitiva gigante con cuerpo serpentino. Uno de los primeros cetáceos totalmente acuáticos.",
                    periodId = periodId,
                    characteristics = listOf("Ballena primitiva", "Serpentino", "Totalmente acuático", "Dientes diferenciados", "Gigante marino"),
                    habitat = "Océanos tropicales poco profundos",
                    diet = "Carnívoro - peces grandes, otras ballenas",
                    size = "20 metros de largo, 5 toneladas",
                    discovery = "Descubierto en Alabama, 1834 por Richard Harlan",
                    imageResource = getCreatureImageResource("basilosaurus")
                ),
                Creature(
                    id = "uintatherium",
                    name = "Uintatherium",
                    scientificName = "Uintatherium robustum",
                    description = "Mamífero gigante con múltiples cuernos y colmillos. Herbívoro primitivo del Paleógeno temprano.",
                    periodId = periodId,
                    characteristics = listOf("Múltiples cuernos", "Colmillos grandes", "Herbívoro gigante", "Cerebro pequeño", "Ungulado primitivo"),
                    habitat = "Bosques subtropicales y praderas",
                    diet = "Herbívoro - hojas, ramas, frutos",
                    size = "4 metros de largo, 2 metros de alto",
                    discovery = "Descubierto en Wyoming, 1872 por Othniel Charles Marsh",
                    imageResource = getCreatureImageResource("uintatherium")
                ),
                Creature(
                    id = "hyaenodon",
                    name = "Hyaenodon",
                    scientificName = "Hyaenodon horridus",
                    description = "Carnívoro mamífero primitivo con mandíbulas poderosas. Depredador dominante del Paleógeno.",
                    periodId = periodId,
                    characteristics = listOf("Carnívoro primitivo", "Mandíbulas poderosas", "Dientes cortantes", "Cazador", "Creodonte"),
                    habitat = "Bosques y praderas abiertas",
                    diet = "Carnívoro - mamíferos primitivos, carroña",
                    size = "1.5 metros de largo, 60 cm de alto",
                    discovery = "Descubierto en Francia, 1862 por Paul Gervais",
                    imageResource = getCreatureImageResource("hyaenodon")
                )
            )
            "neogene" -> listOf(
                Creature(
                    id = "megalodon",
                    name = "Megalodon",
                    scientificName = "Carcharocles megalodon",
                    description = "El tiburón más grande de todos los tiempos. Superdepredador oceánico con dientes del tamaño de una mano.",
                    periodId = periodId,
                    characteristics = listOf("Tiburón gigante", "Superdepredador", "Dientes enormes", "Océanico", "Extinción misteriosa"),
                    habitat = "Océanos tropicales y templados",
                    diet = "Carnívoro - ballenas, pinnípedos, peces grandes",
                    size = "18 metros de largo, 60 toneladas",
                    discovery = "Dientes conocidos desde antigüedad, clasificado en 1835",
                    imageResource = getCreatureImageResource("megalodon")
                ),
                Creature(
                    id = "sivatherium",
                    name = "Sivatherium",
                    scientificName = "Sivatherium giganteum",
                    description = "Jirafa gigante extinta con cuernos complejos. El mayor rumiante que jamás haya existido.",
                    periodId = periodId,
                    characteristics = listOf("Jirafa gigante", "Cuernos complejos", "Rumiante gigante", "Cuello robusto", "Herbívoro"),
                    habitat = "Sabanas y bosques abiertos",
                    diet = "Herbívoro - hojas altas, ramas de árboles",
                    size = "5 metros de largo, 5.2 metros de alto",
                    discovery = "Descubierto en India, 1836 por Hugh Falconer",
                    imageResource = getCreatureImageResource("sivatherium")
                ),
                Creature(
                    id = "australopithecus",
                    name = "Australopithecus",
                    scientificName = "Australopithecus afarensis",
                    description = "Homínido primitivo bípedo, ancestro directo del linaje humano. Famoso por el fósil 'Lucy'.",
                    periodId = periodId,
                    characteristics = listOf("Bípedo primitivo", "Ancestro humano", "Cerebro pequeño", "Mandíbulas grandes", "Evolutivo clave"),
                    habitat = "Sabanas africanas y bosques",
                    diet = "Omnívoro - frutos, semillas, insectos, carne ocasional",
                    size = "1.2 metros de alto, 30 kg",
                    discovery = "Descubierto en Etiopía, 1974 por Donald Johanson",
                    imageResource = getCreatureImageResource("australopithecus")
                )
            )
            "quaternary" -> listOf(
                Creature(
                    id = "mammuthus",
                    name = "Mammuthus",
                    scientificName = "Mammuthus primigenius",
                    description = "Mamut lanudo, icónico gigante de la Edad de Hielo. Adaptado al frío extremo con pelaje espeso.",
                    periodId = periodId,
                    characteristics = listOf("Pelaje lanudo", "Colmillos curvos", "Adaptado al frío", "Gigante", "Herbívoro"),
                    habitat = "Tundra y estepas heladas",
                    diet = "Herbívoro - hierbas, juncos, corteza de árboles",
                    size = "4 metros de largo, 3.5 metros de alto",
                    discovery = "Conocido desde la prehistoria, descrito en 1799",
                    imageResource = getCreatureImageResource("mammuthus")
                ),
                Creature(
                    id = "smilodon",
                    name = "Smilodon",
                    scientificName = "Smilodon fatalis",
                    description = "Tigre dientes de sable, icónico depredador de la megafauna pleistocena. Cazador de grandes herbívoros.",
                    periodId = periodId,
                    characteristics = listOf("Dientes de sable", "Depredador ápex", "Musculoso", "Cazador grupos", "Felino gigante"),
                    habitat = "Praderas y bosques abiertos",
                    diet = "Carnívoro - megafauna (bisontes, caballos, perezosos)",
                    size = "2 metros de largo, 1.2 metros de alto",
                    discovery = "Descubierto en Brasil, 1842 por Peter Wilhelm Lund",
                    imageResource = getCreatureImageResource("smilodon")
                ),
                Creature(
                    id = "homo_sapiens",
                    name = "Homo sapiens",
                    scientificName = "Homo sapiens sapiens",
                    description = "Humano moderno, especie dominante actual. Caracterizado por inteligencia, lenguaje y tecnología avanzada.",
                    periodId = periodId,
                    characteristics = listOf("Inteligencia superior", "Lenguaje complejo", "Tecnología", "Bípedo perfecto", "Dominante global"),
                    habitat = "Todos los continentes y ambientes",
                    diet = "Omnívoro - agricultura, ganadería, tecnología alimentaria",
                    size = "1.7 metros de alto promedio, 70 kg",
                    discovery = "Especie actual, descrita por Linnaeus en 1758",
                    imageResource = getCreatureImageResource("homo_sapiens")
                )
            )
            else -> emptyList()
        }
    }

    companion object {
        private const val ARG_PERIOD_ID = "period_id"

        fun newInstance(periodId: String): CreaturesListFragment {
            return CreaturesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PERIOD_ID, periodId)
                }
            }
        }
    }
}