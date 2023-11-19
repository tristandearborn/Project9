package com.example.project9


import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginScreen : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_screen, container, false)
        val logInButton : Button = view.findViewById(R.id.logIn)
        var auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val fragment = MainScreen()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav,fragment)?.commit()
        }
        logInButton.setOnClickListener{
            var email = view.findViewById<EditText>(R.id.email)
            var password = view.findViewById<EditText>(R.id.password)
            if (email.text.toString().isEmpty() && password.text.toString().isEmpty()){
                Toast.makeText(activity,"Email and Password is Required to Log In",Toast.LENGTH_LONG).show()
            }
            else if (email.text.toString().isEmpty()){
                Toast.makeText(activity,"Email is Required to Log In",Toast.LENGTH_LONG).show()
            }
            else if (password.text.toString().isEmpty()){
                Toast.makeText(activity,"Password is Required to Log In",Toast.LENGTH_LONG).show()
            }
            else{
                auth.signInWithEmailAndPassword(email.text.toString().trim(),password.text.toString().trim()).addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(activity,"Log In Successful",Toast.LENGTH_LONG).show()
                        val fragment = MainScreen()
                        val transaction = fragmentManager?.beginTransaction()
                        transaction?.replace(R.id.nav,fragment)?.commit()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(activity,"Email or Password Incorrect",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        val newUser : Button = view.findViewById(R.id.newUser)
        newUser.setOnClickListener{
            var email = view.findViewById<EditText>(R.id.email)
            var password = view.findViewById<EditText>(R.id.password)
            if (email.text.toString().isEmpty() && password.text.toString().isEmpty()){
                Toast.makeText(activity,"Email and Password is Required to Create an Account",Toast.LENGTH_LONG).show()
            }
            else if (email.text.toString().isEmpty()){
                Toast.makeText(activity,"Email is Required to Create an Account",Toast.LENGTH_LONG).show()
            }
            else if (password.text.toString().isEmpty()){
                Toast.makeText(activity,"Password is Required to Create an Account",Toast.LENGTH_LONG).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email.text.toString().trim(),password.text.toString().trim()).addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(activity,"Account Created Successfully",Toast.LENGTH_LONG).show()
                        val user = auth.currentUser
                        val fragment = MainScreen()
                        val transaction = fragmentManager?.beginTransaction()
                        transaction?.replace(R.id.nav,fragment)?.commit()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(activity,"Email Already in Use",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return view
    }

}