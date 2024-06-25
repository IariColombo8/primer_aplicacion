package com.example.primer_aplicacion

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //<editor-fold desc="IMAGENES GUI">
    lateinit var iv_11: ImageView
    lateinit var iv_12: ImageView
    lateinit var iv_13: ImageView
    lateinit var iv_14: ImageView
    lateinit var iv_21: ImageView
    lateinit var iv_22: ImageView
    lateinit var iv_23: ImageView
    lateinit var iv_24: ImageView
    lateinit var iv_31: ImageView
    lateinit var iv_32: ImageView
    lateinit var iv_33: ImageView
    lateinit var iv_34: ImageView
    //</editor-fold>
    //<editor-fold desc="OTROS GUI">
    lateinit var tv_j1: TextView
    lateinit var tv_j2: TextView
    lateinit var ib_sonido: ImageButton
    //</editor-fold>
    //<editor-fold desc="VARIABLES">
    var imagenesArray = arrayOf(11, 12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 26)
    var homero = 0
    var bart = 0
    var lisa = 0
    var familia = 0
    var juntos = 0
    var comida = 0
    var turno = 1
    var puntosj1 = 0
    var puntosj2 = 0
    var numeroImagen = 1
    var escuchar = true
    var parejaEncontrada = 0
    var imagen1: ImageView? = null
    var imagen2: ImageView? = null
    lateinit var mpFondo: MediaPlayer
    lateinit var mp: MediaPlayer
    //</editor-fold>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enlazarGUI()
    }

    private fun enlazarGUI() {
        //<editor-fold desc="Enlace de imágenes">
        iv_11 = findViewById(R.id.iv_11)
        iv_12 = findViewById(R.id.iv_12)
        iv_13 = findViewById(R.id.iv_13)
        iv_14 = findViewById(R.id.iv_14)
        iv_21 = findViewById(R.id.iv_21)
        iv_22 = findViewById(R.id.iv_22)
        iv_23 = findViewById(R.id.iv_23)
        iv_24 = findViewById(R.id.iv_24)
        iv_31 = findViewById(R.id.iv_31)
        iv_32 = findViewById(R.id.iv_32)
        iv_33 = findViewById(R.id.iv_33)
        iv_34 = findViewById(R.id.iv_34)
        //</editor-fold>
        //<editor-fold desc="Enlace de otros elementos GUI">
        ib_sonido = findViewById(R.id.ib_sonido)
        ib_sonido.setColorFilter(Color.GREEN)
        tv_j1 = findViewById(R.id.tv_j1)
        tv_j2 = findViewById(R.id.tv_j2)
        tv_j2.setTextColor(Color.GRAY)
        tv_j1.setTextColor(Color.WHITE)
        //</editor-fold>
        //<editor-fold desc="Inicialización de recursos">
        homero = R.drawable.homero
        bart = R.drawable.bart
        comida = R.drawable.comida
        familia = R.drawable.familia
        juntos = R.drawable.juntos
        lisa = R.drawable.lisa
        //</editor-fold>
        imagenesArray.shuffle()
        //<editor-fold desc="Asignación de tags">
        iv_11.tag = "0"
        iv_12.tag = "1"
        iv_13.tag = "2"
        iv_14.tag = "3"
        iv_21.tag = "4"
        iv_22.tag = "5"
        iv_23.tag = "6"
        iv_24.tag = "7"
        iv_31.tag = "8"
        iv_32.tag = "9"
        iv_33.tag = "10"
        iv_34.tag = "11"
        //</editor-fold>
        //<editor-fold desc="Configuración de eventos onClick">
        iv_11.setOnClickListener { seleccionar(it as ImageView) }
        iv_12.setOnClickListener { seleccionar(it as ImageView) }
        iv_13.setOnClickListener { seleccionar(it as ImageView) }
        iv_14.setOnClickListener { seleccionar(it as ImageView) }
        iv_21.setOnClickListener { seleccionar(it as ImageView) }
        iv_22.setOnClickListener { seleccionar(it as ImageView) }
        iv_23.setOnClickListener { seleccionar(it as ImageView) }
        iv_24.setOnClickListener { seleccionar(it as ImageView) }
        iv_31.setOnClickListener { seleccionar(it as ImageView) }
        iv_32.setOnClickListener { seleccionar(it as ImageView) }
        iv_33.setOnClickListener { seleccionar(it as ImageView) }
        iv_34.setOnClickListener { seleccionar(it as ImageView) }
        //</editor-fold>
        // Iniciar música de fondo
        sonido("background", true)
    }

    @SuppressLint("DiscouragedApi")
    private fun sonido(sonidoName: String, loop: Boolean = false) {
        var resID = resources.getIdentifier(
            sonidoName, "raw", packageName
        )
        if (sonidoName == "background") {
            mpFondo = MediaPlayer.create(this, resID)
            mpFondo.isLooping = loop
            mpFondo.setVolume(0.04F, 0.04F)
            if (!mpFondo.isPlaying) {
                mpFondo.start()
            }
        } else {
            mp = MediaPlayer.create(this, resID)
            mp.setOnCompletionListener { mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            }
            if (!mp.isPlaying) {
                mp.start()
            }
        }
    }

    fun musicaFondo(v: View) {
        if (escuchar) {
            mpFondo.pause()
            ib_sonido.setImageResource(R.drawable.ic_volume_off)
            ib_sonido.setColorFilter(Color.GREEN)
        } else {
            mpFondo.start()
            ib_sonido.setImageResource(R.drawable.ic_volume_on)
            ib_sonido.setColorFilter(Color.GREEN)
        }
        escuchar = !escuchar
    }

    fun seleccionar(imagen: ImageView) {
        if (!imagen.isEnabled) return // Evitar seleccionar una imagen ya encontrada
        sonido("touch")
        verificar(imagen)
    }

    private fun verificar(iv: ImageView) {
        val tag = iv.tag.toString().toInt()
        when (imagenesArray[tag]) {
            11 -> iv.setImageResource(R.drawable.homero)
            12 -> iv.setImageResource(R.drawable.bart)
            13 -> iv.setImageResource(R.drawable.lisa)
            14 -> iv.setImageResource(R.drawable.familia)
            15 -> iv.setImageResource(R.drawable.comida)
            16 -> iv.setImageResource(R.drawable.juntos)
            21 -> iv.setImageResource(R.drawable.homero)
            22 -> iv.setImageResource(R.drawable.bart)
            23 -> iv.setImageResource(R.drawable.lisa)
            24 -> iv.setImageResource(R.drawable.familia)
            25 -> iv.setImageResource(R.drawable.comida)
            26 -> iv.setImageResource(R.drawable.juntos)
        }
        if (numeroImagen == 1) {
            imagen1 = iv
            numeroImagen = 2
            iv.isEnabled = false
        } else if (numeroImagen == 2) {
            imagen2 = iv
            numeroImagen = 1
            iv.isEnabled = false
            deshabilitarImagenes()
            sonImagenesIguales()
        }
    }

    private fun sonImagenesIguales() {
        if (imagen1!!.drawable.constantState == imagen2!!.drawable.constantState) {
            sonido("success")
            if (turno == 1) {
                puntosj1++
                tv_j1.text = "J1: $puntosj1"
            } else {
                puntosj2++
                tv_j2.text = "J2: $puntosj2"
            }
            parejaEncontrada++
            // Deshabilitar las imágenes emparejadas
            imagen1!!.isEnabled = false
            imagen2!!.isEnabled = false
            varificarFinJuego()
        } else {
            sonido("no")
            Handler().postDelayed({
                imagen1!!.setImageResource(R.drawable.oculta)
                imagen2!!.setImageResource(R.drawable.oculta)
                habilitarImagenes()
                cambiarTurno()
            }, 1000)
        }
    }
    private fun deshabilitarImagenes() {
        iv_11.isEnabled = false
        iv_12.isEnabled = false
        iv_13.isEnabled = false
        iv_14.isEnabled = false
        iv_21.isEnabled = false
        iv_22.isEnabled = false
        iv_23.isEnabled = false
        iv_24.isEnabled = false
        iv_31.isEnabled = false
        iv_32.isEnabled = false
        iv_33.isEnabled = false
        iv_34.isEnabled = false
    }
    private fun habilitarImagenes() {
        iv_11.isEnabled = true
        iv_12.isEnabled = true
        iv_13.isEnabled = true
        iv_14.isEnabled = true
        iv_21.isEnabled = true
        iv_22.isEnabled = true
        iv_23.isEnabled = true
        iv_24.isEnabled = true
        iv_31.isEnabled = true
        iv_32.isEnabled = true
        iv_33.isEnabled = true
        iv_34.isEnabled = true
    }

    private fun cambiarTurno() {
        turno = if (turno == 1) 2 else 1
        actualizarColoresTurno()
    }

    private fun actualizarColoresTurno() {
        if (turno == 1) {
            tv_j1.setTextColor(Color.WHITE)
            tv_j2.setTextColor(Color.GRAY)
        } else {
            tv_j1.setTextColor(Color.GRAY)
            tv_j2.setTextColor(Color.WHITE)
        }
    }

    private fun varificarFinJuego() {
        if (parejaEncontrada == 6) {
            // Todas las parejas han sido encontradas, terminar el juego
            mpFondo.stop()
            mpFondo.release()
            sonido("win")
            val builder = AlertDialog.Builder(this)
            builder
                .setTitle("Fin del juego")
                .setMessage("Puntaje:\nJ1: $puntosj1\nJ2: $puntosj2")
                .setCancelable(false)
                .setPositiveButton("Nuevo juego") { dialogInterface, i -> resetearJuego()}
                .setNeutralButton("Salir") { dialogInterface, i ->finish()}
            val ad = builder.create()
            ad.show()
        } else {
            // Aún hay parejas por encontrar, permitir al jugador seguir seleccionando cartas
            habilitarImagenes()
        }
    }
    private fun resetearJuego() {
        // Reiniciar variables de juego
        puntosj1 = 0
        puntosj2 = 0
        parejaEncontrada = 0
        turno = 1
        numeroImagen = 1
        escuchar = true

        // Reiniciar textos de puntajes
        tv_j1.text = "J1: 0"
        tv_j2.text = "J2: 0"

        // Habilitar todas las imágenes
        habilitarTodasLasImagenes()

        // Mezclar nuevamente el array de imágenes
        imagenesArray.shuffle()

        // Iniciar música de fondo nuevamente
        sonido("background", true)
        // Establecer todas las imágenes en estado oculto
        iv_11.setImageResource(R.drawable.oculta)
        iv_12.setImageResource(R.drawable.oculta)
        iv_13.setImageResource(R.drawable.oculta)
        iv_14.setImageResource(R.drawable.oculta)
        iv_21.setImageResource(R.drawable.oculta)
        iv_22.setImageResource(R.drawable.oculta)
        iv_23.setImageResource(R.drawable.oculta)
        iv_24.setImageResource(R.drawable.oculta)
        iv_31.setImageResource(R.drawable.oculta)
        iv_32.setImageResource(R.drawable.oculta)
        iv_33.setImageResource(R.drawable.oculta)
        iv_34.setImageResource(R.drawable.oculta)

        // Mezclar nuevamente el array de imágenes
        imagenesArray.shuffle()
    }
    private fun habilitarTodasLasImagenes() {
        // Habilitar todas las imágenes
        iv_11.isEnabled = true
        iv_12.isEnabled = true
        iv_13.isEnabled = true
        iv_14.isEnabled = true
        iv_21.isEnabled = true
        iv_22.isEnabled = true
        iv_23.isEnabled = true
        iv_24.isEnabled = true
        iv_31.isEnabled = true
        iv_32.isEnabled = true
        iv_33.isEnabled = true
        iv_34.isEnabled = true
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mpFondo.isPlaying) {
            mpFondo.stop()
            mpFondo.release()
        }
        if (mp.isPlaying) {
            mp.stop()
            mp.release()
        }
    }
}
