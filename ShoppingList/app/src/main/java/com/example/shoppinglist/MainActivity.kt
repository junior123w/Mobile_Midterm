    package com.example.shoppinglist

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.util.Log
    import android.view.KeyEvent
    import android.widget.Button
    import android.widget.TableLayout
    import android.widget.TextView
    import com.example.shoppinglist.databinding.ActivityMainBinding
    import com.google.android.material.textfield.TextInputEditText


    class MainActivity : AppCompatActivity() {

        private lateinit var userInputListName: TextInputEditText
        private lateinit var listNameTextView: TextView
        private lateinit var itemInput: TextInputEditText
        private lateinit var tableLayout: TableLayout
        private lateinit var cancelButton: Button
        private lateinit var saveButton: Button

        private val itemName = mutableListOf<TextView>()
        private val quantity = mutableListOf<TextView>()
        private val plusButton = mutableListOf<Button>()
        private val minusButton = mutableListOf<Button>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            userInputListName = findViewById(R.id.listNameInput)
            listNameTextView = findViewById(R.id.listNameTextView)
            itemInput = findViewById(R.id.itemNameInput)
            tableLayout = findViewById(R.id.shoppingLayout)
            cancelButton = findViewById(R.id.cancelButton)
            saveButton=findViewById(R.id.saveButton)

            itemName.add(findViewById(R.id.itemOne))
            itemName.add(findViewById(R.id.itemTwo))
            itemName.add(findViewById(R.id.itemThree))
            itemName.add(findViewById(R.id.itemFour))

            quantity.add(findViewById(R.id.quantityItemOne))
            quantity.add(findViewById(R.id.quantityItemTwo))
            quantity.add(findViewById(R.id.quantityItemThree))
            quantity.add(findViewById(R.id.quantityItemFour))

            plusButton.add(findViewById(R.id.plusItemOne))
            plusButton.add(findViewById(R.id.plusItemTwo))
            plusButton.add(findViewById(R.id.plusItemThree))
            plusButton.add(findViewById(R.id.plusItemFour))

            minusButton.add(findViewById(R.id.minusItemOne))
            minusButton.add(findViewById(R.id.minusItemTwo))
            minusButton.add(findViewById(R.id.minusItemThree))
            minusButton.add(findViewById(R.id.minusItemFour))



            itemInput.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    val enteredText = itemInput.text.toString().trim()
                    if (enteredText.length > 32) {
                        //truncating the text to 50 characters
                        itemInput.setText(enteredText.take(32))
                    } else {
                        updateitemName(enteredText)
                        resetQuantities()
                    }
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }


            saveButton.setOnClickListener {
                // Log item names and quantities
                for (i in itemName.indices) {
                    val itemNameText = itemName[i].text.toString()
                    val quantityText = quantity[i].text.toString()
                    // Log the data to Logcat
                    Log.d("SaveButton", "Item: $itemNameText, Quantity: $quantityText")
                }
            }

            userInputListName.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    val enteredText = userInputListName.text.toString()
                    listNameTextView.text =
                        enteredText.takeIf { it.isNotEmpty() } ?: "Shopping List"
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }


        private fun updateitemName(enteredText: String) {
            val itemNameMaxLength = 50
            for (i in itemName.indices) {
                if (itemName[i].text.isEmpty()) {
                    val truncatedText = enteredText.take(itemNameMaxLength)
                    itemName[i].text = truncatedText
                    break
                }
            }
        }

        private fun resetQuantities() {
            quantity.forEach { it.text = "0" }

            var quantities = MutableList(itemName.size) { 0 }

            for (i in plusButton.indices) {
                plusButton[i].setOnClickListener {
                    if (quantity[i].text.isNotEmpty()) {
                        quantities[i]++
                        quantity[i].text = quantities[i].toString()
                    }
                }

                minusButton[i].setOnClickListener {
                    if (quantity[i].text.isNotEmpty() && quantities[i] > 0) {
                        quantities[i]--
                        quantity[i].text = quantities[i].toString()
                    }
                }
            }
        }

    }