package mx.edu.itson.practica6

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class detalle_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        val iv_pelicula_imagen: ImageView = findViewById(R.id.iv_pelicula_imagen)
        val tv_nombre_pelicula: TextView = findViewById(R.id.tv_nombre_pelicula)
        val tv_pelicula_desc: TextView = findViewById(R.id.tv_pelicula_desc)
        val seatLeft: TextView = findViewById(R.id.seatLeft)
        val buyTickets: Button = findViewById(R.id.buyTickets)

        // Recuperamos el bundle una sola vez
        val bundle = intent.extras

        if (bundle != null) {
            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            tv_nombre_pelicula.text = bundle.getString("nombre")
            tv_pelicula_desc.text = bundle.getString("sinopsis")

            // Usamos el valor que viene del catálogo para mostrar los asientos
            val seats = bundle.getInt("numberSeats")
            seatLeft.text = "$seats seats available"
        }

        buyTickets.setOnClickListener {
            val intent = Intent(this, SeatSelection::class.java)
            // Reenviamos el nombre
            intent.putExtra("name", tv_nombre_pelicula.text.toString())

            // SOLUCIÓN AL ERROR DE COMPILACIÓN:
            // Usamos bundle?.getInt para que sea una llamada segura
            // "pos" es la posición que mandamos desde Catalogo.kt
            val position = bundle?.getInt("pos") ?: -1
            intent.putExtra("id", position)

            startActivity(intent)
        }
    }
}