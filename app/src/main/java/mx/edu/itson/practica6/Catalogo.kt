package mx.edu.itson.practica6

import Pelicula
import Cliente
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class Catalogo : AppCompatActivity() {

    var adapter: PeliculaAdapter? = null
    var seriesAdapter: PeliculaAdapter? = null

    // Movimos las listas aquí para que SeatSelection pueda acceder a ellas
    companion object {
        var peliculas = ArrayList<Pelicula>()
        var series = ArrayList<Pelicula>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo)

        // Solo cargamos si las listas están vacías para no duplicar al regresar
        if (peliculas.isEmpty()) {
            cargarPeliculas()
        }
        if (series.isEmpty()) {
            cargarSeries()
        }

        adapter = PeliculaAdapter(this, peliculas)
        val gridMovies: GridView = findViewById(R.id.movies_catalogo)
        gridMovies.adapter = adapter

        seriesAdapter = PeliculaAdapter(this, series)
        val gridSeries: GridView = findViewById(R.id.mseries_catalogo)
        gridSeries.adapter = seriesAdapter
    }

    fun cargarPeliculas() {
        peliculas.add(Pelicula("Demon Slayer", R.drawable.demon, R.drawable.demon, "Demon Slayer: Kimetsu no Yaiba -To the Hashira Training- proyectará por primera vez...", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Dune", R.drawable.dune2, R.drawable.dune2, "\"Duna: Parte Dos\" explorará el viaje mítico de Paul Atreides...", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Ghostbusters", R.drawable.ghostbusters, R.drawable.ghostbusters, "En Ghostbusters Apocalipsis Fantasma, regresa la familia Spengler...", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Héroe Por Encargo", R.drawable.heroe_encargo, R.drawable.heroexencargo, "Un ex agente de las fuerzas especiales acepta un trabajo...", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Madame Web", R.drawable.madameweb, R.drawable.madame, "Madame Web cuenta la historia independiente del origen...", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Vidas Pasadas", R.drawable.vidaspasadas, R.drawable.vidaspasadas, "Nora y Hae Sung, dos amigos de la infancia profundamente unidos...", arrayListOf<Cliente>()))
    }

    fun cargarSeries() {
        series.add(Pelicula("Avatar", R.drawable.ant, R.drawable.ant, "La leyenda de Aang sigue al último sobreviviente...", arrayListOf<Cliente>()))
        series.add(Pelicula("Halo", R.drawable.halo, R.drawable.halos, "Una evacuación mortal cambia la guerra del Jefe Maestro...", arrayListOf<Cliente>()))
        series.add(Pelicula("Leveling", R.drawable.sololeveling, R.drawable.sololeveling, "En un mundo en el que ciertos humanos llamados “cazadores”...", arrayListOf<Cliente>()))
        series.add(Pelicula("Mi adorable demonio", R.drawable.adorable, R.drawable.adorabledemonios, "Se centra en la vida de Jung Koo Won...", arrayListOf<Cliente>()))
        series.add(Pelicula("El Monstruo de Seúl", R.drawable.el_monstruo, R.drawable.elmonstruoviej, "Gyeongseong, 1945. En la oscura era colonial de Seúl...", arrayListOf<Cliente>()))
        series.add(Pelicula("Witcher", R.drawable.thewitcher, R.drawable.thewitchers, "Geralt de Rivia, un cazador de monstruos mutante...", arrayListOf<Cliente>()))
    }

    class PeliculaAdapter: BaseAdapter {
        var peliculas = ArrayList<Pelicula>()
        var context: Context? = null

        constructor(context: Context, peliculas: ArrayList<Pelicula>): super() {
            this.peliculas = peliculas
            this.context = context
        }

        override fun getCount(): Int = peliculas.size
        override fun getItem(position: Int): Any = peliculas[position]
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val pelicula = peliculas[position]
            val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val vista = inflator.inflate(R.layout.pelicula, null)

            val image: ImageView = vista.findViewById(R.id.image_movie_cell)
            val tittle: TextView = vista.findViewById(R.id.tv_nombre_pelicula)

            image.setImageResource(pelicula.image)
            tittle.text = pelicula.nombre

            image.setOnClickListener {
                val intent = Intent(context, detalle_pelicula::class.java)
                intent.putExtra("nombre", pelicula.nombre)
                intent.putExtra("image", pelicula.image)
                intent.putExtra("header", pelicula.header)
                intent.putExtra("sinopsis", pelicula.sinopsis)

                // Enviamos la posición actual de la película en el arreglo
                intent.putExtra("pos", position)

                // Enviamos el cálculo de asientos (20 menos los que ya están en la lista)
                intent.putExtra("numberSeats", (20 - pelicula.seats.size))

                context!!.startActivity(intent)
            }
            return vista
        }
    }
}