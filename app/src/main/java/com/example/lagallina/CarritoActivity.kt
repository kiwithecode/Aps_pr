package com.example.lagallina

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class CarritoActivity : AppCompatActivity() {

    private lateinit var carritoAdapter: CarritoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var procederPagoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCarrito)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar el adaptador con los productos del carrito
        carritoAdapter = CarritoAdapter(Carrito.productosEnCarrito)
        recyclerView.adapter = carritoAdapter

        procederPagoButton = findViewById(R.id.botonProcederPago)
        procederPagoButton.setOnClickListener {
            val intentPago = Intent(this, PagoActivity::class.java)
            startActivity(intentPago)
        }
    }


}
