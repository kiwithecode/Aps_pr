package com.example.lagallina

import android.content.Intent
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View


class PagoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        val radioGroup: RadioGroup = findViewById(R.id.pagoOpciones)
        val tarjetaInfo: LinearLayout = findViewById(R.id.tarjetaInfo)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.pagoEfectivo -> tarjetaInfo.visibility = View.GONE
                R.id.pagoTarjeta -> tarjetaInfo.visibility = View.VISIBLE
            }
        }

        val confirmarBoton: Button = findViewById(R.id.botonConfirmarPago)
        confirmarBoton.setOnClickListener {
            // Aquí debes guardar la orden en la base de datos.
            Toast.makeText(this, "Pago confirmado!", Toast.LENGTH_SHORT).show()

            // Redirige al usuario a SplashPagoActivity
            val intent = Intent(this, SplashPagoActivity::class.java)
            startActivity(intent)
            finish()  // Finaliza PagoActivity
        }

    }
}