package com.example.fbrollingdice2

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


data class statistics(val tirades:Int=0,val numdobles:Int=0,val daus:ArrayList<Int> = arrayListOf<Int>(0,0,0,0,0,0))

class MainApp:Application() {

    companion object{
        var idDispositiu= ""
        const val nomAplicacio="Rolling Dice"
        const val idAplicacio="fbrollingdice2"
        var estadistica=statistics()
    }

    //La variable s'inicialitzarà la primera vegada que s'utilitzi.
    val db: FirebaseFirestore by lazy { Firebase.firestore }

    override fun onCreate() {
        super.onCreate()

        //Es desaconsella utilitzar ids lligats al dispositiu,
        //https://developer.android.com/identity/user-data-ids
        idDispositiu = Settings.Secure.getString(getApplicationContext().contentResolver, Settings.Secure.ANDROID_ID)

        //Obtenim les dades de la base de dades.
        //Guardarem les tirades en la col·lecció Devices.
        //Per cada Device(identificat amb un id), es guardaran les estadístiques.
        val doc =db.collection("Devices").document(idDispositiu)

        //Obtenim el document corresponent al nostre dispositiu
        doc.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    //El nostre dispositiu ja estava registrat
                    val estadisticabbdd=documentSnapshot.toObject<statistics>()
                    MainApp.estadistica=estadisticabbdd!!

                } else {
                    //El nostre dispositiu no estava registrat, i el guardem amb valors per defecte.
                    db.collection("Device").document(idDispositiu).set(estadistica)
                }
            }
            .addOnFailureListener { exception ->
                // Manejar el error en caso de fallo al obtener el documento
                Log.i("App_onCreate","Error al comprobar la existencia del documento: $exception")
            }
    }
}