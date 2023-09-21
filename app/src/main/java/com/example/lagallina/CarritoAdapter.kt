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

class CarritoAdapter(private val productos: MutableMap<Producto, Int>) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.productoImagen)
        val nombreTextView: TextView = itemView.findViewById(R.id.productoNombre)
        val cantidadTextView: TextView = itemView.findViewById(R.id.productoCantidad)
        val incrementButton: Button = itemView.findViewById(R.id.incrementButton)
        val decrementButton: Button = itemView.findViewById(R.id.decrementButton)
        val eliminarProductoButton: Button = itemView.findViewById(R.id.eliminarProductoButton)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carrito_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos.keys.elementAt(position)
        var cantidad = productos[producto] ?: 0

        Glide.with(holder.imageView.context)
            .load(producto.imagen)
            .into(holder.imageView)

        holder.nombreTextView.text = producto.nombre
        holder.cantidadTextView.text = "Cantidad: $cantidad"

        // Incrementar cantidad
        holder.incrementButton.setOnClickListener {
            cantidad++
            productos[producto] = cantidad
            holder.cantidadTextView.text = "Cantidad: $cantidad"
        }

        // Decrementar cantidad
        holder.decrementButton.setOnClickListener {
            if (cantidad > 1) {
                cantidad--
                productos[producto] = cantidad
                holder.cantidadTextView.text = "Cantidad: $cantidad"
            } else {
                // Si la cantidad es 1 y se pulsa decrementar, se podría eliminar el producto del carrito
                productos.remove(producto)
                notifyItemRemoved(position)
            }
        }

        // Eliminar producto del carrito
        holder.eliminarProductoButton.setOnClickListener {
            productos.remove(producto)
            notifyItemRemoved(position)
        }
    }


    override fun getItemCount(): Int = productos.size
}
