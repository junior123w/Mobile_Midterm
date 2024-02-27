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
                // Set up listeners for plus and minus buttons
                setupButtonListeners(
                    binding.plusItemOne,
                    binding.minusItemOne,
                    binding.quantityItemOne
                )
                setupButtonListeners(
                    binding.plusItemTwo,
                    binding.minusItemTwo,
                    binding.quantityItemTwo
                )
                setupButtonListeners(
                    binding.plusItemThree,
                    binding.minusItemThree,
                    binding.quantityItemThree
                )
                setupButtonListeners(
                    binding.plusItemFour,
                    binding.minusItemFour,
                    binding.quantityItemFour
                )
                setupButtonListeners(
                    binding.plusItemFive,
                    binding.minusItemFive,
                    binding.quantityItemFive
                )

                // Set up save button click listener
                binding.saveButton.setOnClickListener {
                    // Log item names and quantities
                    logItemDetails(
                        binding.itemNameInputOne,
                        binding.quantityItemOne
                    )
                    logItemDetails(
                        binding.itemNameInputTwo,
                        binding.quantityItemTwo
                    )
                    logItemDetails(
                        binding.itemNameInputThree,
                        binding.quantityItemThree
                    )
                    logItemDetails(
                        binding.itemNameInputFour,
                        binding.quantityItemFour
                    )
                    logItemDetails(
                        binding.itemNameInputFive,
                        binding.quantityItemFive
                    )
                }

                // Set up cancel button click listener
                binding.cancelButton.setOnClickListener {
                    // Reset list name
                    binding.listNameTextView.text = "Shopping List"
                    // Reset item names
                    resetItem(binding.itemNameInputOne, binding.quantityItemOne)
                    resetItem(binding.itemNameInputTwo, binding.quantityItemTwo)
                    resetItem(binding.itemNameInputThree, binding.quantityItemThree)
                    resetItem(binding.itemNameInputFour, binding.quantityItemFour)
                    resetItem(binding.itemNameInputFive, binding.quantityItemFive)
                    // Reset quantities
                    resetQuantities(
                        binding.quantityItemOne,
                        binding.quantityItemTwo,
                        binding.quantityItemThree,
                        binding.quantityItemFour,
                        binding.quantityItemFive
                    )
                }

                // Set up user list name input listener
                binding.listNameInput.setOnKeyListener { _, keyCode, event ->
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        val enteredText = binding.listNameInput.text.toString()
                        binding.listNameTextView.text =
                            enteredText.takeIf { it.isNotEmpty() } ?: "Shopping List"
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
                    quantity++
                    quantityTextView.text = quantity.toString()
                }

                minusButton.setOnClickListener {
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
        }
