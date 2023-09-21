package com.example.lagallina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lagallina.model.Producto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var productosAdapter: ProductosAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val productos = obtenerProductosDeJson()

        productosAdapter = ProductosAdapter(productos)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productosAdapter

        val fabCarrito: FloatingActionButton = findViewById(R.id.fabCarrito)
        fabCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun obtenerProductosDeJson(): List<Producto> {
        val inputStream = resources.openRawResource(R.raw.productos)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val jsonArray = JSONArray(jsonString)
        val productosList = mutableListOf<Producto>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val producto = Producto(
                codigo = jsonObject.getString("codigo"),
                nombre = jsonObject.getString("nombre"),
                cantidad = jsonObject.getInt("cantidad"),
                imagen = jsonObject.getString("imagen")
            )
            productosList.add(producto)
        }

        return productosList
    }

}
