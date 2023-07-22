package com.example.ejercicio_18_m5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio_18_m5.databinding.ActivityMainBinding
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mSharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mSharedPreferences = getSharedPreferences("cookie",Context.MODE_PRIVATE)

        binding.buttonGuardar.setOnClickListener(){
            val texto = binding.editTextTexto.text.toString()
            val entero = binding.editTextEntero.text.toString().toInt()
            val decimal = binding.editTextDecimal.text.toString().toFloat()
            val switch = binding.switch1.isChecked
            guardarDatos(texto,entero,decimal,switch)
        }
        binding.buttonMostrar.setOnClickListener(){
            mostrarDatos()

        }
        binding.buttonBorrar.setOnClickListener(){
            borrarDatos()
        }
    }
    private fun guardarDatos(texto: String, entero: Int, decimal: Float, switch: Boolean){
        mSharedPreferences.edit().putString("miTexto",texto).apply()
        mSharedPreferences.edit().putInt("miEntero",entero).apply()
        mSharedPreferences.edit().putFloat("miDecimal",decimal).apply()
        mSharedPreferences.edit().putBoolean("miSwitch",switch).apply()


    }
    private fun mostrarDatos(){
        val texto = mSharedPreferences.getString("miTexto","")
        val entero = mSharedPreferences.getInt("miEntero",0)
        val decimal = mSharedPreferences.getFloat("miDecimal",0.0f)
        val switch = mSharedPreferences.getBoolean("miSwitch",false)

        binding.tvMostrarTexto.text = texto
        binding.tvMostrarEntero.text = entero.toString()
        binding.tvMostrarDecimal.text = decimal.toString()
        binding.tvMostrarSwitch.text = switch.toString()

        binding.switch1.isChecked = switch

    }
    private fun borrarDatos(){

        binding.tvMostrarTexto.text = ""
        binding.tvMostrarEntero.text = ""
        binding.tvMostrarDecimal.text= ""
        binding.tvMostrarSwitch.text = ""

        binding.editTextTexto.text.clear()
        binding.editTextEntero.text.clear()
        binding.editTextDecimal.text.clear()
        binding.switch1.isChecked = false

        mSharedPreferences.edit().clear().apply()

    }
}