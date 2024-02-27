package com.example.shoppinglist

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

/*
* Name: Naimil Shah
* Student id: 200522618
* File Name: Shopping List
* App description: "The Shopping List app streamlines your shopping experience with a dynamic interface,
*  allowing users to effortlessly manage up to five items, log details, and customize list names
* for organized and efficient shopping."
* Date : 26-02-2024*/
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up UI and button click listeners
        setupUI()
    }

    // Set up UI elements and button click listeners
    private fun setupUI() {
        // Set up listeners for plus and minus buttons for each item
        setupButtonListeners(
            binding.plusItemOne,
            binding.minusItemOne,
            binding.quantityItemOne
        )
        // Repeat for other items...

        // Set up save button click listener
        binding.saveButton.setOnClickListener {
            // Log item names and quantities
            logItemDetails(
                binding.itemNameInputOne,
                binding.quantityItemOne
            )
            // Repeat for other items...
        }

        // Set up cancel button click listener
        binding.cancelButton.setOnClickListener {
            // Reset list name and item details
            resetListAndItems()
        }

        // Set up user list name input listener
        binding.listNameInput.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Update list name when Enter key is pressed
                updateListName()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    // Set up button click listeners for plus and minus buttons
    private fun setupButtonListeners(
        plusButton: Button,
        minusButton: Button,
        quantityTextView: TextView
    ) {
        var quantity = 0

        plusButton.setOnClickListener {
            // Increment quantity and update text view
            quantity++
            quantityTextView.text = quantity.toString()
        }

        minusButton.setOnClickListener {
            // Decrement quantity if greater than 0, and update text view
            if (quantity > 0) {
                quantity--
                quantityTextView.text = quantity.toString()
            }
        }
    }

    // Log item details to Logcat
    private fun logItemDetails(itemNameInput: TextInputEditText, quantityTextView: TextView) {
        val itemNameText = itemNameInput.text.toString()
        val quantityText = quantityTextView.text.toString()
        // Log the data to Logcat
        Log.d("SaveButton", "Item: $itemNameText, Quantity: $quantityText")
    }

    // Reset item name and quantity
    private fun resetItem(itemNameInput: TextInputEditText, quantityTextView: TextView) {
        itemNameInput.text = null
        quantityTextView.text = "0"
    }

    // Reset quantities of multiple TextViews
    private fun resetQuantities(vararg quantityTextViews: TextView) {
        for (textView in quantityTextViews) {
            textView.text = "0"
        }
    }

    // Reset list name and item details
    private fun resetListAndItems() {
        binding.listNameTextView.text = "Shopping List"
        resetItem(binding.itemNameInputOne, binding.quantityItemOne)
        // Repeat for other items...
        resetQuantities(
            binding.quantityItemOne,
            // Repeat for other items...
        )
    }

    // Update list name based on user input
    private fun updateListName() {
        val enteredText = binding.listNameInput.text.toString()
        binding.listNameTextView.text = enteredText.takeIf { it.isNotEmpty() } ?: "Shopping List"
    }
}
