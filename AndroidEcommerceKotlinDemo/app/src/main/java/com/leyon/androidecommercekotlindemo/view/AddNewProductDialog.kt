package com.leyon.androidecommercekotlindemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.model.storage.entity.Products
import com.leyon.androidecommercekotlindemo.viewmodel.HomeViewModel
import com.leyon.androidecommercekotlindemo.view.sendNotification


class AddNewProductDialog(val homeViewModel: HomeViewModel) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_product_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newProductNameET  : EditText = view.findViewById<EditText>(R.id.new_product_name)
        val newProductPriceET : EditText  = view.findViewById<EditText>(R.id.new_product_price)
        val newProductStockET : EditText  = view.findViewById<EditText>(R.id.new_product_stock)

        val addNewProductButton : Button = view.findViewById<Button>(R.id.add_product_button)
        addNewProductButton.setOnClickListener {

            //verify for empty string
            if (checkNotEmptyString(newProductNameET.text.toString())
                && checkNotEmptyString(newProductPriceET.text.toString())
                && checkNotEmptyString(newProductStockET.text.toString())) {

                //if not empty, then add to database
                addProductToDb(
                    newProductNameET.text.toString(),
                    newProductPriceET.text.toString().toDouble(),
                    newProductStockET.text.toString().toInt()
                )
            } else {
                Toast.makeText(context, "Must fill all entries", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addProductToDb(newProductName : String, newProductPrice : Double, newProductStock : Int) {

        //validate input, if invalid input, then cancel add operation
        if (validateFormInput(newProductName, newProductPrice, newProductStock)) {
            return
        }

        var newProduct : Products = Products(newProductName,newProductPrice,newProductStock)
        homeViewModel.insertProduct(newProduct)

        Toast.makeText(context, "New Product Added", Toast.LENGTH_SHORT).show()


        //send notification to user after new product have been added
        sendNotification(requireContext(),"New Product", "Product ${newProductName} have been added to store.\nPrice:${newProductPrice}\nStock available:${newProductStock}")
        dismiss()
    }

    //not empty. return true if not empty, return false if empty
    private fun checkNotEmptyString(s : String) : Boolean {
        if (s.isEmpty()) {
            return false
        }
        return true
    }

    private fun validateFormInput(newProductName : String, newProductPrice : Double, newProductStock : Int) : Boolean {
        if (newProductStock < 1) {
            Toast.makeText(context, "Invalid stock count", Toast.LENGTH_SHORT).show()
        }

        return false
    }





}