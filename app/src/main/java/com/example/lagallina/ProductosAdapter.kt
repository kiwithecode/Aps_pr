package com.example.lagallina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lagallina.model.Producto
import android.widget.Toast


class ProductosAdapter(private val productos: List<Producto>) : RecyclerView.Adapter<ProductosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.productoImagen)
        val nombreTextView: TextView = itemView.findViewById(R.id.productoNombre)
        val productoCantidad: TextView = itemView.findViewById(R.id.productoCantidad)
        val addButton: Button = itemView.findViewById(R.id.agregarCarritoButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_item, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]

        // Aquí puedes usar Glide o Picasso para cargar la imagen si es una URL.
        Glide.with(holder.imageView.context)
            .load(producto.imagen)
            .into(holder.imageView)

        holder.nombreTextView.text = " ${producto.nombre}"
        holder.productoCantidad.text = "${producto.cantidad}$"
        holder.addButton.setOnClickListener {
            Carrito.productosEnCarrito[producto] = Carrito.productosEnCarrito.getOrDefault(producto, 0) + 1
            // Notifica al usuario (por ejemplo, con un Toast) que el producto ha sido agregado
            Toast.makeText(holder.itemView.context, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = productos.size
}
