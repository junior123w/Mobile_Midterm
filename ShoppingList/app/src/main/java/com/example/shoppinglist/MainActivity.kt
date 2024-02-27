    package com.example.shoppinglist

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
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