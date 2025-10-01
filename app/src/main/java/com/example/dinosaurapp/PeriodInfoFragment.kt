package com.example.dinosaurapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragment que muestra información detallada del período
 */
class PeriodInfoFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_period_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupPeriodInfo(view)
        
        // Aplicar animación de entrada
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    private fun setupPeriodInfo(view: View) {
        val tvPeriodName: TextView = view.findViewById(R.id.tvPeriodName)
        val tvTimeRange: TextView = view.findViewById(R.id.tvTimeRange)
        val tvPeriodDescription: TextView = view.findViewById(R.id.tvPeriodDescription)
        val tvClimate: TextView = view.findViewById(R.id.tvClimate)
        val tvEvolution: TextView = view.findViewById(R.id.tvEvolution)
        val tvFossils: TextView = view.findViewById(R.id.tvFossils)

        val periodInfo = getPeriodInfo(periodId)
        
        tvPeriodName.text = periodInfo.name
        tvTimeRange.text = periodInfo.timeRange
        tvPeriodDescription.text = periodInfo.description
        tvClimate.text = periodInfo.climate
        tvEvolution.text = periodInfo.evolution
        tvFossils.text = periodInfo.fossils
    }

    private fun getPeriodInfo(periodId: String): PeriodInfo {
        return when (periodId) {
            "triassic" -> PeriodInfo(
                name = "Período Triásico",
                timeRange = "252 - 201 millones de años atrás",
                description = "El Triásico marca el inicio de la Era Mesozoica y el resurgimiento de la vida tras la gran extinción del Pérmico. Durante este período aparecieron los primeros dinosaurios, aunque aún eran pequeños y compartían el ecosistema con otros reptiles arcosaurios.",
                climate = "Clima cálido y seco, con un supercontinente llamado Pangea. Sin casquetes polares, con vastos desiertos interiores y costas húmedas. La atmósfera tenía niveles altos de CO₂.",
                evolution = "• Aparición de los primeros dinosaurios\n• Evolución de los primeros mamíferos\n• Diversificación de reptiles marinos\n• Desarrollo de coníferas modernas\n• Primeros pterosaurios voladores",
                fossils = "• Coelophysis - uno de los primeros dinosaurios\n• Plateosaurus - precursor de saurópodos\n• Lystrosaurus - superviviente de la extinción\n• Desmatosuchus - reptil acorazado\n• Postosuchus - gran depredador arcosaurio"
            )
            "jurassic" -> PeriodInfo(
                name = "Período Jurásico",
                timeRange = "201 - 145 millones de años atrás",
                description = "El Jurásico es famoso por ser la edad dorada de los dinosaurios. Durante este período, los saurópodos gigantes dominaron las tierras, mientras que en los océanos prosperaron reptiles marinos gigantes.",
                climate = "Clima cálido y húmedo sin hielo polar. Fragmentación de Pangea creando nuevos océanos. Abundantes bosques de coníferas, helechos y cícadas. Niveles altos del mar.",
                evolution = "• Gigantismo en saurópodos\n• Diversificación de terópodos\n• Evolución de las primeras aves\n• Dominio de reptiles marinos\n• Desarrollo de estegosaurios",
                fossils = "• Brachiosaurus - saurópodo gigante\n• Allosaurus - gran depredador\n• Stegosaurus - herbívoro acorazado\n• Archaeopteryx - primer ave conocida\n• Diplodocus - saurópodo de cuello largo"
            )
            "cretaceous" -> PeriodInfo(
                name = "Período Cretácico",
                timeRange = "145 - 66 millones de años atrás",
                description = "El Cretácico fue el último período de los dinosaurios y vio la evolución de algunos de los depredadores más grandes de todos los tiempos. También marca la aparición de las plantas con flores.",
                climate = "Clima muy cálido con alta actividad volcánica. Separación completa de continentes. Aparición de plantas con flores que transformaron los ecosistemas terrestres.",
                evolution = "• Evolución de tiranosáuridos gigantes\n• Desarrollo de ceratópsidos\n• Aparición de hadrosaurios\n• Revolución de las angiospermas\n• Diversificación de aves modernas",
                fossils = "• Tyrannosaurus rex - rey de los depredadores\n• Triceratops - herbívoro con cuernos\n• Spinosaurus - dinosaurio semiacuático\n• Parasaurolophus - hadrosaurio con cresta\n• Carcharocles megalodon - tiburón gigante"
            )
            // ERA PALEOZOICA
            "devonian" -> PeriodInfo(
                name = "Período Devónico",
                timeRange = "419 - 359 millones de años atrás",
                description = "El Devónico es conocido como la 'Era de los Peces' por la gran diversificación de peces acorazados y cartilaginosos. También marca la conquista definitiva de la tierra por las plantas y los primeros vertebrados terrestres.",
                climate = "Clima cálido con niveles del mar altos. Grandes arrecifes de coral y extensos mares poco profundos. Los continentes comenzaron a acercarse formando supercontinentes.",
                evolution = "• Diversificación masiva de peces\n• Primeros vertebrados terrestres\n• Evolución de los primeros bosques\n• Desarrollo de semillas\n• Primeros artrópodos terrestres gigantes",
                fossils = "• Dunkleosteus - pez acorazado gigante\n• Acanthostega - tetrápodo primitivo\n• Ichthyostega - primer vertebrado terrestre\n• Archaeopteris - primer árbol moderno\n• Bothriolepis - pez acorazado herbívoro"
            )
            "carboniferous" -> PeriodInfo(
                name = "Período Carbonífero",
                timeRange = "359 - 299 millones de años atrás",
                description = "El Carbonífero se caracterizó por vastos bosques pantanosos que formaron los depósitos de carbón. Fue la era de los anfibios gigantes y los primeros reptiles verdaderos.",
                climate = "Clima tropical húmedo con extensos pantanos. Altos niveles de oxígeno (35%) permitieron el gigantismo en invertebrados. Glaciaciones en el hemisferio sur.",
                evolution = "• Era de los anfibios gigantes\n• Aparición de los primeros reptiles\n• Bosques de lycopsidas gigantes\n• Artrópodos terrestres gigantes\n• Primeros reptiles voladores",
                fossils = "• Arthropleura - milpiés gigante\n• Meganeura - libélula gigante\n• Hylonomus - primer reptil verdadero\n• Eryops - anfibio gigante\n• Lepidodendron - árbol escama gigante"
            )
            "permian" -> PeriodInfo(
                name = "Período Pérmico",
                timeRange = "299 - 252 millones de años atrás",
                description = "El Pérmico culminó con la mayor extinción masiva de la historia. Dominado por reptiles sinápsidos, ancestros de los mamíferos, y terminó con la Gran Extinción Pérmica.",
                climate = "Clima árido y cálido con formación del supercontinente Pangea. Vastos desiertos interiores y actividad volcánica intensa. Reducción drástica de bosques pantanosos.",
                evolution = "• Dominio de reptiles sinápsidos\n• Evolución de termoregulación\n• Desarrollo de reptiles herbívoros\n• Aparición de arcosaurios primitivos\n• Crisis biótica final",
                fossils = "• Dimetrodon - sinápsido con vela\n• Gorgonops - depredador con dientes de sable\n• Scutosaurus - herbívoro acorazado\n• Inostrancevia - superdepredador\n• Lystrosaurus - superviviente de la extinción"
            )
            // ERA CENOZOICA
            "paleogene" -> PeriodInfo(
                name = "Período Paleógeno",
                timeRange = "66 - 23 millones de años atrás",
                description = "El Paleógeno marca el renacimiento de la vida tras la extinción de los dinosaurios. Los mamíferos se diversificaron rápidamente para ocupar nichos ecológicos vacíos.",
                climate = "Clima inicialmente cálido que se fue enfriando gradualmente. Formación de casquetes polares. Separación final de continentes creando geografía moderna.",
                evolution = "• Radiación adaptativa de mamíferos\n• Evolución de ballenas primitivas\n• Aparición de primates\n• Desarrollo de gramíneas\n• Gigantismo en aves terrestres",
                fossils = "• Basilosaurus - ballena primitiva gigante\n• Uintatherium - herbívoro cornudo\n• Hyaenodon - carnívoro primitivo\n• Gastornis - ave gigante depredadora\n• Phenacodus - ungulado primitivo"
            )
            "neogene" -> PeriodInfo(
                name = "Período Neógeno",
                timeRange = "23 - 2.6 millones de años atrás",
                description = "El Neógeno vio la expansión de las praderas y la evolución de mamíferos modernos. Los ecosistemas actuales comenzaron a tomar forma con la diversificación de mamíferos pastadores.",
                climate = "Enfriamiento global continuo con formación de casquetes polares permanentes. Expansión de praderas y reducción de bosques. Inicio de ciclos glaciales.",
                evolution = "• Expansión de mamíferos pastadores\n• Evolución de depredadores modernos\n• Diversificación de primates\n• Aparición de homínidos\n• Desarrollo de ecosistemas de sabana",
                fossils = "• Megalodon - tiburón gigante\n• Sivatherium - jirafa gigante\n• Australopithecus - homínido primitivo\n• Machairodus - felino dientes de sable\n• Deinotherium - proboscídeo primitivo"
            )
            "quaternary" -> PeriodInfo(
                name = "Período Cuaternario",
                timeRange = "2.6 millones de años - presente",
                description = "El Cuaternario se caracteriza por ciclos glaciales y la evolución humana. La megafauna pleistocena dominó hasta las extinciones del final del Pleistoceno.",
                climate = "Ciclos glaciales-interglaciales regulares. Edad de Hielo con casquetes polares masivos. Clima actual relativamente estable (Holoceno).",
                evolution = "• Evolución del género Homo\n• Extinción de megafauna pleistocena\n• Desarrollo de agricultura\n• Revolución tecnológica humana\n• Antropoceno actual",
                fossils = "• Mammuthus - mamut lanudo\n• Smilodon - tigre dientes de sable\n• Homo sapiens - humano moderno\n• Megaloceros - alce irlandés gigante\n• Coryphodon - mamífero primitivo gigante"
            )
            else -> PeriodInfo(
                name = "Período Prehistórico",
                timeRange = "Hace millones de años",
                description = "Un fascinante período de la historia de la Tierra.",
                climate = "Condiciones climáticas únicas de la época.",
                evolution = "Importantes eventos evolutivos.",
                fossils = "Diversos descubrimientos paleontológicos."
            )
        }
    }

    companion object {
        private const val ARG_PERIOD_ID = "period_id"

        fun newInstance(periodId: String): PeriodInfoFragment {
            return PeriodInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PERIOD_ID, periodId)
                }
            }
        }
    }
}

/**
 * Modelo de datos para información del período
 */
data class PeriodInfo(
    val name: String,
    val timeRange: String,
    val description: String,
    val climate: String,
    val evolution: String,
    val fossils: String
)