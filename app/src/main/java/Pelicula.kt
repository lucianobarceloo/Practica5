data class Pelicula (
    var nombre: String, // Asegúrate que se llame 'titulo' para que coincida con tu Catalogo
    var image: Int,
    var header: Int,
    var sinopsis: String,
    var seats: ArrayList<Cliente> // Este campo es el que te falta
)