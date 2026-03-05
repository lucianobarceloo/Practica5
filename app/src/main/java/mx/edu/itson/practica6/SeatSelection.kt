package mx.edu.itson.practica6

import Cliente
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SeatSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val title: TextView = findViewById(R.id.titleSeats)
        var posMovie = -1 // [cite: 1038]

        val bundle = intent.extras
        if (bundle != null) {
            title.setText(bundle.getString("name")) // [cite: 1042]
            posMovie = bundle.getInt("id") // [cite: 1043]
        }

        val confirm: Button = findViewById(R.id.confirm)
        confirm.setOnClickListener {
            if (posMovie != -1) {
                // Agregamos un objeto Cliente a la película seleccionada en la lista global
                Catalogo.peliculas[posMovie].seats.add(Cliente("Reserva", "Pago", 0))

                Toast.makeText(this, "Enjoy the movie! :D", Toast.LENGTH_LONG).show()

                // Cierra la pantalla y regresa al catálogo automáticamente
                finish()
            }
        }

        // Referencias a los grupos de asientos (filas) [cite: 1054, 1055, 1056, 1058]
        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        // Lógica para que solo se pueda seleccionar un asiento en toda la sala [cite: 1062, 1106]

        row1.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
            }
        }

        row2.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
            }
        }

        row3.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row1.clearCheck()
                row2.clearCheck()
                row4.clearCheck()
            }
        }

        row4.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row1.clearCheck()
                row2.clearCheck()
                row3.clearCheck()
            }
        }
    }
}